package com.talanton.web.lala.category.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.naming.NamingException;

import com.talanton.web.lala.category.dao.CategoryDAO;
import com.talanton.web.lala.category.dao.CategoryDAOImpl;
import com.talanton.web.lala.category.model.Category;
import com.talanton.web.lala.category.model.MoveRequest;
import com.talanton.web.lala.category.model.OptionInfo;
import com.talanton.web.lala.utils.jdbc.JdbcUtil;

public class CategoryServiceImpl implements CategoryService {	// 싱글톤으로 동작
	private static CategoryServiceImpl instance = null;
	public static CategoryServiceImpl getInstance() {
		if(instance == null) {
			instance = new CategoryServiceImpl();
		}
		return instance;
	}
	private CategoryServiceImpl() { }
	
	@Override
	public List<Category> getList() {	// 카테고리 목록을 데이터베이스로부터 가져온다.
		CategoryDAO dao = CategoryDAOImpl.getInstance();
		Connection conn = null;
		List<Category> list = null;
		try {
			conn = JdbcUtil.getConnection();
			list = dao.getListFirst(conn);	// 1차 카테고리 목록을 가져온다.
			for(Category category : list) {	// 2차 카테고리 정보를 가져온다.
				category.setList(dao.getListSecond(conn, category.getCode()));
			}
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return list;
	}
	
	@Override
	public List<Category> getSecondList(String parent) {	// 카테고리 목록을 데이터베이스로부터 가져온다.
		CategoryDAO dao = CategoryDAOImpl.getInstance();
		Connection conn = null;
		List<Category> list = null;
		try {
			conn = JdbcUtil.getConnection();
			list = dao.getListSecond(conn, parent);	// 2차 카테고리 목록을 가져온다.
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return list;
	}
	
	@Override
	public String codeCheck(String code) {
		CategoryDAO dao = CategoryDAOImpl.getInstance();
		Connection conn = null;
		Category category = null;
		try {
			conn = JdbcUtil.getConnection();
			category = dao.selectByCode(conn, code);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		if(category != null) {
			return "using";
		} else {
			return "not using";
		}
	}
	
	@Override
	public int add(Category category) {
		CategoryDAO dao = CategoryDAOImpl.getInstance();
		Connection conn = null;
		int result = -1;
		try {
			conn = JdbcUtil.getConnection();
			category.setSeq(dao.getSeq(conn, category.getStep(), category.getParent()) + 1);
			result = dao.insert(conn, category);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	@Override
	public int moveCatgory(MoveRequest mr) {
		int result = -1;
		int direction;	// 배치순서(seq)값을 1 증가(down), 1 감소(-1, up) 나타낸다.
		if(mr.getDirection().equals("up")) {	// upward
			direction = -1;	// up
		} else {
			direction = 1;	// down
		}
		CategoryDAO dao = CategoryDAOImpl.getInstance();
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			Category other = null;	// 이동할 상대 카테고리
			if(mr.getStep() == 1) {	// 1차 카테고리
				other = dao.getFirstCategoryBySeq(conn, mr.getSeq() + direction);
			} else {				// 2차 카테고리
				other = dao.getSecondCategoryBySeq(conn, mr.getParent(), mr.getSeq() + direction);
			}
			// 현재 카테고리의 배치 순서를 변경
			result = dao.updateCategorySeq(conn, mr.getCode(), mr.getSeq() + direction);
			// 상대 카테고리의 배치 순서를 변경
			result = dao.updateCategorySeq(conn, other.getCode(), mr.getSeq());
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	// 2차 카테고리 정보 변경 : 노출 여부(expose)
	@Override
	public int modify(String code, boolean expose) {
		CategoryDAO dao = CategoryDAOImpl.getInstance();
		Connection conn = null;
		int result = -1;
		try {
			conn = JdbcUtil.getConnection();
			result = dao.updateCategory(conn, code, expose);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}

	// 1차 카테고리 정보 변경 : 노출 여부(expose), GNB 표시 여부(gnb)
	@Override
	public int modify(String code, boolean expose, boolean gnb) {
		CategoryDAO dao = CategoryDAOImpl.getInstance();
		Connection conn = null;
		int result = -1;
		try {
			conn = JdbcUtil.getConnection();
			result = dao.updateCategory(conn, code, expose, gnb);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	// code값을 가지는 상품 카테고리 정보를 삭제한다.
	@Override
	public int remove(String code) {
		CategoryDAO dao = CategoryDAOImpl.getInstance();
		Connection conn = null;
		int result = -1;
		try {
			conn = JdbcUtil.getConnection();
			// 카테고리 정보를 검색한다.
			Category category = dao.selectByCode(conn, code);	// 카테고리 정보 가져오기
			// 지정 카테고리 정보를 삭제
			result = dao.deleteCategory(conn, code);			// 카테고리 삭제
			// 영향을 받은 카테고리에 대하여 seq 갱신
			result = dao.updateSequence(conn, category.getParent(), category.getSeq());
			// 삭제하는 카테고리가 1차 카테고리이면, 산하의 2차 카테고리들도 삭제
			if(category.getStep() == 1) {	// 1차 카테고리면, 산하 2차 카테고리를 삭제
				result = dao.deleteSecondCategory(conn, category.getCode());
			}
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	// 카테고리 노출 여부(expose)와 GNB 노출 여부(gnb)를 데이터베이스로부터 가져온다.
	@Override
	public OptionInfo getOptionInfo(String code) {
		CategoryDAO dao = CategoryDAOImpl.getInstance();
		Connection conn = null;
		OptionInfo info = null;
		try {
			conn = JdbcUtil.getConnection();
			info = dao.getOptionInfo(conn, code);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return info;
	}
	
	@Override
	public int getSeed(String parent) {
		CategoryDAO dao = CategoryDAOImpl.getInstance();
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			int[] data = dao.getMaxCount(conn, parent);
			if(data[0] == data[1]) {
				return data[0] + 1;
			}
			String[] codes = dao.getCodes(conn, parent, data[1]);
			int[] codesNum = new int[data[1]];
			for(int i = 0;i < codes.length;i++) {
				codesNum[i] = Integer.parseInt(codes[i]);
			}
			Arrays.sort(codesNum);	// 오름차순으로 정렬
			for(int i = 0;i < codes.length;i++) {
				if((i+1) == codesNum[i])
					continue;
				return i + 1;
			}
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return -1;
	}
}