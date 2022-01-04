package com.talanton.web.lala.pds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.talanton.web.lala.pds.model.PdsItem;
import com.talanton.web.lala.utils.jdbc.JdbcUtil;

public class PdsItemDao {
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from pds_item");
			rs.next();
			return rs.getInt(1);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	public List<PdsItem> select(Connection conn, int firstRow, int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * "
					+ "from pds_item order by createdAt limit ?, ?");
			
			pstmt.setInt(1, firstRow - 1);
			pstmt.setInt(2, endRow - firstRow + 1);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				return Collections.emptyList();
			}
			List<PdsItem> itemList = new ArrayList<PdsItem>();
			do {
				PdsItem article = makeItemFromResultSet(rs);
				itemList.add(article);
			} while (rs.next());
			return itemList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private PdsItem makeItemFromResultSet(ResultSet rs) throws SQLException {
		PdsItem item = new PdsItem();
		item.setUuid(rs.getString("uuid"));
		item.setUploadPath(rs.getString("uploadPath"));
		item.setFileName(rs.getString("fileName"));
		item.setFileType(rs.getString("fileType"));
		item.setFileSize(rs.getLong("fileSize"));
		item.setDcount(rs.getInt("dcount"));
		item.setGid(rs.getInt("gid"));
		item.setCreatedAt(rs.getTimestamp("createdAt"));
		item.setModifiedAt(rs.getTimestamp("modifiedAt"));
		return item;
	}

	public PdsItem selectById(Connection conn, String uuid) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from pds_item "
					+ "where uuid = ?");
			pstmt.setString(1, uuid);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			PdsItem item = makeItemFromResultSet(rs);
			return item;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int insert(Connection conn, PdsItem item) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		int insertedCount = -1;
		try {
			pstmt = conn.prepareStatement("insert into pds_item "
					+ "(uuid, uploadPath, fileName, fileType, fileSize, gid) "
					+ "values (?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, item.getUuid());
			pstmt.setString(2, item.getUploadPath());
			pstmt.setString(3, item.getFileName());
			pstmt.setString(4, item.getFileType());
			pstmt.setLong(5, item.getFileSize());
			pstmt.setInt(6, item.getGid());
			insertedCount = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
		return insertedCount;
	}

	public int increaseCount(Connection conn, String uuid) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update pds_item set dcount = dcount + 1 where uuid = ?");
			pstmt.setString(1, uuid);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public List<PdsItem> selectByFilename(Connection conn, String fileName) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from pds_item "
					+ "where fileName like ?");
			pstmt.setString(1, "%" + fileName + "%");
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				return Collections.emptyList();
			}
			List<PdsItem> itemList = new ArrayList<PdsItem>();
			do {
				PdsItem article = makeItemFromResultSet(rs);
				itemList.add(article);
			} while (rs.next());
			return itemList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<PdsItem> selectByGid(Connection conn, String fileType, int gid) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if(fileType == null) {
				pstmt = conn.prepareStatement("select * from pds_item "
						+ "where gid = ?");
				pstmt.setInt(1, gid);
			} else {
				pstmt = conn.prepareStatement("select * from pds_item "
						+ "where fileType=? and gid=?");
				pstmt.setString(1, fileType);
				pstmt.setInt(2, gid);
			}
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				return Collections.emptyList();
			}
			List<PdsItem> itemList = new ArrayList<PdsItem>();
			do {
				PdsItem item = makeItemFromResultSet(rs);
				itemList.add(item);
			} while (rs.next());
			return itemList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int updatePdsItem(Connection conn, PdsItem pdsItem) throws SQLException {
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			pstmt = conn.prepareStatement("update pds_item set fileName=?, fileType=?, fileSize=?, gid=?"
					+ "where uuid=?");
			pstmt.setString(1, pdsItem.getFileName());
			pstmt.setString(2, pdsItem.getFileType());
			pstmt.setLong(3, pdsItem.getFileSize());
			pstmt.setInt(4, pdsItem.getGid());
			pstmt.setString(5, pdsItem.getUuid());
			result = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}
	
	public int deletePdsItem(Connection conn, int gid) throws SQLException {
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			pstmt = conn.prepareStatement("delete from pds_item where gid=?");
			pstmt.setInt(1, gid);
			result = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}

	public int deletePdsItem(Connection conn, String uuid) throws SQLException {
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			pstmt = conn.prepareStatement("delete from pds_item where uuid=?");
			pstmt.setString(1, uuid);
			result = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}
}