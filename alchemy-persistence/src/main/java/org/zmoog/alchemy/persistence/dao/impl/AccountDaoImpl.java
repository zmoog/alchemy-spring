package org.zmoog.alchemy.persistence.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.zmoog.alchemy.model.Account;
import org.zmoog.alchemy.persistence.AccountDao;
import org.zmoog.alchemy.persistence.support.ColumnToBeanPropertyRowMapper;
import org.zmoog.alchemy.persistence.support.LocalGenericBeanFactory;

import java.util.List;
import java.util.Map;
import java.util.UUID;


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
    
    @Override
    public List<Account> find() {
    	return getNamedParameterJdbcTemplate().query(sql.get("find"), new MapSqlParameterSource(), mapper);
    }
    
    @Override
    public void update(Account account) {
    	getNamedParameterJdbcTemplate().update(sql.get("update"), new BeanPropertySqlParameterSource(account));
    }
    
    @Override
    public void create(Account account) {

    	KeyHolder keyHolder = new GeneratedKeyHolder();
    	
    	getNamedParameterJdbcTemplate().update(sql.get("create"), new BeanPropertySqlParameterSource(account), keyHolder);
    	
    	logger.debug(keyHolder.getKeys());
    }
}
