package com.ebanking.dao;

public interface BankFunctionsDao {

	int updateAmount(double amount, String email);
	int changePassword(String password, String newPassword);
	
}
