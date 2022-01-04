package com.talanton.web.lala.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.talanton.web.lala.product.dto.ProductInfo;
import com.talanton.web.lala.utils.jdbc.JdbcUtil;

public class ProductInfoDAOImpl implements ProductInfoDAO {
	private static ProductInfoDAOImpl instance = null;
	public static ProductInfoDAOImpl getInstance() {
		if(instance == null) {
			instance = new ProductInfoDAOImpl();
		}
		return instance;
	}
	private ProductInfoDAOImpl() { }
	
	@Override
	public int insert(Connection conn, ProductInfo info) throws SQLException {
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			String query = "insert into product_info (name, description, pid) values (?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, info.getName());
			pstmt.setString(2, info.getDescription());
			pstmt.setInt(3, info.getPid());
			result = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}
	
	@Override
	public List<ProductInfo> select(Connection conn, int pid) throws SQLException {
		List<ProductInfo> result = null;
		ResultSet rs = null;
		String sql = "select * from product_info where pid=? order by pi_id";
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			pstmt.setInt(1, pid);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				result = Collections.emptyList();
			}
			else {
				List<ProductInfo> list = new ArrayList<ProductInfo>();
				do {
					ProductInfo info = makeProductInfoFromResultSet(rs);	// false
					list.add(info);
				} while (rs.next());
				result = list;
			}
		} finally {
			JdbcUtil.close(rs);
		}
		return result;
	}
	
	private ProductInfo makeProductInfoFromResultSet(ResultSet rs) throws SQLException {
		ProductInfo info = new ProductInfo();
		info.setPi_id(rs.getInt("pi_id"));
		info.setName(rs.getString("name"));
		info.setDescription(rs.getString("description"));
		info.setPid(rs.getInt("pid"));
		info.setCreatedAt(rs.getTimestamp("createdAt"));
		info.setModifiedAt(rs.getTimestamp("modifiedAt"));
		return info;
	}
	
	@Override
	public int delete(Connection conn, int pid) throws SQLException {
		int result = -1;
		String sql = "delete from product_info where pid=?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			pstmt.setInt(1, pid);
			result = pstmt.executeUpdate();
		}
		return result;
	}
}