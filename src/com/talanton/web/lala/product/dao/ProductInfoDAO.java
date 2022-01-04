package com.talanton.web.lala.product.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.talanton.web.lala.product.dto.ProductInfo;

public interface ProductInfoDAO {
	int insert(Connection conn, ProductInfo info) throws SQLException;
	List<ProductInfo> select(Connection conn, int pid) throws SQLException;
	int delete(Connection conn, int pid) throws SQLException;
}