package com.talanton.web.lala.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.talanton.web.lala.member.dao.MemberDao;
import com.talanton.web.lala.member.model.Member;
import com.talanton.web.lala.utils.jdbc.JdbcUtil;

public class MemberServiceImpl implements MemberService {
	private static MemberServiceImpl instance = new MemberServiceImpl();
	public static MemberServiceImpl getInstance() {
		return instance;
	}
	private MemberServiceImpl() { }
	
//	private int count_per_page;

	@Override
	public int add(Member member) {
		MemberDao mDao = MemberDao.getInstance();
		Connection conn = null;
		int result = -1;
		try {
			conn = JdbcUtil.getConnection();
			result = mDao.insert(conn, member);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:"+e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:"+e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
	
//	@Override
//	public boolean updateDeviceId(String uid, String deviceId) {
//		MemberDao memberDao = MemberDao.getInstance();
//		Connection conn = null;
//		boolean response = false;
//		try {
//			conn = JdbcUtil.getConnection();
//			int result = memberDao.updateDeviceId(conn, uid, deviceId);
//			if(result == 1) {
//				response = true;
//			}
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
//		} catch (NamingException e) {
//			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
//		} finally {
//			JdbcUtil.close(conn);
//		}
//		return response;
//	}
//	
//	@Override
//	public ListMember getMemberList(int pageNumber) {
//		if (pageNumber < 0) {
//			throw new IllegalArgumentException("page number < 0 : "	+ pageNumber);
//		}
//		MemberDao memberDao = MemberDao.getInstance();
//		Connection conn = null;
//		count_per_page = Integer.valueOf((String)Constants.getParameter("count_per_page"));
//		try {
//			conn = JdbcUtil.getConnection();
//			int totalArticleCount = memberDao.selectCount(conn);
//
//			if (totalArticleCount == 0) {
//				return new ListMember();
//			}
//
//			int totalPageCount = calculateTotalPageCount(totalArticleCount);
//
//			int firstRow = (pageNumber - 1) * count_per_page + 1;
//			int endRow = firstRow + count_per_page - 1;
//
//			if (endRow > totalArticleCount) {
//				endRow = totalArticleCount;
//			}
//			List<Member> MemberList = memberDao.select(conn, firstRow,
//					endRow);
//
//			ListMember MemberListView = new ListMember(
//					MemberList, pageNumber, totalPageCount, firstRow, endRow);
//			return MemberListView;
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
//		} catch (NamingException e) {
//			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
//		} finally {
//			JdbcUtil.close(conn);
//		}
//	}
//	
//	private int calculateTotalPageCount(int totalMemberCount) {
//		if (totalMemberCount == 0) {
//			return 0;
//		}
//		int pageCount = totalMemberCount / count_per_page;
//		if (totalMemberCount % count_per_page > 0) {
//			pageCount++;
//		}
//		return pageCount;
//	}
//	
//	@Override
//	public Member getMember(String id) throws MemberNotFoundException {
//		Connection conn = null;
//		try {
//			conn = JdbcUtil.getConnection();
//			MemberDao memberDao = MemberDao.getInstance();
//			Member member = memberDao.selectById(conn, id);
//			if (member == null) {
//				throw new MemberNotFoundException("존재하지 않는 사용자:" + id);
//			}
//			return member;
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
//		} catch (NamingException e) {
//			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
//		} finally {
//			JdbcUtil.close(conn);
//		}
//	}
//	
	@Override
	public String idCheck(String id) {
		Connection conn = null;
		Member member = null;
		try {
			conn = JdbcUtil.getConnection();
			MemberCheckHelper mch = new MemberCheckHelper();
			member = mch.checkExists(conn, id);
		} catch (SQLException e) {
			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
		} catch (MemberNotFoundException e) {
			
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		if(member == null) {
			return "not using";
		} else {
			return "using";
		}
	}
//	
//	@Override
//	public boolean emailCheck(String email) {
//		Connection conn = null;
//		Member member = null;
//		try {
//			conn = JdbcUtil.getConnection();
//			MemberCheckHelper mch = new MemberCheckHelper();
//			member = mch.checkEmailExists(conn, email);
//			
//		} catch (SQLException e) {
//			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
//		} catch (MemberNotFoundException e) {
//			
//		} catch (NamingException e) {
//			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
//		} finally {
//			JdbcUtil.close(conn);
//		}
//		return member != null;
//	}
//	
//	@Override
//	public String getDeviceId(String name, String phone) {
//		MemberDao memberDao = MemberDao.getInstance();
//		Connection conn = null;
//		String response = null;
//		try {
//			conn = JdbcUtil.getConnection();
//			response = memberDao.getDeviceId(conn, name, phone);
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
//		} catch (NamingException e) {
//			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
//		} finally {
//			JdbcUtil.close(conn);
//		}
//		return response;
//	}
//	
//	@Override
//	public void cryptPassword() {
//		MemberDao memberDao = MemberDao.getInstance();
//		Connection conn = null;
//		try {
//			conn = JdbcUtil.getConnection();
//			memberDao.cryptPassword(conn);
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
//		} catch (NamingException e) {
//			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
//		} finally {
//			JdbcUtil.close(conn);
//		}
//	}
}