package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int accountNumber;
	private String firstName;
	private String  lastName;
	
	public Account(int accNum, String firstName, String lastName) {
		this.accountNumber = accNum;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Account() { } //needed for JPA
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "Account:[accountNumber=" + accountNumber + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		Account other = (Account) obj;
		return accountNumber == other.accountNumber &&
				firstName.equals(other.firstName) &&
				lastName.equals(other.lastName);
	}
}
