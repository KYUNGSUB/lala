package com.talanton.web.lala.abanner.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.talanton.web.lala.abanner.dao.BannerDao;
import com.talanton.web.lala.abanner.dao.BannerDaoImpl;
import com.talanton.web.lala.abanner.model.Banner;
import com.talanton.web.lala.abanner.model.BannerInfo;
import com.talanton.web.lala.pds.dao.PdsItemDao;
import com.talanton.web.lala.pds.model.PdsItem;
import com.talanton.web.lala.utils.jdbc.JdbcUtil;

public class ABannerServiceImpl implements ABannerService {
	private static ABannerServiceImpl instance = null;
	private ABannerServiceImpl() { }
	public static ABannerServiceImpl getInstance() {
		if(instance == null) {
			instance = new ABannerServiceImpl();
		}
		return instance;
	}
	
	@Override
	public Banner getBanner(int kind, int position) {
		Connection conn = null;
		Banner banner = null;
		try {
			conn = JdbcUtil.getConnection();
			BannerDao dao = BannerDaoImpl.getInstance();
			banner = dao.getBanner(conn, kind, position);
			if(banner != null) {
				banner.setInfoList(dao.getBannerInfoList(conn, banner.getBid()));
				PdsItemDao pdsDao = new PdsItemDao();
				for(BannerInfo bannerInfo : banner.getInfoList()) {
					List<PdsItem> list = pdsDao.selectByGid(conn, null, bannerInfo.getInfo_id());
					bannerInfo.setPds(list.get(0));
				}
			}
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return banner;
	}
	
	@Override
	public int addBanner(Banner banner) {
		Connection conn = null;
		int result = -1;
		try {
			conn = JdbcUtil.getConnection();
			BannerDao dao = BannerDaoImpl.getInstance();
			result = dao.addBanner(conn, banner);
		} catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	@Override
	public int updateBanner(Banner banner) {
		Connection conn = null;
		int result = -1;
		try {
			conn = JdbcUtil.getConnection();
			BannerDao dao = BannerDaoImpl.getInstance();
			result = dao.updateBanner(conn, banner);
		} catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	@Override
	public int addBannerInfo(BannerInfo bannerInfo) {
		Connection conn = null;
		int result = -1;
		try {
			conn = JdbcUtil.getConnection();
			BannerDao dao = BannerDaoImpl.getInstance();
			result = dao.addBannerInfo(conn, bannerInfo);
		} catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	@Override
	public Banner getBannerOnly(int kind, int position) {
		Connection conn = null;
		Banner banner = null;
		try {
			conn = JdbcUtil.getConnection();
			BannerDao dao = BannerDaoImpl.getInstance();
			banner = dao.getBanner(conn, kind, position);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		return banner;
	}
	
	@Override
	public int updateBannerInfo(BannerInfo bannerInfo) {
		Connection conn = null;
		int result = -1;
		try {
			conn = JdbcUtil.getConnection();
			BannerDao dao = BannerDaoImpl.getInstance();
			result = dao.updateBannerInfo(conn, bannerInfo);
		} catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	@Override
	public int removeBannerInfo(int info_id) {
		Connection conn = null;
		int result = -1;
		try {
			conn = JdbcUtil.getConnection();
			BannerDao dao = BannerDaoImpl.getInstance();
			result = dao.deleteBannerInfo(conn, info_id);
		} catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
}