package com.qa.persistence.repository;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.persistence.domain.Account;
import com.qa.persistence.exceptions.AccountNotFoundException;
import com.qa.util.JSONUtil;

@Default
@Transactional(value = TxType.SUPPORTS)
public class AccountDBImpl implements AccountRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	@Inject
	private JSONUtil json;
	
	@Override
	public String getAllAccounts() {
		TypedQuery<Account> query = manager.createQuery("SELECT a FROM Account a", Account.class);
		return json.getJSONForObject(query.getResultList());
	}
	
	public Account findAnAccount(int AccountId) {
		return manager.find(Account.class, AccountId);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String createAccount(String account) {
		manager.persist(json.getObjectForJSON(account, Account.class));
		return SUCCESS;
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String deleteAccount(int accountNumber) {
		manager.remove(findAnAccount(accountNumber));
		return SUCCESS;
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String updateAccount(int AccountId, String account) throws AccountNotFoundException {
		Account updateTo = findAnAccount(AccountId);
		if(updateTo == null) {
			throw new AccountNotFoundException();
		}
		
		Account updateFrom = json.getObjectForJSON(account, Account.class);
		updateTo.setAccountNumber(updateFrom.getAccountNumber());
		updateTo.setFirstName(updateFrom.getFirstName());
		updateTo.setLastName(updateFrom.getLastName());
		return SUCCESS;
	}
}
