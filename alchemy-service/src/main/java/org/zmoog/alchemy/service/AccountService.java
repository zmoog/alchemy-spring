package org.zmoog.alchemy.service;

import java.util.List;

import org.zmoog.alchemy.model.Account;


public interface AccountService {

	Account find(String id);
	
	List<Account> find();
}