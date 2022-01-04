package com.talanton.web.lala.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.talanton.web.lala.member.model.Member;
import com.talanton.web.lala.utils.jdbc.JdbcUtil;

public class MemberDao {
	private static MemberDao instance = new MemberDao();
	private MemberDao() { }
	public static MemberDao getInstance() {
		return instance;
	}
	
//	public int selectCount(Connection conn) throws SQLException {
//		Statement stmt = null;
//		ResultSet rs = null;
//		try {
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery("select count(*) from member");
//			rs.next();
//			return rs.getInt(1);
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(stmt);
//		}
//	}

	public Member getMember(Connection conn, String id, String password) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		try {
			String query = "select * from member where id=? and pwd=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = makeMemberFromResultSet(rs);
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return member;
	}
	
	public Member selectById(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from member where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			Member item = makeMemberFromResultSet(rs);
			return item;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
//	public Member selectByEmail(Connection conn, String email) throws SQLException {
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = conn.prepareStatement("select * from member where email = ?");
//			pstmt.setString(1, email);
//			rs = pstmt.executeQuery();
//			if (!rs.next()) {
//				return null;
//			}
//			Member item = makeMemberFromResultSet(rs);
//			return item;
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//	}
	
//	public int userCheck(Connection conn, String uid, String passwd) throws SQLException {
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		int x = -1;
//		try {
//			pstmt = conn.prepareStatement("select password from member where uid = ?");
//			pstmt.setString(1, uid);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {	// 해당 id가 존재
//				String dbPassword = rs.getString("password");
//				if(passwd.equals(dbPassword))
//					x = 1;	// 인증 성공
//				else
//					x = 0;	// 비밀번호 틀림
//			}
//			else {	// 해당 id가 없으면
//				x = -1;
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//
//		return x;
//	}
	
	public Member makeMemberFromResultSet(ResultSet rs) throws SQLException {
		Member member = new Member();
		member.setId(rs.getString("id"));
		member.setPwd(rs.getString("pwd"));
		member.setName(rs.getString("name"));
		member.setMobile(rs.getString("mobile"));
		member.setEmail(rs.getString("email"));
		member.setMarketing(rs.getString("marketing"));
		member.setRcv(rs.getString("rcv"));
		member.setGrade(rs.getInt("grade"));
		member.setVisited(rs.getInt("visited"));
		member.setPursuit(rs.getInt("pursuit"));
		member.setAccum(rs.getInt("accum"));
		member.setCreatedAt(rs.getTimestamp("createdAt"));
		member.setModifiedAt(rs.getTimestamp("modifiedAt"));
		return member;
	}
	
	public int insert(Connection conn, Member member) throws SQLException {
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			String query = "insert into member (id, pwd, name, email, grade, marketing"
					+ ") values(?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.setInt(5, member.getGrade());
			pstmt.setString(6, member.getMarketing());
			result = pstmt.executeUpdate();
			//System.out.println("result = " + result);
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}

//	public int update(Connection conn, Member member) throws SQLException {
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		int result = -1;
//		try {
//			String query = "update member set password = ?, name=?, login_type=?, email=?, phone=? where uid=?";
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, member.getPassword());
//			pstmt.setString(2, member.getName());
//			pstmt.setInt(3, member.getLogin_type());
//			pstmt.setString(4, member.getEmail());
//			pstmt.setString(5, member.getPhone());
//			pstmt.setString(6, member.getUid());
//			result = pstmt.executeUpdate();
//			//System.out.println("result = " + result);
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//		return result;
//	}
//	
//	public void delete(Connection conn, String uid) throws SQLException {
//		PreparedStatement pstmt = null;
//		try {
//			pstmt = conn.prepareStatement("delete from member " + "where uid = ?");
//			pstmt.setString(1, uid);
//			pstmt.executeUpdate();
//		} finally {
//			JdbcUtil.close(pstmt);
//		}
//	}
//	
//	public List<Member> select(Connection conn, int firstRow, int endRow) throws SQLException {
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = conn.prepareStatement("select * from member order by createdAt desc limit ?, ?");
//			
//			pstmt.setInt(1, firstRow - 1);
//			pstmt.setInt(2, endRow - firstRow + 1);
//			rs = pstmt.executeQuery();
//			if (!rs.next()) {
//				return Collections.emptyList();
//			}
//			List<Member> itemList = new ArrayList<Member>();
//			do {
//				Member member = makeMemberFromResultSet(rs);
//				itemList.add(member);
//			} while (rs.next());
//			return itemList;
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//	}
//	
//	public int updateDeviceId(Connection conn, String uid, String deviceId) throws SQLException {
//		PreparedStatement pstmt = null;
//		int result = -1;
//		try {
//			String query = "update member set deviceId=? where uid=?";
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, deviceId);
//			pstmt.setString(2, uid);
//			result = pstmt.executeUpdate();
//			//System.out.println("result = " + result);
//		} finally {
//			JdbcUtil.close(pstmt);
//		}
//		return result;
//	}
//	
//	public String getDeviceId(Connection conn, String name, String phone) throws SQLException {
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = conn.prepareStatement("select deviceId from member where name=? and phone=?");
//			pstmt.setString(1, name);
//			pstmt.setString(2, phone);
//			rs = pstmt.executeQuery();
//			if (!rs.next()) {
//				return null;
//			}
//			return rs.getString("deviceId");
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//	}
//	
//	public void cryptPassword(Connection conn) {
//		String sql = "select uid, password from member";
//		PreparedStatement pstmt = null;
//		ResultSet rs= null;
//		//SHA-256를 사용하는 SHA256클래스의 객체를 얻어낸다.
//        SHA256 sha = SHA256.getInsatnce();
//        
//		try (Statement stmt = conn.createStatement();) {
//			rs = stmt.executeQuery(sql);
//			while(rs.next()) {
//				String uid = rs.getString("uid");
//            	String orgPass = rs.getString("password");
//            	
//            	//SHA256클래스의 getSha256()메소드를 사용해 
//            	//원래의 비밀번호를 SHA-256방식으로 암호화
//            	String shaPass = sha.getSha256(orgPass.getBytes());
//            	
//            	//SHA-256방식으로 암호화된 값을 다시 BCrypt클래스의 
//            	//hashpw()메소드를 사용해서 bcrypt방식으로 암호화
//            	//BCrypt.gensalt()메소드는 salt값을 난수를 사용해 생성.
//            	String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
//            	
//            	pstmt = conn.prepareStatement(
//                        "update member set password=? where uid=?");
//                pstmt.setString(1, bcPass);
//                pstmt.setString(2, uid);
//                pstmt.executeUpdate();
//                pstmt.close();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
//            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
//		}
//	}
}