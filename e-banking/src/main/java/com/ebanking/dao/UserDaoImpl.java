package com.ebanking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import com.ebanking.model.BankUserDetails;

public class UserDaoImpl implements UserDao {
	
	private Connection connection;
	private final String url = "jdbc:mysql://localhost:3306/e-banking?user=root&password=Siva@2805";
	private final String registration = "insert into Bank_User_Details(First_Name, Last_Name, Email_Id, Password, Mobile_Number, aadhar_number, Gender, Account_Number, Address, Age, Date_Of_Birth, IFSC_Code, Amount, Father_Name)values(?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?)";
	private final String driver = "com.mysql.cj.jdbc.Driver";
	private final String login = "select * from Bank_User_Details where (Email_Id=? or Mobile_Number=?) and Password=?";
	private final String updatePassword = "update Bank_User_Details set Password=? where Email_Id=?";

	@Override
	public int userRegistration(BankUserDetails bankUserDetails) {
		
		try {
			Class.forName(driver);
			
			connection = DriverManager.getConnection(url);
			
			PreparedStatement ps = connection.prepareStatement(registration);
			ps.setString(1, bankUserDetails.getFirstname());
			ps.setString(2, bankUserDetails.getLastname());
			ps.setString(3, bankUserDetails.getEmailid());
			ps.setString(4, bankUserDetails.getPassword());
			ps.setString(5, bankUserDetails.getMobilenumber());
			ps.setString(6, bankUserDetails.getAadharnumber());
			ps.setString(7, bankUserDetails.getGender());
			Random random = new Random();
			int accountnumber = random.nextInt(1000000);
			if (accountnumber<10000000) {
				accountnumber = accountnumber+1000000;
			}
			ps.setInt(8, accountnumber);
			ps.setString(9, bankUserDetails.getAddress());
			ps.setInt(10, bankUserDetails.getAge());
			ps.setDate(11, bankUserDetails.getDateofbirth());
			ps.setString(12, "TECA5556");
			ps.setDouble(13, bankUserDetails.getAmount());
			ps.setString(14, bankUserDetails.getFathername());
			
			int result = ps.executeUpdate();
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public BankUserDetails userLogin(String email, String password) {
		
		try {
			Class.forName(driver);
			
			connection = DriverManager.getConnection(url);
			
			PreparedStatement preparedStatement = connection.prepareStatement(login);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, password);
			
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				BankUserDetails bankUserDetails = new BankUserDetails();
				bankUserDetails.setId(rs.getInt(1));
				bankUserDetails.setFirstname(rs.getString(2));
				bankUserDetails.setLastname(rs.getString(3));
				bankUserDetails.setEmailid(rs.getString(4));
				bankUserDetails.setPassword(rs.getString(5));
				bankUserDetails.setMobilenumber(rs.getString(6));
				bankUserDetails.setAadharnumber(rs.getString(7));
				bankUserDetails.setGender(rs.getString(8));
				bankUserDetails.setAccountnumber(rs.getInt(9));
				bankUserDetails.setAddress(rs.getString(10));
				bankUserDetails.setAge(rs.getInt(11));
				bankUserDetails.setDateofbirth(rs.getDate(12));
				bankUserDetails.setIfsccode(rs.getString(13));
				bankUserDetails.setAmount(rs.getDouble(14));
				bankUserDetails.setFathername(rs.getString(15));
				return bankUserDetails;
			}
			else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public int updatePassword(String password, String email) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(updatePassword);
			ps.setString(1, password);
			ps.setString(2, email);
			int result = ps.executeUpdate();
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
