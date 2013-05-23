package org.zmoog.alchemy.web.api.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.zmoog.alchemy.model.Account;
import org.zmoog.alchemy.services.AccountService;


@Controller
@RequestMapping("/account")
public class AccountController extends ApplicationObjectSupport {

	@Resource(name="accountService")
	AccountService accountService;

	public AccountController() {
		logger.debug("Ready");
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Account getById(@PathVariable("id") String id) {
		return accountService.find(id);
	}
}
