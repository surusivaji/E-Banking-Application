package com.ebanking.dao;

import com.ebanking.model.BankUserDetails;

public interface UserDao {
	
	int userRegistration(BankUserDetails bankUserDetails);
	BankUserDetails userLogin(String email, String password);
	int updatePassword(String password, String email);
	BankUserDetails getUserByUserId(int userid);

}
