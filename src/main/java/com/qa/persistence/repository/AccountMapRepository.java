package com.qa.persistence.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Alternative
public class AccountMapRepository implements AccountRepository{

	private int idCount = 0;
	
	Map<Integer, Account> accountMap = new HashMap<>();
	@Inject
	private JSONUtil json;
	
	public String getAllAccounts() {
		return json.getJSONForObject(accountMap.values());
	}

	public String createAccount(String account) {
		Account a = json.getObjectForJSON(account, Account.class);
		return accountMap.put(idCount++, a) != null ? SUCCESS : FAIL;
	}

	public String deleteAccount(int accountNumber) {
		return accountMap.remove(accountNumber) != null ? SUCCESS : FAIL;
}

	public String updateAccount(int accountNumber, String account) {
		Account oldValue = accountMap.replace(accountNumber, json.getObjectForJSON(account, Account.class));
		if(oldValue != null) {
			return json.getJSONForObject(oldValue);
		}
		return FAIL;
	}
	
	public List<Account> getAccountsByFirstName(String firstName){
		return accountMap.values()
				.stream()
				.filter(a -> a.getFirstName().equals(firstName))
				.collect(Collectors.toList());
	}

	public int size() {
		return accountMap.size();
	}
	
}
