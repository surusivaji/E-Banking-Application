package com.ebanking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ebanking.dao.BankFunctionsDao;
import com.ebanking.dao.BankFunctionsDaoImpl;
import com.ebanking.dao.StatementDao;
import com.ebanking.dao.StatementDaoImpl;
import com.ebanking.model.Statement;

@WebServlet("/creditAmount")
public class CreditAmount extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String amount = request.getParameter("credit");
		double creditAmount = Double.parseDouble(amount);
		
		HttpSession session = request.getSession();
		Double bankBalance = (Double) session.getAttribute("amount");
		String emailid = (String) session.getAttribute("mailid");
		Integer accountno = (Integer) session.getAttribute("accountno");
		Integer userid = (Integer) session.getAttribute("uid");
		
		
		BankFunctionsDao bankFunctionsDao = new BankFunctionsDaoImpl();
		
		if (creditAmount>=0) {
			double totalBalance = creditAmount+bankBalance;
			int result = bankFunctionsDao.updateAmount(totalBalance, emailid);
			if (result!=0) {
				Statement statement = new Statement();
				statement.setStatus("Credit");
				statement.setAmount(creditAmount+"cr");
				statement.setBankaccountnumber(accountno);
				statement.setDateoftransaction(Date.valueOf(LocalDate.now()));
				statement.setTransactiontime(Time.valueOf(LocalTime.now()));
				Random random = new Random();
				int tranactionid = random.nextInt(1000000);
				if (tranactionid<100000) {
					tranactionid += 1000000;
				}
				statement.setTransactionid(tranactionid);
				statement.setTypeofpayment("Online");
				statement.setUserid(userid);
				statement.setRemainingbalance(totalBalance+"rs");
				
				StatementDao statementDao = new StatementDaoImpl();
				int statementResult = statementDao.insertStatementDetails(statement);
				if (statementResult!=0) {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("BankFunctions.html");
					requestDispatcher.include(request, response);
					out.println("<h3 style='color:green;text-align:center;margin-top:20px;font-size:25px;font-family:Helvetica;'>Amount Credited Successfully...!!!</h3>");
				}
				else {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("CreditAmount.html");
					requestDispatcher.include(request, response);
					out.println("<h3 style='color:red;text-align:center;margin-top:20px'>500 Error</h3>");
				}
			}
			else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("CreditAmount.html");
				requestDispatcher.include(request, response);
				out.println("<h3 style='color:red;text-align:center;margin-top:20px'>403 Error</h3>");
			}
		}
		else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("CreditAmount.html");
			requestDispatcher.include(request, response);
			out.println("<h3 style='color:red;text-align:center;margin-top:20px'>Invalid Amount</h3>");
		}
		
	}
	

}
