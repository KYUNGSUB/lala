package com.talanton.web.lala.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.talanton.web.lala.product.dto.ProductOption;
import com.talanton.web.lala.utils.jdbc.JdbcUtil;

public class ProductOptionDAOImpl implements ProductOptionDAO {
	private static ProductOptionDAOImpl instance = null;
	public static ProductOptionDAOImpl getInstance() {
		if(instance == null) {
			instance = new ProductOptionDAOImpl();
		}
		return instance;
	}
	private ProductOptionDAOImpl() { }
	
	@Override
	public int insert(Connection conn, ProductOption option) throws SQLException {
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			String query = "insert into product_option (gid, name, description, price, pid) values (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, option.getGid());
			pstmt.setString(2, option.getName());
			pstmt.setString(3, option.getDescription());
			pstmt.setInt(4, option.getPrice());
			pstmt.setInt(5, option.getPid());
			result = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}
	
	@Override
	public List<ProductOption> select(Connection conn, int pid) throws SQLException {
		List<ProductOption> result = null;
		ResultSet rs = null;
		String sql = "select * from product_option where pid=? order by po_id";
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			pstmt.setInt(1, pid);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				result = Collections.emptyList();
			}
			else {
				List<ProductOption> list = new ArrayList<ProductOption>();
				do {
					ProductOption option = makeProductOptionFromResultSet(rs);	// false
					list.add(option);
				} while (rs.next());
				result = list;
			}
		} finally {
			JdbcUtil.close(rs);
		}
		return result;
	}
	
	private ProductOption makeProductOptionFromResultSet(ResultSet rs) throws SQLException {
		ProductOption option = new ProductOption();
		option.setPo_id(rs.getInt("po_id"));
		option.setGid(rs.getInt("gid"));
		option.setName(rs.getString("name"));
		option.setDescription(rs.getString("description"));
		option.setPrice(rs.getInt("price"));
		option.setPid(rs.getInt("pid"));
		option.setCreatedAt(rs.getTimestamp("createdAt"));
		option.setModifiedAt(rs.getTimestamp("modifiedAt"));
		return option;
	}
	
	@Override
	public int delete(Connection conn, int pid) throws SQLException {
		int result = -1;
		String sql = "delete from product_option where pid=?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			pstmt.setInt(1, pid);
			result = pstmt.executeUpdate();
		}
		return result;
	}
}