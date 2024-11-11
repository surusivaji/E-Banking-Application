package com.ebanking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebanking.dao.UserDao;
import com.ebanking.dao.UserDaoImpl;
import com.ebanking.model.BankUserDetails;

public class UserRegistration extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String firstname = request.getParameter("fname");
		String lastname = request.getParameter("lname");
		String mobilenumber = request.getParameter("mobile");
		String emailid = request.getParameter("email");
		String password = request.getParameter("password");
		String aadhar = request.getParameter("aadhar");
		String gender = request.getParameter("gender");
		String tamount = request.getParameter("amount");
		double amount = Double.parseDouble(tamount);
		
		String tage = request.getParameter("age");
		int age = Integer.parseInt(tage);
		
		String tdob = request.getParameter("dob");
		Date dateofbirth = Date.valueOf(tdob);
		
		String fathername = request.getParameter("fathername");
		String address = request.getParameter("address");
		
		BankUserDetails bankUserDetails = new BankUserDetails();
		bankUserDetails.setFirstname(firstname);
		bankUserDetails.setLastname(lastname);
		bankUserDetails.setEmailid(emailid);
		bankUserDetails.setPassword(password);
		bankUserDetails.setMobilenumber(mobilenumber);
		bankUserDetails.setAadharnumber(aadhar);
		bankUserDetails.setGender(gender);
		bankUserDetails.setAddress(address);
		bankUserDetails.setAge(age);
		bankUserDetails.setDateofbirth(dateofbirth);
		bankUserDetails.setAmount(amount);
		bankUserDetails.setFathername(fathername);
		
		UserDao userDao = new UserDaoImpl();
		
		int result = userDao.userRegistration(bankUserDetails);
		if (result!=0) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserLogin.html");
			requestDispatcher.forward(request, response);
		}
		else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserRegistration.html");
			requestDispatcher.include(request, response);
			out.println("<h3 style='text-align:center;color:red'>Invalid Credientials</h3>");
		}
		
	}

}
