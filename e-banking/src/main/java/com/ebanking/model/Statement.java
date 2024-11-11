package com.ebanking.model;

import java.sql.Date;
import java.sql.Time;

public class Statement {
	
	//Status, Date_Of_Transaction, Type_Of_Payment, Transaction_Id, Amount, User_Id, Bank_Account_Number, Transaction_Time
	private String status;
	private Date dateoftransaction;
	private String typeofpayment;
	private int transactionid;
	private String amount;
	private int userid;
	private int bankaccountnumber;
	private Time transactiontime;
	private String remainingbalance;

	public Statement() {
		
	}

	public Statement(String status, Date dateoftransaction, String typeofpayment, int transactionid, String amount,
			int userid, int bankaccountnumber, Time transactiontime, String remaingbalance) {
		this.status = status;
		this.dateoftransaction = dateoftransaction;
		this.typeofpayment = typeofpayment;
		this.transactionid = transactionid;
		this.amount = amount;
		this.userid = userid;
		this.bankaccountnumber = bankaccountnumber;
		this.transactiontime = transactiontime;
		this.remainingbalance = remainingbalance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateoftransaction() {
		return dateoftransaction;
	}

	public void setDateoftransaction(Date dateoftransaction) {
		this.dateoftransaction = dateoftransaction;
	}

	public String getTypeofpayment() {
		return typeofpayment;
	}

	public void setTypeofpayment(String typeofpayment) {
		this.typeofpayment = typeofpayment;
	}

	public int getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getBankaccountnumber() {
		return bankaccountnumber;
	}

	public void setBankaccountnumber(int bankaccountnumber) {
		this.bankaccountnumber = bankaccountnumber;
	}

	public Time getTransactiontime() {
		return transactiontime;
	}

	public void setTransactiontime(Time transactiontime) {
		this.transactiontime = transactiontime;
	}

	public String getRemainingbalance() {
		return remainingbalance;
	}

	public void setRemainingbalance(String remainingbalance) {
		this.remainingbalance = remainingbalance;
	}

	@Override
	public String toString() {
		return "Statement [status=" + status + ", dateoftransaction=" + dateoftransaction + ", typeofpayment="
				+ typeofpayment + ", transactionid=" + transactionid + ", amount=" + amount + ", userid=" + userid
				+ ", bankaccountnumber=" + bankaccountnumber + ", transactiontime=" + transactiontime
				+ ", remainingbalance=" + remainingbalance + "]";
	}

	
	
	
	
}
