package org.zmoog.alchemy.persistence.dao;

import org.junit.Assert;
import static org.junit.Assert.*; 
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.zmoog.alchemy.model.Account;
import org.zmoog.alchemy.persistence.AccountDao;

import javax.annotation.Resource;

import java.util.List;
import java.math.BigDecimal;

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
    public void testFindAll() {

        List<Account> accountList = accountDAO.find();

        logger.info(accountList);

        Assert.assertNotNull(accountList);
    }


    @Test
    public void test() {

        logger.debug("Testing!");

        Account account = accountDAO.findById("32314574-3231-4574-9842-b26f9842b26f");

        Assert.assertNotNull(account);

        logger.debug("account: %s" + account);
    }

    @Test
    public void testNotExisting() {

        logger.debug("Testing!");

        try {

            accountDAO.findById("32314574-3231-4574-9842-b26f9842b222");

            Assert.fail("You should not find this account.");

        } catch (EmptyResultDataAccessException e) {
            // ok
        }

    }

    @Test
    public void testInsert() {

        logger.debug("Insert new account");

        Account account = new Account();

        account.setName("Test");
        account.setDescription("Test account");
        account.setAccountType("asset");
        account.setBalance(new BigDecimal("729.01"));
        account.setCreatedBy("lgelli");

        accountDAO.create(account);
        
        
        Account newAccount = accountDAO.findById(account.getId());
        
        logger.debug(String.format("newAccount: %s", newAccount));
        
        assertNotNull(newAccount);
        assertEquals(newAccount.getId(), account.getId());
    }    
}
