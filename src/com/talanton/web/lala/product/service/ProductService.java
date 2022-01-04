package com.talanton.web.lala.product.service;

import java.util.List;

import com.talanton.web.lala.product.dto.Criteria;
import com.talanton.web.lala.product.dto.Product;
import com.talanton.web.lala.product.dto.ProductHistory;
import com.talanton.web.lala.product.dto.ProductInfo;
import com.talanton.web.lala.product.dto.ProductOption;
import com.talanton.web.lala.product.dto.WriteForm;

public interface ProductService {
	int insert(WriteForm wf);	// 상품 정보 추가
	List<Product> getProductList(Criteria cri);	// 검색조건에 따른 상품목록 가져오기
	List<ProductInfo> getInfoList(int pid);		// 지정 상품에 대한 상품 정보 가져오기
	List<ProductOption> getOptionList(int pid);	// 지정 상품에 대한 옵션 가져오기
	List<ProductHistory> getHistoryList(int pid);// 지정 상품에 대한 상품정보 변경 이력 가져오기
	int getTotal();						// 전체 상품 갯수 가져오기
	int getCriteriaTotal(Criteria cri);	// 검색조건을 만족하는 상품 갯수 가져오기
	int updateExpose(int pid, String expose);	// 상품 노출 여부 정보 변경
	int remove(int pid);			// 상품 정보 삭제
	Product getProduct(int pid);	// 특정 상품 정보 가져오기
	int update(WriteForm wf);		// 상품 정보 변경(product 테이블)
}