package com.talanton.web.lala.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.talanton.web.lala.product.dto.Criteria;
import com.talanton.web.lala.product.dto.Product;
import com.talanton.web.lala.utils.jdbc.JdbcUtil;

public class ProductDAOImpl implements ProductDAO {
	private static ProductDAOImpl instance = null;
	public static ProductDAOImpl getInstance() {
		if(instance == null) {
			instance = new ProductDAOImpl();
		}
		return instance;
	}
	private ProductDAOImpl() { }
	
	@Override
	public int addProduct(Connection conn, Product product) throws SQLException {
		String query = "insert into product (category1, category2, name, price, salePrice, maxPurchase, deposit, "
				+ "delivery, newp, best, discount, info, opt, userid, introduction, pc_detail, mobile_detail, pc_delivery,"
				+ "mobile_delivery, pc_exchange, mobile_exchange, memo, expose) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int result = -1;
		try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); ){
			pstmt.setString(1, product.getCategory1());
			pstmt.setString(2, product.getCategory2());
			pstmt.setString(3, product.getName());
			pstmt.setInt(4, product.getPrice());
			pstmt.setInt(5, product.getSalePrice());
			pstmt.setInt(6, product.getMaxPurchase());
			pstmt.setInt(7, product.getDeposit());
			pstmt.setInt(8, product.getDelivery());
			pstmt.setBoolean(9, product.isNewp());
			pstmt.setBoolean(10, product.isBest());
			pstmt.setBoolean(11, product.isDiscount());
			pstmt.setBoolean(12, product.isInfo());
			pstmt.setBoolean(13, product.isOpt());
			pstmt.setString(14, product.getUserid());
			pstmt.setString(15, product.getIntroduction());
			pstmt.setString(16, product.getPc_detail());
			pstmt.setString(17, product.getMobile_detail());
			pstmt.setString(18, product.getPc_delivery());
			pstmt.setString(19, product.getMobile_delivery());
			pstmt.setString(20, product.getPc_exchange());
			pstmt.setString(21, product.getMobile_exchange());
			pstmt.setString(22, product.getMemo());
			pstmt.setString(23, product.getExpose());
			pstmt.executeUpdate();
			ResultSet keys = pstmt.getGeneratedKeys();     
			keys.next();   
			result = keys.getInt(1);    // row id 값
		}
		return result;
	}
	
	private Product makeProductFromResultSet(ResultSet rs) throws SQLException {
		Product product = new Product();
		product.setPid(rs.getInt("pid"));
		product.setCategory1(rs.getString("category1"));
		product.setCategory2(rs.getString("category2"));
		product.setName(rs.getString("name"));
		product.setPrice(rs.getInt("price"));
		product.setSalePrice(rs.getInt("salePrice"));
		product.setMaxPurchase(rs.getInt("maxPurchase"));
		product.setDeposit(rs.getInt("deposit"));
		product.setDelivery(rs.getInt("delivery"));
		product.setNewp(rs.getBoolean("newp"));
		product.setBest(rs.getBoolean("best"));
		product.setDiscount(rs.getBoolean("discount"));
		product.setInfo(rs.getBoolean("info"));
		product.setOpt(rs.getBoolean("opt"));
		product.setUserid(rs.getString("userid"));
		product.setReadCount(rs.getInt("readCount"));
		product.setIntroduction(rs.getString("introduction"));
		product.setPc_detail(rs.getString("pc_detail"));
		product.setMobile_detail(rs.getString("mobile_detail"));
		product.setPc_delivery(rs.getString("pc_delivery"));
		product.setMobile_delivery(rs.getString("mobile_delivery"));
		product.setPc_exchange(rs.getString("pc_exchange"));
		product.setMobile_exchange(rs.getString("mobile_exchange"));
		product.setMemo(rs.getString("memo"));
		product.setExpose(rs.getString("expose"));
		product.setCreatedAt(rs.getTimestamp("createdAt"));
		product.setModifiedAt(rs.getTimestamp("modifiedAt"));
		return product;
	}

	// 전체 상품 갯수 검색
	@Override
	public int selectCount(Connection conn) throws SQLException {
		int count = 0;
		try (Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select count(*) from product"); ) {
			rs.next();
			count = rs.getInt(1);
		}
		return count;
	}

	// 검색조건을 만족하는 상품 목록 검색 (페이징 포함)
	@Override
	public List<Product> select(Connection conn, Criteria cri) throws SQLException {
		ResultSet rs = null;
		List<Product> result = null;
		String sql = "select * from product " + makeWhereFromCriteria(cri) +
			" order by createdAt desc limit ?, ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, cri.getPageStart());
			pstmt.setInt(2, cri.getAmount());
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				result = Collections.emptyList();
			}
			else {
				List<Product> list = new ArrayList<Product>();
				do {
					Product product = makeProductFromResultSet(rs);	// false
					list.add(product);
				} while (rs.next());
				result = list;
			}
		} finally {
			JdbcUtil.close(rs);
		}
		return result;
	}

	// 검색 조건을 만족하는 상품 갯수 검색
	@Override
	public int selectCriteriaTotalCount(Connection conn, Criteria cri) throws SQLException {
		int count = -1;
		String sql = "select count(*) from product " + makeWhereFromCriteria(cri);
		try (Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql); ) {
			if (rs.next()) {
				count = rs.getInt(1);
			}
		}
		return count;
	}
	
	// 검색조건에 따른 SQL where절 생성
	private String makeWhereFromCriteria(Criteria cri) {
		if(cri.getType() == null) {	// 전체 검색
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("where ");
		if(cri.getType().equals("name")) {
			sb.append("name like '%");	// 상품명
		} else {
			sb.append("userid like '%");	// 등록한 사람
		}
		sb.append(cri.getKeyword());
		sb.append("%'");
		if(cri.getDetail().equals("yes")) {
			sb.append(" and category1='");
			sb.append(cri.getCategory1());
			sb.append("' and category2='");
			sb.append(cri.getCategory2());
			sb.append("' and (salePrice between ");
			sb.append(cri.getPriceFrom());
			sb.append(" and ");
			sb.append(cri.getPriceTo());
			sb.append(") and (date(createdAt) between '");
			sb.append(cri.getRegFrom());
			sb.append("' and '");
			sb.append(cri.getRegTo());
			sb.append("') and (");
			for(int i = 0;i < cri.getExpose().length;i++) {
				String expose = cri.getExpose()[i];
				if(i > 0) {
					sb.append(" or ");
				}
				sb.append("expose='");
				sb.append(expose);
				sb.append("'");
			}
			sb.append(")");
		}
		return sb.toString();
	}
	
	@Override
	public int updateExpose(Connection conn, int pid, String expose) throws SQLException {
		int result = -1;
		String sql = "update product set expose=? where pid=?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			pstmt.setString(1, expose);
			pstmt.setInt(2, pid);
			result = pstmt.executeUpdate();
		}
		return result;
	}
	
	@Override
	public int delete(Connection conn, int pid) throws SQLException {
		int result = -1;
		String sql = "delete from product where pid=?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			pstmt.setInt(1, pid);
			result = pstmt.executeUpdate();
		}
		return result;
	}
	
	@Override
	public Product getProduct(Connection conn, int pid) throws SQLException {
		ResultSet rs = null;
		Product product = null;
		String sql = "select * from product where pid=?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, pid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				product = makeProductFromResultSet(rs);
			}
		} finally {
			JdbcUtil.close(rs);
		}
		return product;
	}
	
	@Override
	public int updateProduct(Connection conn, Product product) throws SQLException {
		String query = "update product set category1=?, category2=?, name=?, price=?, salePrice=?, maxPurchase=?, deposit=?, "
				+ "delivery=?, newp=?, best=?, discount=?, info=?, opt=?, userid=?, introduction=?, pc_detail=?, mobile_detail=?, pc_delivery=?,"
				+ "mobile_delivery=?, pc_exchange=?, mobile_exchange=?, memo=?, expose=? where pid=?";
		int result = -1;
		try (PreparedStatement pstmt = conn.prepareStatement(query); ){
			pstmt.setString(1, product.getCategory1());
			pstmt.setString(2, product.getCategory2());
			pstmt.setString(3, product.getName());
			pstmt.setInt(4, product.getPrice());
			pstmt.setInt(5, product.getSalePrice());
			pstmt.setInt(6, product.getMaxPurchase());
			pstmt.setInt(7, product.getDeposit());
			pstmt.setInt(8, product.getDelivery());
			pstmt.setBoolean(9, product.isNewp());
			pstmt.setBoolean(10, product.isBest());
			pstmt.setBoolean(11, product.isDiscount());
			pstmt.setBoolean(12, product.isInfo());
			pstmt.setBoolean(13, product.isOpt());
			pstmt.setString(14, product.getUserid());
			pstmt.setString(15, product.getIntroduction());
			pstmt.setString(16, product.getPc_detail());
			pstmt.setString(17, product.getMobile_detail());
			pstmt.setString(18, product.getPc_delivery());
			pstmt.setString(19, product.getMobile_delivery());
			pstmt.setString(20, product.getPc_exchange());
			pstmt.setString(21, product.getMobile_exchange());
			pstmt.setString(22, product.getMemo());
			pstmt.setString(23, product.getExpose());
			pstmt.setInt(24, product.getPid());
			result = pstmt.executeUpdate();
		}
		return result;
	}
}