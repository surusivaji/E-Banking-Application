package com.ebanking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	@Override
	public List<Statement> selectStatementByUserId(int userid) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			String query = "select * from Statement where User_Id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userid);
			ResultSet resultSet = preparedStatement.executeQuery();
			//Status, Date_Of_Transaction, Type_Of_Payment, Transaction_Id, Amount, User_Id, Bank_Account_Number, Transaction_Time, Remaining_Balance
			List<Statement> list = new ArrayList<Statement>();
			while (resultSet.next()) {
				Statement statement = new Statement();
				statement.setStatus(resultSet.getString(1));
				statement.setDateoftransaction(resultSet.getDate(2));
				statement.setTypeofpayment(resultSet.getString(3));
				statement.setTransactionid(resultSet.getInt(4));
				statement.setAmount(resultSet.getString(5));
				statement.setUserid(resultSet.getInt(6));
				statement.setBankaccountnumber(resultSet.getInt(7));
				statement.setTransactiontime(resultSet.getTime(8));
				statement.setRemainingbalance(resultSet.getString(9));
				list.add(statement);
			}
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int deleteStatement(int transactionid) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			String deleteStatement = "delete from Statement where Transaction_Id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setInt(1, transactionid);
			int update = preparedStatement.executeUpdate();
			return update;
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	

}
