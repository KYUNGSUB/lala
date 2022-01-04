package com.talanton.web.lala.policy.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.talanton.web.lala.policy.dto.Policy;

public interface PolicyDAO {
	// 정책 마라미터 목록 정보를 가져온다.
	List<Policy> getList(Connection conn) throws SQLException;
	// 하나의 코드에 해당하는 정책 파라미터 값을 가져온다.
	Policy getPolicy(Connection conn, int code) throws SQLException;
	// 정책 파라미터를 추가
	int add(Connection conn, Policy policy) throws SQLException;
	// 정책 파라미터의 값을 변경
	int update(Connection conn, Policy policy) throws SQLException;
	// 지정 코드의 값을 가지는 모든 정책 파라미터의 값을 삭제한다.
	// 배송불가지역의 변경은 이전에 저장된 값을 모두 삭제한 후 다시 추가하여 진행
	int removeAll(Connection conn, int code) throws SQLException;
	String getValue(Connection conn, String category, String name) throws SQLException;
}