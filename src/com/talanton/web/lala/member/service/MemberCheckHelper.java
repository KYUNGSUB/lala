package com.talanton.web.lala.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.talanton.web.lala.member.dao.MemberDao;
import com.talanton.web.lala.member.model.Member;

public class MemberCheckHelper {
	public Member checkExists(Connection conn, String uid)
			throws SQLException, MemberNotFoundException {
		MemberDao memberDao = MemberDao.getInstance();
		Member member = memberDao.selectById(conn, uid);
		if (member == null) {
			throw new MemberNotFoundException(
					"없는 사용자 입니다. : " + uid);
		}
		return member;
	}
	
//	public Member checkEmailExists(Connection conn, String email)
//			throws SQLException, MemberNotFoundException {
//		MemberDao memberDao = MemberDao.getInstance();
//		Member member = memberDao.selectByEmail(conn, email);
//		if (member == null) {
//			throw new MemberNotFoundException(
//					"없는 사용자 입니다. : " + email);
//		}
//		return member;
//	}
//	
//	public Member checkMember(Connection conn, String id, String password) throws SQLException, MemberNotFoundException, PasswordMisMatchException {
//		MemberDao memberDao = MemberDao.getInstance();
//		Member member = memberDao.selectById(conn, id);
//		if (member == null) {
//			throw new MemberNotFoundException(
//					"없는 사용자 입니다. : " + id);
//		}
//		else if(password== null) {
//			throw new PasswordMisMatchException(
//					"올바른 비밀번호를 입력해 주세요. : " + password);
//		}
//		else if(!password.equals(member.getPassword())) {
//			throw new PasswordMisMatchException(
//					"올바른 사용자 정보를 입력해 주세요. : " + password);
//		}
//		return member;
//	}
}