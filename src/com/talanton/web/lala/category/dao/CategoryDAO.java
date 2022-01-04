package com.talanton.web.lala.category.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.talanton.web.lala.category.model.Category;
import com.talanton.web.lala.category.model.OptionInfo;

public interface CategoryDAO {
	// 1차 카테고리 정보 목록을 가져온다.
	List<Category> getListFirst(Connection conn) throws SQLException;
	// 2차 카테고리 정보를 가져온다.
	List<Category> getListSecond(Connection conn, String code) throws SQLException;
	// 카테고리 코드로 카테고리 정보를 가져온다.
	Category selectByCode(Connection conn, String code) throws SQLException;
	// 카테고리 정보를 추가
	int insert(Connection conn, Category category) throws SQLException;
	// 게시 순서(seq)를 사용하여 1차 카테고리 정보를 가져온다.
	Category getFirstCategoryBySeq(Connection conn, int seq) throws SQLException;
	// 카테고리의 게시 순서를 변경
	int updateCategorySeq(Connection conn, String code, int seq) throws SQLException;
	// 게시글 순서(seq)를 사용하여 2차 카테고리 정보를 가져온다.
	Category getSecondCategoryBySeq(Connection conn, String parent, int seq) throws SQLException;
	// 카테고리의 노출여부를 변경
	int updateCategory(Connection conn, String code, boolean expose) throws SQLException;
	// 카테고리의 노출여부와 GNB 메뉴 노출여부를 변경
	int updateCategory(Connection conn, String code, boolean expose, boolean gnb) throws SQLException;
	// 카테고리 정보를 삭제
	int deleteCategory(Connection conn, String code) throws SQLException;
	// 지정 카테고리 코드에 대한 노출여부 정보를 가져온다.
	OptionInfo getOptionInfo(Connection conn, String code) throws SQLException;
	// 카테고리 삭제에 따라 영향을 주는 다른 카테고리 순서를 변경
	int updateSequence(Connection conn, String parent, int seq) throws SQLException;
	// 1차 카테고리에 속하는 2차 카테고리들을 삭제
	int deleteSecondCategory(Connection conn, String parent) throws SQLException;
	// 카테고리의 배치 순서를 가져온다.
	int getSeq(Connection conn, int step, String parent) throws SQLException;
	// 2차 카테고리 코드를 생성하기 위하여 필요한 현재 카테고리들의 최대값, 갯수를 구한다. 
	int[] getMaxCount(Connection conn, String parent) throws SQLException;
	// 2차 카테고리 코드를 생성하기 위하여 필요한 현재 카테고리들의 코드값을 구한다.
	String[] getCodes(Connection conn, String parent, int count) throws SQLException;
}