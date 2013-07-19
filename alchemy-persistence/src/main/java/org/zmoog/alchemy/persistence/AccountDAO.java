package org.zmoog.alchemy.persistence;

import java.util.List;

import org.zmoog.alchemy.model.Account;

/**
 * Created with IntelliJ IDEA.
 * User: zmoog
 * Date: 20/05/13
 * Time: 1.14
 * To change this template use File | Settings | File Templates.
 */
public interface AccountDao {
    
    Account findById(String id);

	List<Account> find();
	
	void update(Account account);

	void create(Account account);
}
