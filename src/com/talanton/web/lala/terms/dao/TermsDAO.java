package com.talanton.web.lala.terms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.talanton.web.lala.terms.dto.Terms;

public interface TermsDAO {
	List<Terms> getList(Connection conn) throws SQLException;
	int insert(Connection conn, Terms term) throws SQLException;
	int update(Connection conn, Terms term) throws SQLException;
	int delete(Connection conn, int tid) throws SQLException;
}