<%@page import="java.util.List"%>
<%@page import="com.ebanking.model.Statement"%>
<%@page import="com.ebanking.dao.StatementDao"%>
<%@page import="com.ebanking.dao.StatementDaoImpl"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bank Transactions</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: Helvetica;
}
body {
	background-color: lightgray;
	font-size: 18px;
	display: flex;
	justify-content: center;
	flex-direction: column;
	width: 100%;
}
table {
	border-collapse: collapse;
	width: 95%;
	text-align: center;
	background: #fff;
	box-shadow: 0 0 2px 2px #3C3D37;
	margin: 0 auto;
	margin-top: 20px;
	border: none;
}
table tr td, th {
	padding: 8px;
	border: 1px solid black;
}
table tr th {
	color: #fff;
	background: #1F4529;
	padding: 10px;
}
#lname {
	text-align: center;
	font-size: 45px
	font-weight: 1100;
	color: red;
	text-shadow: 1px 2px #000;
	text-transform: uppercase;
	margin-top: 15px;
	word-spacing: 2px;
}
a button {
	padding: 5px 10px;
	font-size: 17px;
	background: #FF0000;
	color: #fff;
	outline: none;
	border: none;
	border-radius: 5px;
}
</style>
</head>
<body>
	<%
	Integer userid = (Integer) session.getAttribute("uid");
	StatementDao dao = new StatementDaoImpl();
	List<Statement> list = dao.selectStatementByUserId(userid);
	String lastName = (String) session.getAttribute("lname");
	%>
	
	<h1 id="lname"><%=lastName%> Transactions</h1>
	
	<%
		String deleteStatement = (String) session.getAttribute("deleteStatement");
		if (deleteStatement!=null) {%>
			<h3 style="color:green;font-weight:bold; text-align:center"><%=deleteStatement%></h3>
		<% session.removeAttribute("deleteStatement");
		}
	%>
	
	<table border="2">
		<tr>
			<th>Status</th>
			<th>Date Of Transaction</th>
			<th>Type Of Payment</th>
			<th>Transaction Id</th>
			<th>Amount</th>
			<th>Transaction Time</th>
			<th>Remaining Balance</th>
			<th>Action</th>
		</tr>
		<%
		for (Statement statement : list) {
		%>
		<tr>
			<td><%=statement.getStatus()%></td>
			<td><%=statement.getDateoftransaction()%></td>
			<td><%=statement.getTypeofpayment()%></td>
			<td><%=statement.getTransactionid()%></td>
			<td><%=statement.getAmount()%></td>
			<td><%=statement.getTransactiontime()%></td>
			<td><%=statement.getRemainingbalance()%></td>
			<td><a href="deleteStatement?transactionid=<%=statement.getTransactionid() %>"><button>Delete</button></a></td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>
