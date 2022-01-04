package com.talanton.web.lala.product.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.talanton.web.lala.product.dto.Criteria;
import com.talanton.web.lala.product.dto.Product;

public interface ProductDAO {
	// 상품 추가
	int addProduct(Connection conn, Product product) throws SQLException;
	// 전체 상품 갯수 검색
	int selectCount(Connection conn) throws SQLException;
	// 검색 조건에 따른 상품 목록 검색
	List<Product> select(Connection conn, Criteria cri) throws SQLException;
	// 검색 조건에 따른 상품 갯수 검색
	int selectCriteriaTotalCount(Connection conn, Criteria cri) throws SQLException;
	// 상품 노출 여부 정보 수정(product 테이블)
	int updateExpose(Connection conn, int pid, String expose) throws SQLException;
	// 상품 정보 삭제
	int delete(Connection conn, int pid) throws SQLException;
	// 지정 상품 정보 검색
	Product getProduct(Connection conn, int pid) throws SQLException;
	// 지정 상품 정보 수정
	int updateProduct(Connection conn, Product product) throws SQLException;
}