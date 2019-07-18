package com.qa.service;

import javax.inject.Inject;

import com.qa.persistence.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {

	@Inject
	private AccountRepository repo;
	
	@Override
	public String getAllAccounts() {
		return repo.getAllAccounts();
	}

	@Override
	public String createAccount(String account) {
		return this.repo.createAccount(account);
		
	}

	@Override
	public String deleteAccount(int AccountId) {
		return null;
		
	}

	@Override
	public String updateAccount(int AccountId, String account) {
		return null;
		
	}

}
