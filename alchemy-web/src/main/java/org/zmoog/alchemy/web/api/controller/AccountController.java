package org.zmoog.alchemy.web.api.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.zmoog.alchemy.model.Account;
import org.zmoog.alchemy.service.AccountService;


@Controller
@RequestMapping("/account")
public class AccountController extends ApplicationObjectSupport {

	@Resource(name="accountService")
	AccountService accountService;

	public AccountController() {
		logger.debug("Ready");
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	@ResponseBody
	public List<Account> getAll() {
		return accountService.find();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Account getById(@PathVariable("id") String id) {
		return accountService.find(id);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public void update(@PathVariable("id") String id, @RequestBody Account account) {
		logger.info(String.format("Updating account %s", account));
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public void create(@RequestBody Account account) {
		logger.info(String.format("Creating account %s", account));
	}

}
