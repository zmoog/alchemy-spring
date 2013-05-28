package org.zmoog.alchemy.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.zmoog.alchemy.model.Account;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: zmoog
 * Date: 28/05/13
 * Time: 23:18
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration
public class AccountServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Resource(name = "accountService")
    AccountService accountService;


    @Test
    public void test() {

        Account account = accountService.find("1");

        Assert.assertNotNull(account);

    }

}
