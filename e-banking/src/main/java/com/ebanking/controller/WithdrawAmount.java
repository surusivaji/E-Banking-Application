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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ebanking.dao.BankFunctionsDao;
import com.ebanking.dao.BankFunctionsDaoImpl;
import com.ebanking.dao.StatementDao;
import com.ebanking.dao.StatementDaoImpl;
import com.ebanking.model.Statement;

public class WithdrawAmount extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String emailid = request.getParameter("email");
		String userAmount = request.getParameter("amount");
		double uAmount = Double.parseDouble(userAmount);
		
		HttpSession session = request.getSession();
		Double bankbalance = (Double) session.getAttribute("amount");
		String email = (String) session.getAttribute("mailid");
		Integer accountno = (Integer) session.getAttribute("accountno");
		Integer userid = (Integer) session.getAttribute("uid");
		
		BankFunctionsDao bankFunctionsDao = new BankFunctionsDaoImpl();
		
		if (uAmount>=0 && email.equals(emailid)) {
			if (uAmount<=bankbalance) {
				double total = bankbalance-uAmount;
				int result = bankFunctionsDao.updateAmount(total, email);
				if (result!=0) {
					Statement statement = new Statement();
					statement.setStatus("Debit");
					statement.setAmount(uAmount+"Dr");
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
					statement.setRemainingbalance(total+"rs");
					
					StatementDao statementDao = new StatementDaoImpl();
					int statementResult = statementDao.insertStatementDetails(statement);
					if (statementResult!=0) {
						RequestDispatcher requestDispatcher1 = request.getRequestDispatcher("BankFunctions.html");
						requestDispatcher1.include(request, response);
						out.println("<h3 style='color:green;margin-top:25px;font-weight:900;font-size:25px;text-align:center;font-family:Helvetica;'>Amount Debited Successfully...!!!</h3>");
					}
					else {RequestDispatcher requestDispatcher1 = request.getRequestDispatcher("WithdrawlAmount.html");
					requestDispatcher1.include(request, response);
					out.println("<h3 style='color:red;margin-top:25px;font-weight:600;text-align:center'>500 Error</h3>");	
					}
				}
				else {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("WithdrawlAmount.html");
					requestDispatcher.include(request, response);
					out.println("<h3 style='color:red;margin-top:25px;font-weight:600;text-align:center'>403 Error</h3>");
				}
			}
			else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WithdrawlAmount.html");
				requestDispatcher.include(request, response);
				out.println("<h3 style='color:red;margin-top:25px;font-weight:600;text-align:center'>Insufficient Amount</h3>");
			}
		}
		else if (uAmount>=0 && !email.equals(emailid)) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WithdrawlAmount.html");
			requestDispatcher.include(request, response);
			out.println("<h3 style='color:red;margin-top:25px;font-weight:600;text-align:center'>Invalid Email</h3>");
		}
		else if (uAmount<=0 && email.equals(emailid)){
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WithdrawlAmount.html");
			requestDispatcher.include(request, response);
			out.println("<h3 style='color:red;margin-top:25px;font-weight:600;text-align:center'>Invalid Amount</h3>");
		}
		else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WithdrawlAmount.html");
			requestDispatcher.include(request, response);
			out.println("<h3 style='color:red;margin-top:25px;font-weight:600;text-align:center'>Invalid Email And Amount</h3>");
		}
		
	}

}
