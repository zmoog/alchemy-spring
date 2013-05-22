package org.zmoog.alchemy.services.impl;

import java.util.Date;

import org.zmoog.alchemy.model.Account;
import org.zmoog.alchemy.services.AccountService;


public class AccountServiceImpl implements AccountService {

	public Account find(String id) {

		Account account = new Account("Fineco");
		
		account.setId(id);
		account.setDescription("Bank account");
		account.setCreatedBy("zmoog");
		account.setCreatedAt(new Date());
		
		return account;
	}
}