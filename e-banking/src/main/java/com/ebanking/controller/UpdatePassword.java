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

import com.ebanking.dao.UserDao;
import com.ebanking.dao.UserDaoImpl;

@WebServlet("/updatepassword")
public class UpdatePassword extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");
		
		String updatedPassword = request.getParameter("newpassword");
		
		HttpSession session = request.getSession();
		String emailid = (String) session.getAttribute("mailid");
		
		UserDao dao = new UserDaoImpl();
		int result = dao.updatePassword(updatedPassword, emailid);
		if (result!=0) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("ChangePassword.html");
			dispatcher.include(request, response);
			writer.println("<h3 style='color:red;text-align:center;font-weight:600'>Password Updated Successfully...!!!</h3>");
		}
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("ChangePassword.html");
			dispatcher.include(request, response);
			writer.println("<h3 style='color:red;text-align:center;font-weight:600'>Invalid Password...!!!</h3>");
		}
		
		
		
	}

}
