package com.talanton.web.lala.product.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.talanton.web.lala.product.dto.ProductOption;

public interface ProductOptionDAO {
	int insert(Connection conn, ProductOption option) throws SQLException;
	List<ProductOption> select(Connection conn, int pid) throws SQLException;
	int delete(Connection conn, int pid) throws SQLException;
}