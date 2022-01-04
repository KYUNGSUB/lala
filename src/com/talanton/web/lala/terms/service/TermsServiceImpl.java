package com.talanton.web.lala.terms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.talanton.web.lala.terms.dao.TermsDAO;
import com.talanton.web.lala.terms.dao.TermsDAOImpl;
import com.talanton.web.lala.terms.dto.Terms;
import com.talanton.web.lala.utils.jdbc.JdbcUtil;

public class TermsServiceImpl implements TermsService {
	private static TermsServiceImpl instance = null;
	public static TermsServiceImpl getInstance() {
		if(instance == null) {
			instance = new TermsServiceImpl();
		}
		return instance;
	}
	private TermsServiceImpl() { }
	
	@Override
	public List<Terms> getList() {
		TermsDAO dao = TermsDAOImpl.getInstance();
		Connection conn = null;
		List<Terms> list = null;
		try {
			conn = JdbcUtil.getConnection();
			list = dao.getList(conn);	// 약관 목록을 가져온다.
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
	
	@Override
	public int add(Terms term) {
		TermsDAO dao = TermsDAOImpl.getInstance();
		Connection conn = null;
		int result = -1;
		try {
			conn = JdbcUtil.getConnection();
			result = dao.insert(conn, term);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	@Override
	public int modify(Terms term) {
		TermsDAO dao = TermsDAOImpl.getInstance();
		Connection conn = null;
		int result = -1;
		try {
			conn = JdbcUtil.getConnection();
			result = dao.update(conn, term);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	@Override
	public int remove(int tid) {
		TermsDAO dao = TermsDAOImpl.getInstance();
		Connection conn = null;
		int result = -1;
		try {
			conn = JdbcUtil.getConnection();
			result = dao.delete(conn, tid);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
}