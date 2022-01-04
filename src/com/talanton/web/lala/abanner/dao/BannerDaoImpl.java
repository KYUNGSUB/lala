package com.talanton.web.lala.abanner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.talanton.web.lala.abanner.model.Banner;
import com.talanton.web.lala.abanner.model.BannerInfo;
import com.talanton.web.lala.utils.jdbc.JdbcUtil;

public class BannerDaoImpl implements BannerDao {
	private static BannerDaoImpl instance = null;
	private BannerDaoImpl() { }
	public static BannerDaoImpl getInstance() {
		if(instance == null) {
			instance = new BannerDaoImpl();
		}
		return instance;
	}

	@Override
	public Banner getBanner(Connection conn, int kind, int position) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Banner banner = null;
		try {
			String query = "select * from banner where kind=? and position=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, kind);
			pstmt.setInt(2, position);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				banner = makeBannerFromResultSet(rs);
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return banner;
	}
	
	private Banner makeBannerFromResultSet(ResultSet rs) throws SQLException {
		Banner banner = new Banner();
		banner.setBid(rs.getInt("bid"));
		banner.setKind(rs.getInt("kind"));
		banner.setPosition(rs.getInt("position"));
		banner.setLocation(rs.getInt("location"));
		banner.setCreatedAt(rs.getTimestamp("createdAt"));
		banner.setModifiedAt(rs.getTimestamp("modifiedAt"));
		return banner;
	}
	
	@Override
	public int addBanner(Connection conn, Banner banner) throws SQLException {
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			String query = "insert into banner (kind, position, location) values (?, ?, ?)";
			pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, banner.getKind());
			pstmt.setInt(2, banner.getPosition());
			pstmt.setInt(3, banner.getLocation());
			pstmt.executeUpdate();
			ResultSet keys = pstmt.getGeneratedKeys();     
			keys.next();   
			result = keys.getInt(1);    // row id 값
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}
	
	@Override
	public int updateBanner(Connection conn, Banner banner) throws SQLException {
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			String query = "update banner set location=? where bid = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, banner.getLocation());
			pstmt.setInt(2, banner.getBid());
			result = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}
	
	@Override
	public int addBannerInfo(Connection conn, BannerInfo bannerInfo) throws SQLException {
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			String query = "insert into banner_info (bid, url, alt, target, loginBefore) values (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, bannerInfo.getBid());
			pstmt.setString(2, bannerInfo.getUrl());
			pstmt.setString(3, bannerInfo.getAlt());
			pstmt.setString(4, bannerInfo.getTarget());
			pstmt.setInt(5, bannerInfo.getLoginBefore());
			pstmt.executeUpdate();
			ResultSet keys = pstmt.getGeneratedKeys();     
			keys.next();   
			result = keys.getInt(1);    // row id 값
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}
	
	@Override
	public List<BannerInfo> getBannerInfoList(Connection conn, int bid) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BannerInfo> list = new ArrayList<BannerInfo>();
		try {
			String query = "select * from banner_info where bid = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BannerInfo bannerInfo = makeBannerInfoFromResultSet(rs);
				list.add(bannerInfo);
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return list;
	}
	
	private BannerInfo makeBannerInfoFromResultSet(ResultSet rs) throws SQLException {
		BannerInfo bannerInfo = new BannerInfo();
		bannerInfo.setInfo_id(rs.getInt("info_id"));
		bannerInfo.setBid(rs.getInt("bid"));
		bannerInfo.setUrl(rs.getString("url"));
		bannerInfo.setAlt(rs.getString("alt"));
		bannerInfo.setTarget(rs.getString("target"));
		bannerInfo.setLoginBefore(rs.getInt("loginBefore"));
		bannerInfo.setCreatedAt(rs.getTimestamp("createdAt"));
		bannerInfo.setModifiedAt(rs.getTimestamp("modifiedAt"));
		return bannerInfo;
	}
	
	@Override
	public int updateBannerInfo(Connection conn, BannerInfo bannerInfo) throws SQLException {
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			String query = "update banner_info set url=?, alt=?, target=?, loginBefore=? where info_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bannerInfo.getUrl());
			pstmt.setString(2, bannerInfo.getAlt());
			pstmt.setString(3, bannerInfo.getTarget());
			pstmt.setInt(4, bannerInfo.getLoginBefore());
			pstmt.setInt(5, bannerInfo.getInfo_id());
			result = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}
	
	@Override
	public int deleteBannerInfo(Connection conn, int info_id) throws SQLException {
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			String query = "delete from banner_info where info_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, info_id);
			result = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}
}