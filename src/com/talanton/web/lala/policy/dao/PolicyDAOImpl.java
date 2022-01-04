package com.talanton.web.lala.policy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.talanton.web.lala.policy.dto.Policy;
import com.talanton.web.lala.utils.jdbc.JdbcUtil;

public class PolicyDAOImpl implements PolicyDAO {	// 싱글톤으로 동작
	private static PolicyDAOImpl instance = null;
	public static PolicyDAOImpl getInstance() {
		if(instance == null) {
			instance = new PolicyDAOImpl();
		}
		return instance;
	}
	private PolicyDAOImpl() { }
	
	// 정책 파라미터 목록을 코드의 오름차순으로 정렬하여 가져온다.
	@Override
	public List<Policy> getList(Connection conn) throws SQLException {
		String sql = "select * from policy order by code asc";
		List<Policy> list = new ArrayList<Policy>();
		try (Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);) {
			while(rs.next()) {
				Policy policy = makeCategoryFromResultSet(rs);
				list.add(policy);
			}
		}
		return list;
	}

	// 정책 파라미터 열을 가져온다.
	private Policy makeCategoryFromResultSet(ResultSet rs) throws SQLException {
		Policy policy = new Policy();
		policy.setCode(rs.getInt("code"));
		policy.setCategory(rs.getString("category"));
		policy.setName(rs.getString("name"));
		policy.setValue(rs.getString("value"));
		policy.setCreatedAt(rs.getTimestamp("createdAt"));
		policy.setCreatedAt(rs.getTimestamp("modifiedAt"));
		return policy;
	}

	// 지정 코드의 정책 파라미터를 가져온다.
	@Override
	public Policy getPolicy(Connection conn, int code) throws SQLException {
		String sql = "select * from policy where code = ?";
		Policy policy = null;
		ResultSet rs = null;
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				policy = makeCategoryFromResultSet(rs);
			}
		} finally {
			JdbcUtil.close(rs);
		}
		return policy;
	}

	// 정책 파라미터를 추가
	@Override
	public int add(Connection conn, Policy policy) throws SQLException {
		String sql = "insert into policy (code, category, name, value) values(?, ?, ?, ?)";
		int result = -1;
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, policy.getCode());
			pstmt.setString(2, policy.getCategory());
			pstmt.setString(3, policy.getName());
			pstmt.setString(4, policy.getValue());
			result = pstmt.executeUpdate();
		}
		return result;
	}

	// 지정 코드의 정책 파라미터 값을 변경
	@Override
	public int update(Connection conn, Policy policy) throws SQLException {
		String sql = "update policy set value=? where code=?";
		int result = -1;
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, policy.getValue());
			pstmt.setInt(2, policy.getCode());
			result = pstmt.executeUpdate();
		}
		return result;
	}

	// 지정 코드를 가지는 정책 파라미터를 모두 삭제 : 보통 배송 불가 지역
	@Override
	public int removeAll(Connection conn, int code) throws SQLException {
		String sql = "delete from policy where code=?";
		int result = -1;
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, code);
			result = pstmt.executeUpdate();
		}
		return result;
	}
	
	@Override
	public String getValue(Connection conn, String category, String name) throws SQLException {
		String sql = "select value from policy where category=? and name=?";
		String value = null;
		ResultSet rs = null;
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			pstmt.setString(1, category);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				value = rs.getString(1);
			}
		} finally {
			JdbcUtil.close(rs);
		}
		return value;
	}
}