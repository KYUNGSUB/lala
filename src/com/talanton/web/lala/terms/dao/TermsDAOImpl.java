package com.talanton.web.lala.terms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.talanton.web.lala.terms.dto.Terms;

public class TermsDAOImpl implements TermsDAO {
	private static TermsDAOImpl instance = null;
	public static TermsDAOImpl getInstance() {
		if(instance == null) {
			instance = new TermsDAOImpl();
		}
		return instance;
	}
	private TermsDAOImpl() { }
	
	@Override
	public List<Terms> getList(Connection conn) throws SQLException {
		String sql = "select * from terms order by tid asc";
		List<Terms> list = new ArrayList<Terms>();
		try (Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);) {
			while(rs.next()) {
				Terms terms = makeTermsFromResultSet(rs);
				list.add(terms);
			}
		}
		return list;
	}
	
	private Terms makeTermsFromResultSet(ResultSet rs) throws SQLException {
		Terms terms = new Terms();
		terms.setTid(rs.getInt("tid"));
		terms.setTitle(rs.getString("title"));
		terms.setExpose(rs.getBoolean("expose"));
		terms.setMandatory(rs.getBoolean("mandatory"));
		terms.setContent(rs.getString("content"));
		terms.setCreatedAt(rs.getTimestamp("createdAt"));
		terms.setModifiedAt(rs.getTimestamp("modifiedAt"));
		return terms;
	}
	
	@Override
	public int insert(Connection conn, Terms term) throws SQLException {
		String sql = "insert into terms (title, content, expose, mandatory)"
				+ " values (?, ?, ?, ?)";
		int result = -1;
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, term.getTitle());
			pstmt.setString(2, term.getContent());
			pstmt.setBoolean(3, term.isExpose());
			pstmt.setBoolean(4, term.isMandatory());
			result = pstmt.executeUpdate();
		}
		return result;
	}
	
	@Override
	public int update(Connection conn, Terms term) throws SQLException {
		String sql = "update terms set title=?, content=?, expose=?, mandatory=?"
				+ " where tid=?";
		int result = -1;
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, term.getTitle());
			pstmt.setString(2, term.getContent());
			pstmt.setBoolean(3, term.isExpose());
			pstmt.setBoolean(4, term.isMandatory());
			pstmt.setInt(5, term.getTid());
			result = pstmt.executeUpdate();
		}
		return result;
	}
	
	@Override
	public int delete(Connection conn, int tid) throws SQLException {
		String sql = "delete from terms where tid=?";
		int result = -1;
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, tid);
			result = pstmt.executeUpdate();
		}
		return result;
	}
}