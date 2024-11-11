package com.ebanking.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/otpValidation")
public class OTPValidation extends HttpServlet {

	public String accountNo(int num) {
		String accountNumber = num + "";
		String ac = "";
		for (int i=0; i<=1; i++) {
			ac = ac + accountNumber.charAt(i);
		}
		for (int i=2; i<=4; i++) {
			ac = ac + '*';
		}
		for (int i=5; i<=6; i++) {
			ac = ac + accountNumber.charAt(i);
		}
		return ac;
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String uotp = request.getParameter("uotp");
		int otp = Integer.parseInt(uotp);

		HttpSession session = request.getSession();

		Integer gotp = (Integer) session.getAttribute("gotp");
		if (gotp != null) {
			
			String firstname = (String) session.getAttribute("fname");
			String lastname = (String) session.getAttribute("lname");
			String gender = (String) session.getAttribute("gender");
			int accountno = (Integer) session.getAttribute("accountno");
	
			String accountnumber = accountNo(accountno);
			if (otp == gotp) {
				if (gender.equalsIgnoreCase("male")) {
					out.println("<h1 style='text-align:center;margin-top:10px'>Welcome Mr: <span style='color:red;text-transform:uppercase'>"+ firstname + " " + lastname + "</span></h1>");
					out.println("<h2 style='text-align:center;margin-top:10px'>Your A/C Number: " + accountnumber+ "</h2>");
				} 
				else {
					out.println("<h1 style='text-align:center;margin-top:10px'>Welcome Miss: <span style='color:red;text-transform:uppercase'>"+ firstname + " " + lastname + "</span></h1>");
					out.println("<h2 style='text-align:center;margin-top:10px'>Your A/C Number: " + accountnumber+ "</h2>");
				}
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("BankFunctions.html");
				requestDispatcher.include(request, response);
			} 
			else {
				if (gender.equalsIgnoreCase("male")) {
					out.println("<h1 style='text-align:center;margin-top:10px'>Welcome Mr: <span style='color:red;text-transform:uppercase'>"+ firstname + " " + lastname + "</span></h1>");
					out.println("<h2 style='text-align:center;margin-top:10px'>Enter Valid OTP</h2>");
				} 
				else {
					out.println("<h1 style='text-align:center;margin-top:10px'>Welcome Miss: <span style='color:red;text-transform:uppercase'>"+ firstname + " " + lastname + "</span></h1>");
					out.println("<h2 style='text-align:center;margin-top:10px'>Ente Valid OTP</h2>");
				}
			}
		} 
		else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserLogin.html");
			requestDispatcher.include(request, response);
			out.println("<center><h3 style='color:red'>Session time out</h3></center>");
		}

	}

}
