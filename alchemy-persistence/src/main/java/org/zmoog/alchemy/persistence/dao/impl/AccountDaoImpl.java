package org.zmoog.alchemy.persistence.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.zmoog.alchemy.model.Account;
import org.zmoog.alchemy.persistence.AccountDao;
import org.zmoog.alchemy.persistence.support.ColumnToBeanPropertyRowMapper;
import org.zmoog.alchemy.persistence.support.LocalGenericBeanFactory;

import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: zmoog
 * Date: 20/05/13
 * Time: 23.42
 * To change this template use File | Settings | File Templates.
 */
public class AccountDaoImpl extends NamedParameterJdbcDaoSupport implements AccountDao {

    private final Map<String, String> sql;

    private final RowMapper<Account> mapper = ColumnToBeanPropertyRowMapper
            .newInstance(Account.class);

    public AccountDaoImpl() {
        LocalGenericBeanFactory beans = new LocalGenericBeanFactory(this);
        sql = beans.getBeansOfType(String.class);
    }

    @Override
    public Account findById(String id) {
        return getNamedParameterJdbcTemplate().queryForObject(
                sql.get("findById"), new MapSqlParameterSource("id", id), mapper);
    }
}
