package com.talanton.web.lala.product.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.talanton.web.lala.product.dto.ProductHistory;

public interface ProductHistoryDAO {
	int insert(Connection conn, ProductHistory history) throws SQLException;
	List<ProductHistory> select(Connection conn, int pid) throws SQLException;
}