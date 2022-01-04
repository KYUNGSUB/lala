package com.talanton.web.lala.abanner.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.talanton.web.lala.abanner.model.Banner;
import com.talanton.web.lala.abanner.model.BannerInfo;

public interface BannerDao {
	Banner getBanner(Connection conn, int kind, int position) throws SQLException;
	int addBanner(Connection conn, Banner banner) throws SQLException;
	int updateBanner(Connection conn, Banner banner) throws SQLException;
	int addBannerInfo(Connection conn, BannerInfo bannerInfo) throws SQLException;
	List<BannerInfo> getBannerInfoList(Connection conn, int bid) throws SQLException;
	int updateBannerInfo(Connection conn, BannerInfo bannerInfo) throws SQLException;
	int deleteBannerInfo(Connection conn, int info_id) throws SQLException;
}