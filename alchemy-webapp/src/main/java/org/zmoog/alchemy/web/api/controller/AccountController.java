package org.zmoog.alchemy.web.api.controller;

import java.util.Date;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zmoog.alchemy.model.Account;

@Controller
@RequestMapping("/account")
public class AccountController extends ApplicationObjectSupport {

	public AccountController() {
		logger.debug("Ready");
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Account getById(@PathVariable("id") String id) {
		
		Account account = new Account("Fineco");
		
		account.setId(id);
		account.setDescription("Bank account");
		account.setCreatedBy("zmoog");
		account.setCreatedAt(new Date());
		
		return account;
	}
}
