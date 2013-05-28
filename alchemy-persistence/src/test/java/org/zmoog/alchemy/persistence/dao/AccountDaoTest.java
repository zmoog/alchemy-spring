package org.zmoog.alchemy.persistence.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.zmoog.alchemy.model.Account;
import org.zmoog.alchemy.persistence.AccountDao;

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
    AccountDao accountDAO;

    @Test
    public void test() {

        logger.debug("Testing!");

        Account account = accountDAO.findById("1");

        Assert.assertNotNull(account);

        logger.debug("account: %s" + account);
    }

    @Test
    public void testNotExisting() {

        logger.debug("Testing!");

        try {

            accountDAO.findById("99");

            Assert.fail("You should not find this account.");

        } catch (EmptyResultDataAccessException e) {
            // ok
        }

    }
}
