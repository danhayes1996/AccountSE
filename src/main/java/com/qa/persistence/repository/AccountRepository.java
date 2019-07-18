package com.qa.persistence.repository;

public interface AccountRepository {

	static final String SUCCESS = "{\"message\":\"OPERATION SUCCESS\"}";
	static final String FAIL = "{\"message\":\"OPERATION FAILED\"}";
	
	public String getAllAccounts();
	public String createAccount(String account);
	public String deleteAccount(int AccountId);
	public String updateAccount(int AccountId, String account);
}
