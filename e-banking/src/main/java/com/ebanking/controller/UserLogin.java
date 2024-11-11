package com.ebanking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ebanking.dao.UserDao;
import com.ebanking.dao.UserDaoImpl;
import com.ebanking.model.BankUserDetails;

public class UserLogin extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String username = request.getParameter("email");
		String password = request.getParameter("password");

		UserDao userDao = new UserDaoImpl();
		BankUserDetails bankuserDetails = userDao.userLogin(username, password);

		HttpSession session = request.getSession();

		if (bankuserDetails != null) {
			Random random = new Random();
			int otp = random.nextInt(10000);
			if (otp < 1000) {
				otp += 1000;
			}
			
			out.println("<h1 style='text-align:center;color:green;margin-bottom:30px;font-size:30px;font-family:Helventica;letter-spacing:1px;text-shadow:0px 2px 1px black;font-weight:900'>YOUR OTP : " + otp + "</h1>");
			
			session.setAttribute("gotp", otp);
			session.setMaxInactiveInterval(100);
			
			session.setAttribute("fname", bankuserDetails.getFirstname());
			session.setAttribute("lname", bankuserDetails.getLastname());
			session.setAttribute("gender",bankuserDetails.getGender());
			session.setAttribute("accountno", bankuserDetails.getAccountnumber());
			session.setAttribute("amount", bankuserDetails.getAmount());
			session.setAttribute("mailid", username);
			session.setAttribute("uid", bankuserDetails.getId());
			session.setAttribute("password", bankuserDetails.getPassword());

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("OTP.html");
			requestDispatcher.include(request, response);
		} 
		else {
			RequestDispatcher requestDispachter = request.getRequestDispatcher("UserLogin.html");
			requestDispachter.include(request, response);
			out.println("<center><h3 style='color:red'>Invalid Credientails</h3></center>");
		}

	}

}
