package com.qa.MapTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountMapRepository;
import com.qa.util.JSONUtil;

public class AccountServiceTest {

	AccountMapRepository accounts;
	JSONUtil json = new JSONUtil();
	
	@Before
	public void setup() {
		accounts = new AccountMapRepository();
	}
	
	@Test
	public void addAccountTest() {
		accounts.createAccount("{\"accountNumber\":10, \"firstName\":\"Dan\", \"lastName\": \"Hayes\"}");	
		assertEquals("adding an account", 1, accounts.size());
	}
	
	@Test
	public void add2AccountsTest() {
		accounts.createAccount("{\"accountNumber\":10, \"firstName\":\"Dan\", \"lastName\": \"Hayes\"}");
		accounts.createAccount("{\"accountNumber\":15, \"firstName\":\"John\", \"lastName\": \"Doe\"}");
		assertEquals("adding 2 accounts", 2, accounts.size());
	}

	@Test
	public void removeAccountTest() {
		accounts.createAccount("{\"accountNumber\":10, \"firstName\":\"Dan\", \"lastName\": \"Hayes\"}");	
		accounts.deleteAccount(0);
		assertEquals("removing an account", 0, accounts.size());
	}
	
	@Test
	public void remove2AccountsTest() {
		accounts.createAccount("{\"accountNumber\":10, \"firstName\":\"Dan\", \"lastName\": \"Hayes\"}");
		accounts.createAccount("{\"accountNumber\":15, \"firstName\":\"John\", \"lastName\": \"Doe\"}");
		accounts.deleteAccount(0);
		accounts.deleteAccount(1);
		assertEquals("removing 2 accounts", 0, accounts.size());
	}
	
	@Test
	public void remove2AccountTestAnd1ThatDoesntExist() {
		accounts.createAccount("{\"accountNumber\":10, \"firstName\":\"Dan\", \"lastName\": \"Hayes\"}");
		accounts.createAccount("{\"accountNumber\":15, \"firstName\":\"John\", \"lastName\": \"Doe\"}");
		accounts.deleteAccount(0);
		accounts.deleteAccount(2);
		assertEquals("removing 2 accounts, 1 doesnt exist", 1, accounts.size());
	}
	
	@Test
	public void jsonStringToAccountConversionTest() {
		Account a = new Account(10, "Dan", "Hayes");
		Account a2 = json.getObjectForJSON("{\"accountNumber\":10, \"firstName\":\"Dan\", \"lastName\": \"Hayes\"}", Account.class);
		assertTrue("test converting from json to Account", a.equals(a2));
	}

	@Test
	public void accountConversionToJSONTest() {
		String str = "{\"accountNumber\":10,\"firstName\":\"Dan\",\"lastName\":\"Hayes\"}";
		//assertTrue("Test converting from Account to json", str.equals(json.getJSONForObject(new Account(10, "Dan", "Hayes"))));
	}

	@Test
	public void getCountForFirstNamesInAccountWhenZeroOccurances() {
		assertEquals("get first names where list size is 0", Arrays.asList(), accounts.getAccountsByFirstName("Dan"));
	}
	
	@Test
	public void getCountForFirstNamesInAccountWhenOne() {
		accounts.createAccount("{\"accountNumber\":10, \"firstName\":\"Dan\", \"lastName\": \"Hayes\"}");	
		assertEquals("get first names where list size is 1", Arrays.asList(new Account(10, "Dan", "Hayes")), accounts.getAccountsByFirstName("Dan"));
	}

	@Test
	public void getCountForFirstNamesInAccountWhenTwo() {
		accounts.createAccount("{\"accountNumber\":10, \"firstName\":\"Dan\", \"lastName\": \"Hayes\"}");	
		accounts.createAccount("{\"accountNumber\":15, \"firstName\":\"John\", \"lastName\": \"Doe\"}");
		assertEquals("get first names where list size is 1", Arrays.asList(new Account(10, "Dan", "Hayes")), accounts.getAccountsByFirstName("Dan"));	
	}

}
