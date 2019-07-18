package com.qa.persistence.repository;

public interface AccountRepository {

	static final String SUCCESS = "{\"message\":\"OPERATION SUCCESS\"}";
	static final String FAIL = "{\"message\":\"OPERATION FAILED\"}";
	
	String getAllAccounts();
	String createAccount(String account);
	String deleteAccount(int AccountId);
	String updateAccount(int AccountId, String account);

}
