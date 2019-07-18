package com.qa.service;

public interface AccountService {

	public String getAllAccounts();
	public String createAccount(String account);
	public String deleteAccount(int AccountId);
	public String updateAccount(int AccountId, String account);
}
