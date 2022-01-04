package com.talanton.web.lala.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.talanton.web.lala.product.dto.ProductHistory;
import com.talanton.web.lala.utils.jdbc.JdbcUtil;

public class ProductHistoryDAOImpl implements ProductHistoryDAO {
	private static ProductHistoryDAOImpl instance = null;
	public static ProductHistoryDAOImpl getInstance() {
		if(instance == null) {
			instance = new ProductHistoryDAOImpl();
		}
		return instance;
	}
	private ProductHistoryDAOImpl() { }
	
	@Override
	public int insert(Connection conn, ProductHistory history) throws SQLException {
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			String query = "insert into product_history (item, userid, pid) values (?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, history.getItem());
			pstmt.setString(2, history.getUserid());
			pstmt.setInt(3, history.getPid());
			result = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}
	
	@Override
	public List<ProductHistory> select(Connection conn, int pid) throws SQLException {
		List<ProductHistory> result = null;
		ResultSet rs = null;
		String sql = "select * from product_history where pid=? order by hid";
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			pstmt.setInt(1, pid);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				result = Collections.emptyList();
			}
			else {
				List<ProductHistory> list = new ArrayList<ProductHistory>();
				do {
					ProductHistory history = makeProductHistoryFromResultSet(rs);	// false
					list.add(history);
				} while (rs.next());
				result = list;
			}
		} finally {
			JdbcUtil.close(rs);
		}
		return result;
	}
	
	private ProductHistory makeProductHistoryFromResultSet(ResultSet rs) throws SQLException {
		ProductHistory history = new ProductHistory();
		history.setHid(rs.getInt("hid"));
		history.setItem(rs.getInt("item"));
		history.setTimeAt(rs.getTimestamp("timeAt"));
		history.setUserid(rs.getString("userid"));
		history.setPid(rs.getInt("pid"));
		return history;
	}
}