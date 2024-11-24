package com.ebanking.dao;

import java.util.List;

import com.ebanking.model.Statement;

public interface StatementDao {
	
	int insertStatementDetails(Statement statement);
	List<Statement> selectStatementByUserId(int userid);
	int deleteStatement(int transactionid);

}
