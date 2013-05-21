package org.zmoog.alchemy.persistence;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.zmoog.alchemy.model.Account;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: zmoog
 * Date: 21/05/13
 * Time: 0.09
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration
public class AccountDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Resource(name = "accountDao")
    AccountDAO accountDAO;

    @Test
    public void test() {

        logger.debug("Testing!");

        Account account = accountDAO.findById("1");

        System.out.println("account: %s" + account);
    }
}
