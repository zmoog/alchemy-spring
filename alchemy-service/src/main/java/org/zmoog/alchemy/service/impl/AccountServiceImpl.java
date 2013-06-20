package org.zmoog.alchemy.service.impl;

import java.util.List;

import org.springframework.context.support.ApplicationObjectSupport;
import org.zmoog.alchemy.model.Account;
import org.zmoog.alchemy.persistence.AccountDao;
import org.zmoog.alchemy.service.AccountService;

import javax.annotation.Resource;


public class AccountServiceImpl extends ApplicationObjectSupport implements AccountService {

    @Resource(name = "accountDao")
    AccountDao accountDao;

	public Account find(String id) {

        logger.debug(String.format("id: %s", id));

		return accountDao.findById(id);
	}

	public List<Account> find() {
		return accountDao.find();
	}
	
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}