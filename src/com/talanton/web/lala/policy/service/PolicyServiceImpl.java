package com.talanton.web.lala.policy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.talanton.web.lala.policy.dao.PolicyDAO;
import com.talanton.web.lala.policy.dao.PolicyDAOImpl;
import com.talanton.web.lala.policy.dto.Policy;
import com.talanton.web.lala.utils.jdbc.JdbcUtil;

public class PolicyServiceImpl implements PolicyService {	// 싱글톤으로 동작
	private static PolicyServiceImpl instance = null;
	public static PolicyServiceImpl getInstance() {
		if(instance == null) {
			instance = new PolicyServiceImpl();
		}
		return instance;
	}
	private PolicyServiceImpl() { }
	
	@Override
	public List<Policy> getList() {	// 정책 파라미터 목록을 가져온다.
		PolicyDAO dao = PolicyDAOImpl.getInstance();
		Connection conn = null;
		List<Policy> list = null;
		try {
			conn = JdbcUtil.getConnection();
			list = dao.getList(conn);	// 정책 파라미터 목록을 가져온다.
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return list;
	}
	
	// 정책 파라미터를 추가하거나 수정한다. 파라미터에 따라 코드값과 카테고리 이름이 고정된다.
	// 배송불가지역은 코드 10의 값을 가진다. - 복수 개이므로 배열을 사용한다.
	@Override
	public void modify(String shopping, String free, String[] posts, String subscription, String pursuit, String period,
			String dpc, String dmobile, String epc, String emobile) {
		PolicyDAO dao = PolicyDAOImpl.getInstance();
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			processing(dao, conn, 1, "배송정책", "기본배송료", shopping);
			processing(dao, conn, 2, "배송정책", "무료 배송", free);
			processing(dao, conn, 3, "포인트 정책", "가입 포인트", subscription);
			processing(dao, conn, 4, "포인트 정책", "구매 포인트", pursuit);
			processing(dao, conn, 5, "주문 취소 정책", "무통장 입금 시", period);
			processing(dao, conn, 6, "공통 배송 안내 등록", "PC", dpc);
			processing(dao, conn, 7, "공통 배송 안내 등록", "Mobile", dmobile);
			processing(dao, conn, 8, "공통 교환 및 반품 안내 등록", "PC", epc);
			processing(dao, conn, 9, "공통 교환 및 반품 안내 등록", "Mobile", emobile);
			processing2(dao, conn, 10, "배송정책", "배송불가 지역", posts);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	// 배송 불가 지역 처리
	private void processing2(PolicyDAO dao, Connection conn, int code, String category, String name, String[] values) throws SQLException {
		dao.removeAll(conn, code);
		if(values == null) {
			return;
		}
		Policy policy = new Policy();
		policy.setCode(code);
		policy.setCategory(category);
		policy.setName(name);
		for(String value : values) {
			policy.setValue(value);
			dao.add(conn, policy);
		}
	}
	
	// 정책 정보 변경 처리 : 파라미터에 대하여 공통으로 처리할 수 있도록 메소드화
	private void processing(PolicyDAO dao, Connection conn, int code, String category, String name, String value) throws SQLException {
		Policy newPolicy = new Policy();
		newPolicy.setCode(code);
		newPolicy.setCategory(category);
		newPolicy.setName(name);
		newPolicy.setValue(value);
		Policy oldPolicy = dao.getPolicy(conn, code);
		if(oldPolicy == null) {	// 신규 -> 추가
			dao.add(conn, newPolicy);
		} else {				// 기존 -> 수정
			dao.update(conn, newPolicy);
		}
	}
	
	// 분류(category)와 이름(name)에 의하여 정책관리 값을 가져온다.
	@Override
	public String getValue(String category, String name) {
		PolicyDAO dao = PolicyDAOImpl.getInstance();
		Connection conn = null;
		String value = null;
		try {
			conn = JdbcUtil.getConnection();
			value = dao.getValue(conn, category, name);	// 정책 파라미터 목록을 가져온다.
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return value;
	}
}