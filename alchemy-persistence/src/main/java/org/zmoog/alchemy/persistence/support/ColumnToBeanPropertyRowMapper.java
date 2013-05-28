package org.zmoog.alchemy.persistence.support;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.NumberUtils;

/**
 * Mapper that maps ResultSet rows into JavaBean.
 * The mapping binds column to bean property with the same name of the column:
 * name comparisions are case-insensitive.
 */
public final class ColumnToBeanPropertyRowMapper<T extends Object>
        extends ApplicationObjectSupport implements RowMapper<T> {

    private Class<T> beanClass;

    /** Descriptors of all writable bean properties. */
    private final HashMap<String, PropertyDescriptor> propertyDescriptors;

    /** Descriptors of the current ResultSet properties. */
    private static final ThreadLocal<PropertyDescriptor[]> currentDescriptors =
            new ThreadLocal<PropertyDescriptor[]>();

    /** Current Statement of ResultSet. */
    private static final ThreadLocal<Statement> currentStatement =
            new ThreadLocal<Statement>();

    /**
     * Create a Mapper using 'beanClass' as target JavaBean class.
     */
    private ColumnToBeanPropertyRowMapper(Class<T> mappedClass) {
        this.beanClass = mappedClass;
        PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(beanClass);
        propertyDescriptors = new HashMap<String, PropertyDescriptor>();

        // Collect all writable properties in a case-insensitive Map.
        for (int i = 0; i < descriptors.length; i++) {
            PropertyDescriptor descriptor = descriptors[i];
            if ("class".equals(descriptor.getName())) {
                // Skip this property.
                continue;
            } else if (descriptor.getWriteMethod() == null) {
                logger.debug("No write method for property "+ descriptor.getName()
                        +" in class "+ beanClass.getName());
            } else {
                propertyDescriptors.put(descriptor.getName().toLowerCase(),	descriptor);
            }
        }
    }

    public static final <T> RowMapper<T> newInstance(Class<T> mappedClass) {
        return new ColumnToBeanPropertyRowMapper<T>(mappedClass);
    }

    /**
     * Creates a new JavaBean and populates it with properties derived from
     * columns of the ResultSet.
     */
    public final T mapRow(final ResultSet rs, int rowNum) throws SQLException {
        try {
            // Create the instance to populate.
            T beanInstance = beanClass.newInstance();

            // Get the corresponding bean property descriptor for each column.
            PropertyDescriptor[] descriptors = getDescriptorsForColumns(rs);
            for (int i = 0; i < descriptors.length; i++) {
                PropertyDescriptor descriptor = descriptors[i];
                if (descriptor == null) {
                    continue;
                }
                // Try to get the column value and set the bean property.
                Object columnValue = getColumnValue(rs, i + 1, descriptor.getPropertyType());
                setSimpleProperty(beanInstance, descriptor, columnValue);
            }
            return beanInstance;
        } catch (Exception e) {
            logger.error("Failure in mapping rowNum " + rowNum, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the array that maps column indexes with the JavaBean property
     * descriptors.
     * The mapping is created only once for the first row (rowNum = 0), and it
     * is mantained for every single thread.
     */
    private PropertyDescriptor[] getDescriptorsForColumns(final ResultSet rs) throws SQLException {

        // Ricarica i descrittori se non ci sono gia' o se e' cambiato statement
        if (currentStatement.get() != rs.getStatement()) {

            // logger.debug("New mapping for statement "+ rs.getStatement());

            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();
            PropertyDescriptor[] descriptors = new PropertyDescriptor[columnCount];

            // Retrieve bean properties from ResultSet column names.
            for (int i = 0; i < columnCount; i++) {
                String columnName = rsMetaData.getColumnLabel(i + 1);
                descriptors[i] = propertyDescriptors.get(columnName.toLowerCase());

                if (descriptors[i] == null && logger.isDebugEnabled()) {
                    logger.warn("No property mapping found for column "
                            + columnName +" in class "+ beanClass.getName());
                }
            }
            currentDescriptors.set(descriptors);
            currentStatement.set(rs.getStatement());
        }
        return currentDescriptors.get();
    }

    /**
     * Set a simple property of the bean with the given value.
     */
    private void setSimpleProperty(final Object bean, final PropertyDescriptor descriptor,
                                   final Object value)	throws Exception {
        try {
            Method writeMethod = descriptor.getWriteMethod();
            writeMethod.invoke(bean, new Object[] { value });
        } catch (Exception e) {
            logger.error("Cannot set property " + descriptor.getName() +" in class "+
                    beanClass.getName() +" with value "+ value, e);
            throw new Exception(e);
        }
    }

    /**
     * Try to convert the column of ResultSet into an object type. Convert most
     * common Java types (String, Integer, Boolean,...).
     * Override this to provide a specialized mapping for special types, like Blob.
     */
    protected Object getColumnValue(final ResultSet rs, final int columnIndex,
                                    final Class<?> targetType) throws SQLException {
        if (targetType.equals(String.class)) {
            return rs.getString(columnIndex);
        }
        if (targetType.equals(java.util.Date.class)) {
            return rs.getTimestamp(columnIndex);
        }
        if (Number.class.isAssignableFrom(targetType)) {
            // cast to the Number class
            @SuppressWarnings("unchecked")
            Class<? extends Number> numberClass = (Class<? extends Number>)targetType;
            // try to convert a generic number
            Object value = rs.getObject(columnIndex);
            if (rs.wasNull()) {
                return null;
            } else {
                return NumberUtils.convertNumberToTargetClass((Number)value, numberClass);
            }
        }
        if (targetType.equals(Boolean.class)) {
            return new Boolean(rs.getBoolean(columnIndex));
        }

        return rs.getObject(columnIndex);
    }
}