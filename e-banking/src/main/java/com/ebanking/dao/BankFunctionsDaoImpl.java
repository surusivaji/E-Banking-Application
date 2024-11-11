package com.ebanking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BankFunctionsDaoImpl implements BankFunctionsDao {
	
	private Connection connection;
	private final String url = "jdbc:mysql://localhost:3306/e-banking?user=root&password=Siva@2805";
	private final String updateBalance = "update bank_user_details SET Amount =? where Email_Id = ?";
	private final String changePassword = "update bank_user_details SET Password=? where Password=?";

	@Override
	public int updateAmount(double amount, String emailid) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(updateBalance);
			preparedStatement.setDouble(1, amount);
			preparedStatement.setString(2, emailid);
			int result = preparedStatement.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int changePassword(String password, String newPassword) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(changePassword);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, newPassword);
			int result = preparedStatement.executeUpdate();
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	

}
