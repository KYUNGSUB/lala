package com.talanton.web.lala.category.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.talanton.web.lala.category.model.Category;
import com.talanton.web.lala.category.model.OptionInfo;
import com.talanton.web.lala.utils.jdbc.JdbcUtil;

public class CategoryDAOImpl implements CategoryDAO {
	private static CategoryDAOImpl instance = null;
	public static CategoryDAOImpl getInstance() {
		if(instance == null) {
			instance = new CategoryDAOImpl();
		}
		return instance;
	}
	private CategoryDAOImpl() { }
	
	@Override
	public List<Category> getListFirst(Connection conn) throws SQLException {
		String sql = "select * from category where step = 1 order by seq asc";
		List<Category> list = new ArrayList<Category>();
		try (Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);) {
			while(rs.next()) {
				Category category = makeCategoryFromResultSet(rs);
				list.add(category);
			}
		}
		return list;
	}
	
	private Category makeCategoryFromResultSet(ResultSet rs) throws SQLException {
		Category category = new Category();
		category.setName(rs.getString("name"));
		category.setCode(rs.getString("code"));
		category.setExpose(rs.getBoolean("expose"));
		category.setGnb(rs.getBoolean("gnb"));
		category.setStep(rs.getInt("step"));
		category.setSeq(rs.getInt("seq"));
		category.setParent(rs.getString("parent"));
		category.setCreatedAt(rs.getTimestamp("createdAt"));
		category.setModifiedAt(rs.getTimestamp("modifiedAt"));
		return category;
	}
	
	@Override
	public List<Category> getListSecond(Connection conn, String code) throws SQLException {
		String sql = "select * from category where step = 2 and parent = ? order by seq asc";
		ResultSet rs = null;
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				return Collections.emptyList();
			}
			List<Category> list = new ArrayList<Category>();
			do {
				Category category = makeCategoryFromResultSet(rs);
				list.add(category);
			}
			while(rs.next());
			return list;
		} finally {
			JdbcUtil.close(rs);
		}
	}
	
	@Override
	public Category selectByCode(Connection conn, String code) throws SQLException {
		String sql = "select * from category where code = ?";
		ResultSet rs = null;
		Category result = null;
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = makeCategoryFromResultSet(rs);
			}
		} finally {
			JdbcUtil.close(rs);
		}
		return result;
	}
	
	@Override
	public int insert(Connection conn, Category category) throws SQLException {
		String sql = "insert into category (name, code, expose, gnb, step, seq, parent)"
				+ " values (?, ?, ?, ?, ?, ?, ?)";
		int result = -1;
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, category.getName());
			pstmt.setString(2, category.getCode());
			pstmt.setBoolean(3, category.isExpose());
			pstmt.setBoolean(4, category.isGnb());
			pstmt.setInt(5, category.getStep());
			pstmt.setInt(6, category.getSeq());
			pstmt.setString(7, category.getParent());
			result = pstmt.executeUpdate();
		}
		return result;
	}
	
	// 배치 순서를 사용하여 이동할 상대 카테고리 정보를 검색 : 현재 카테고리가 1차 일 경우
	@Override
	public Category getFirstCategoryBySeq(Connection conn, int seq) throws SQLException {
		String sql = "select * from category where step=1 and seq=?";
		Category category = null;
		ResultSet rs = null;
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				category = makeCategoryFromResultSet(rs);
			}
		} finally {
			JdbcUtil.close(rs);
		}
		return category;
	}
	
	// 카테고리의 배치 순서(seq) 값을 변경
	@Override
	public int updateCategorySeq(Connection conn, String code, int seq) throws SQLException {
		String sql = "update category set seq = ? where code = ?";
		int result = -1;
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, seq);
			pstmt.setString(2, code);
			result = pstmt.executeUpdate();
		}
		return result;
	}

	// 1차 카테고리 코드(parent)와 seq를 사용하여 상대 카테고리(현재 2차)를 검색
	@Override
	public Category getSecondCategoryBySeq(Connection conn, String parent, int seq) throws SQLException {
		String sql = "select * from category where step = 2 and parent = ? and seq = ?";
		Category category = null;
		ResultSet rs = null;
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			pstmt.setString(1, parent);
			pstmt.setInt(2, seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				category = makeCategoryFromResultSet(rs);
			}
		} finally {
			JdbcUtil.close(rs);
		}
		return category;
	}
	
	// 2차 카테고리 정보를 수정 : 노출 여부(expose)
	@Override
	public int updateCategory(Connection conn, String code, boolean expose) throws SQLException {
		int result = -1;
		String sql;
		sql = "update category set expose = ? where code = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setBoolean(1, expose);
			pstmt.setString(2, code);
			result = pstmt.executeUpdate();
		}
		return result;
	}
	
	// 1차 카테고리 정보 수정 : 노출 여부(expose), GNB 표시 여부(gnb)
	@Override
	public int updateCategory(Connection conn, String code, boolean expose, boolean gnb) throws SQLException {
		int result = -1;
		String sql = "update category set expose = ?, gnb = ? where code = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setBoolean(1, expose);
			pstmt.setBoolean(2, gnb);
			pstmt.setString(3, code);
			result = pstmt.executeUpdate();
		}
		return result;
	}
	
	// 지정 카테고리 코드를 가지는 카테고리 정보를 삭제
	@Override
	public int deleteCategory(Connection conn, String code) throws SQLException {
		int result = -1;
		String sql = "delete from category where code = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, code);
			result = pstmt.executeUpdate();
		}
		return result;
	}
	
	// 카테고리 노출 여부(expose)와 GNB 노출 여부(gnb)를 데이터베이스로부터 가져온다.
	@Override
	public OptionInfo getOptionInfo(Connection conn, String code) throws SQLException {
		String sql = "select expose, gnb from category where code=?";
		OptionInfo info = null;
		ResultSet rs = null;
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boolean expose = rs.getBoolean("expose");
				boolean gnb = rs.getBoolean("gnb");
				info = new OptionInfo(expose, gnb);
			}
		} finally {
			JdbcUtil.close(rs);
		}
		return info;
	}
	
	// 카테고리 정보의 삭제로 영향을 받는 카테고리들의 배치 순서를 수정
	// 삭제한 카테고리 배치순서보다 큰 값을 가지는 배치순서(seq)를 가지는 카테고리들의 배치순서를 1 감소
	@Override
	public int updateSequence(Connection conn, String parent, int seq) throws SQLException {
		int result = -1;
		if(parent == null) {	// 1차 카테고리
			String sql = "update category set seq=seq-1 where step=1 and seq>?";
			try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
				pstmt.setInt(1, seq);
				result = pstmt.executeUpdate();
			}
		} else {				// 2차 카테고리
			String sql = "update category set seq=seq-1 where parent=? and seq>?";
			try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
				pstmt.setString(1, parent);
				pstmt.setInt(2, seq);
				result = pstmt.executeUpdate();
			}
		}
		return result;
	}
	
	// 1차 카테고리의 삭제로 산하 2차 카테고리들을 삭제
	@Override
	public int deleteSecondCategory(Connection conn, String parent) throws SQLException {
		int result = -1;
		String sql = "delete from category where parent = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, parent);
			result = pstmt.executeUpdate();
		}
		return result;
	}
	
	@Override
	public int getSeq(Connection conn, int step, String parent) throws SQLException {
		if(parent == null) {
			String sql = "select count(*) from category where step = ?";
			int seq = 0;
			ResultSet rs = null;
			try (PreparedStatement pstmt = conn.prepareStatement(sql); ) {
				pstmt.setInt(1, step);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					seq = rs.getInt(1);
				}
			} finally {
				JdbcUtil.close(rs);
			}
			return seq;
		} else {
			String sql = "select count(*) from category where step = ? and parent = ?";
			int seq = 0;
			ResultSet rs = null;
			try (PreparedStatement pstmt = conn.prepareStatement(sql); ) {
				pstmt.setInt(1, step);
				pstmt.setString(2, parent);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					seq = rs.getInt(1);
				}
			} finally {
				JdbcUtil.close(rs);
			}
			return seq;
		}
	}
	
	@Override
	public int[] getMaxCount(Connection conn, String parent) throws SQLException {
		String sql = "select max(substr(code,4,3)), count(code) from category where parent = ?";
		int[] result = new int[2];
		ResultSet rs = null;
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			pstmt.setString(1, parent);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result[0] = rs.getInt(1);
				result[1] = rs.getInt(2);
			}
		} finally {
			JdbcUtil.close(rs);
		}
		return result;
	}
	
	@Override
	public String[] getCodes(Connection conn, String parent, int count) throws SQLException {
		String sql = "select substr(code,4,3) from category where parent = ?";
		String[] result = new String[count];
		ResultSet rs = null;
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			pstmt.setString(1, parent);
			rs = pstmt.executeQuery();
			for(int i = 0;rs.next();i++) {
				result[i] = rs.getString(1);
			}
		} finally {
			JdbcUtil.close(rs);
		}
		return result;
	}
}