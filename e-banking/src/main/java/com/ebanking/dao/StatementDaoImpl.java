package com.ebanking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.ebanking.model.Statement;

public class StatementDaoImpl implements StatementDao {
	
	Connection connection;
	
	private final String url = "jdbc:mysql://localhost:3306/e-banking?user=root&password=Siva@2805";
	private final String insert = "insert into statement values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public int insertStatementDetails(Statement statement) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(url);
			
			PreparedStatement ps = connection.prepareStatement(insert);
			ps.setString(1, statement.getStatus());
			ps.setDate(2, statement.getDateoftransaction());
			ps.setString(3, statement.getTypeofpayment());
			ps.setInt(4, statement.getTransactionid());
			ps.setString(5, statement.getAmount());
			ps.setInt(6, statement.getUserid());
			ps.setInt(7, statement.getBankaccountnumber());
			ps.setTime(8, statement.getTransactiontime());
			ps.setString(9, statement.getRemainingbalance());
			
			int result = ps.executeUpdate();
			return result;
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	

}
