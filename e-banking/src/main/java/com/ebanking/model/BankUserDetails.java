package com.ebanking.model;

import java.sql.Date;

public class BankUserDetails {
	
	private int id;
	private String firstname;
	private String lastname;
	private String emailid;
	private String password;
	private String mobilenumber;
	private String aadharnumber;
	private String gender;
	private String address;
	private int age;
	private Date dateofbirth;
	private double amount;
	private int accountnumber;
	private String fathername;
	private String ifsccode;
	
	public BankUserDetails() {
		
	}

	public BankUserDetails(int id, String firstname, String lastname, String emailid, String password,
			String mobilenumber, String aadharnumber, String gender, String address, int age, Date dateofbirth,
			double amount, int accountnumber, String fathername, String ifsccode) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailid = emailid;
		this.password = password;
		this.mobilenumber = mobilenumber;
		this.aadharnumber = aadharnumber;
		this.gender = gender;
		this.address = address;
		this.age = age;
		this.dateofbirth = dateofbirth;
		this.amount = amount;
		this.accountnumber = accountnumber;
		this.fathername = fathername;
		this.ifsccode = ifsccode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getAadharnumber() {
		return aadharnumber;
	}

	public void setAadharnumber(String aadharnumber) {
		this.aadharnumber = aadharnumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getAccountnumber() {
		return accountnumber;
	}

	public  void setAccountnumber(int accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getFathername() {
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	public String getIfsccode() {
		return ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}	

}
