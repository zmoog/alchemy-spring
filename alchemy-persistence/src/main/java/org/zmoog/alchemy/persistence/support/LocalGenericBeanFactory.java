package org.zmoog.alchemy.persistence.support;


import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Load a bean factory that is in the same package of the reference class.
 * The name of the xml file must be same of the class, plus the ".xml"
 * extension.
 */
public class LocalGenericBeanFactory extends ClassPathXmlApplicationContext{

    public LocalGenericBeanFactory(Class<?> relativeClass) {
        super(relativeClass.getSimpleName() +".xml", relativeClass);
    }

    public LocalGenericBeanFactory(Object bean) {
        this(bean.getClass());
    }

}
