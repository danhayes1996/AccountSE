package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.qa.service.AccountService;

@Path("/account")
public class AccountController {

	@Inject
	private AccountService service;
	
	@GET
	@Path("/getAllAccounts")
	public String getAllAccounts() {
		return service.getAllAccounts();
	}

	@POST
	@Path("/createAccount")
	public String createAccount(String account) {
		return this.service.createAccount(account);
	}
	
	@POST
	@Path("/deleteAccount")
	public String deleteAccount(int AccountId) {
		return this.service.deleteAccount(AccountId);
	}

	@POST
	@Path("/updateAccount")
	public String updateAccount(int AccountId, String account) {
		return this.service.updateAccount(AccountId, account);
	}
}
