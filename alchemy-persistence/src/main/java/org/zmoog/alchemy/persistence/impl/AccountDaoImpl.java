package org.zmoog.alchemy.persistence.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.zmoog.alchemy.model.Account;
import org.zmoog.alchemy.persistence.AccountDAO;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created with IntelliJ IDEA.
 * User: zmoog
 * Date: 20/05/13
 * Time: 23.42
 * To change this template use File | Settings | File Templates.
 */
public class AccountDaoImpl extends NamedParameterJdbcDaoSupport implements AccountDAO {

    @Override
    public Account findById(String id) {
        return getNamedParameterJdbcTemplate().queryForObject(
                "select id, name from account", new MapSqlParameterSource("id", id), new RowMapper<Account>() {

            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {

                Account account = new Account();

                account.setId(rs.getString("id"));
                account.setName(rs.getString("name"));

                return account;
            }
        });
    }
}
