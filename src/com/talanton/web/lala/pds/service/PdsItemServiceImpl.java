package com.talanton.web.lala.pds.service;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.talanton.web.lala.common.listener.CommonParameter;
import com.talanton.web.lala.common.parameter.Constants;
import com.talanton.web.lala.pds.dao.PdsItemDao;
import com.talanton.web.lala.pds.model.AddRequest;
import com.talanton.web.lala.pds.model.ListPdsItem;
import com.talanton.web.lala.pds.model.PdsItem;
import com.talanton.web.lala.utils.jdbc.JdbcUtil;

public class PdsItemServiceImpl implements PdsItemService {
	private static PdsItemServiceImpl instance = null;
	public static PdsItemServiceImpl getInstance() {
		if(instance == null) {
			instance = new PdsItemServiceImpl();
		}
		return instance;
	}
	private PdsItemServiceImpl() {
		count_per_page = Integer.valueOf((String)Constants.getParameter("article_per_page"));
	}
	
	private int count_per_page;
	
	@Override
	public PdsItem getPdsItem(String uuid) throws PdsItemNotFoundException {
		PdsItemDao pDao = new PdsItemDao();
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			PdsItem pdsItem = pDao.selectById(conn, uuid);
			if (pdsItem == null) {
				throw new PdsItemNotFoundException("해당 파일이 존재하지 않음 :" + uuid);
			}
			return pdsItem;
		} catch (SQLException e) {
			throw new RuntimeException("DB 처리 오류 : " + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("Naming 예외 처리 : " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public boolean increaseCount(String uuid) throws NamingException {
		Connection conn = null;
		PdsItemDao pDao = new PdsItemDao();
		try {
			conn = JdbcUtil.getConnection();
			int updatedCount = pDao.increaseCount(conn, uuid);
			return updatedCount == 0 ? false : true;
		} catch (SQLException e) {
			throw new RuntimeException("DB 처리 오류 : " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public int add(AddRequest request) {
		PdsItemDao pDao = new PdsItemDao();
		Connection conn = null;
		PdsItem pdsItem = null;
		int result = -1;
		try {
			conn = JdbcUtil.getConnection();
			pdsItem = request.toPdsItem();
			result = pDao.insert(conn, pdsItem);
			if (result == -1) {
				JdbcUtil.rollback(conn);
				throw new RuntimeException("DB 처리 오류");
			}
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (NamingException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	public ListPdsItem getPdsItemList(int pageNumber) throws NamingException {
		if (pageNumber < 0) {
			throw new IllegalArgumentException("page number < 0 : "	+ pageNumber);
		}
		PdsItemDao pDao = new PdsItemDao();
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			int totalArticleCount = pDao.selectCount(conn);

			if (totalArticleCount == 0) {
				return new ListPdsItem();
			}

			int totalPageCount = calculateTotalPageCount(totalArticleCount);

			int firstRow = (pageNumber - 1) * count_per_page + 1;
			int endRow = firstRow + count_per_page - 1;

			if (endRow > totalArticleCount) {
				endRow = totalArticleCount;
			}
			List<PdsItem> pList = pDao.select(conn, firstRow, endRow);

			ListPdsItem PdsItemListView = new ListPdsItem(
					pList, pageNumber, totalPageCount, firstRow, endRow);
			return PdsItemListView;
		} catch (SQLException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	private int calculateTotalPageCount(int totalPdsItemCount) {
		if (totalPdsItemCount == 0) {
			return 0;
		}
		int pageCount = totalPdsItemCount / count_per_page;
		if (totalPdsItemCount % count_per_page > 0) {
			pageCount++;
		}
		return pageCount;
	}
	
	public int getTotalPageNumber() throws NamingException {
		PdsItemDao pDao = new PdsItemDao();
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			int totalArticleCount = pDao.selectCount(conn);
			return calculateTotalPageCount(totalArticleCount);
		} catch (SQLException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public int modifyPdsItem(PdsItem pdsItem) throws NamingException {
		int result = -1;
		PdsItemDao pDao = new PdsItemDao();
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			result = pDao.updatePdsItem(conn, pdsItem);
		} catch (SQLException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	@Override
	public List<PdsItem> getPdsItemByGid(String fileType, int bid) throws SQLException, PdsItemNotFoundException {
		PdsItemDao pDao = new PdsItemDao();
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			List<PdsItem> list = pDao.selectByGid(conn, fileType, bid);
			if (list == null) {
				throw new PdsItemNotFoundException("해당 파일이 존재하지 않음 :" + bid);
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException("DB 처리 오류 : " + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("Naming 예외 처리 : " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	@Override
	public int removePdsItem(int gid) throws SQLException, NamingException {
		int result = -1;
		PdsItemDao pDao = new PdsItemDao();
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			result = pDao.deletePdsItem(conn, gid);
		} catch (SQLException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	@Override
	public int removePdsItemByUuid(PdsItem pds) throws SQLException, NamingException {
		int result = -1;
		PdsItemDao pDao = new PdsItemDao();
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			result = pDao.deletePdsItem(conn, pds.getUuid());	// 데이터베이스 삭제
			removeFile(pds);	// 파일 삭제
		} catch (SQLException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	private void removeFile(PdsItem pds) {
		CommonParameter cp = CommonParameter.getInstance();
		String upload_path = (String)cp.get("upload_path");
		File file = new File(upload_path, pds.getFullFileName());
		if(file.exists()) {
			file.delete();
		}
	}
}