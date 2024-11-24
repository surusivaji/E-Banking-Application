package com.ebanking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ebanking.dao.StatementDao;
import com.ebanking.dao.StatementDaoImpl;

@WebServlet("/deleteStatement")
public class DeleteStatement extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tempId = request.getParameter("transactionid");
		Integer transactionId = Integer.parseInt(tempId);
		StatementDao dao = new StatementDaoImpl();
		int deleteStatement = dao.deleteStatement(transactionId);
		HttpSession session = request.getSession();
		if (deleteStatement!=0) {
			session.setAttribute("deleteStatement", "statement removed successfully...!!!");
			response.sendRedirect("BankStatement.jsp");
		}
		else {
			session.setAttribute("deleteStatement", "Something Went Wrong On The Server");
			response.sendRedirect("BankStatement.jsp");
		}
	}

}
