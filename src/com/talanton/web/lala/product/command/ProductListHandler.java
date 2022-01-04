package com.talanton.web.lala.product.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talanton.web.lala.common.command.CommandHandler;
import com.talanton.web.lala.common.listener.CommonParameter;
import com.talanton.web.lala.pds.service.PdsItemNotFoundException;
import com.talanton.web.lala.policy.service.PolicyService;
import com.talanton.web.lala.policy.service.PolicyServiceImpl;
import com.talanton.web.lala.product.dto.Paging;
import com.talanton.web.lala.product.dto.Product;
import com.talanton.web.lala.product.dto.ProductListModel;
import com.talanton.web.lala.product.dto.Criteria;
import com.talanton.web.lala.product.service.ProductService;
import com.talanton.web.lala.product.service.ProductServiceImpl;

public class ProductListHandler implements CommandHandler {
	private static final String FORM_VIEW = "/product/list.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws SQLException, PdsItemNotFoundException {
		Criteria cri = new Criteria();					// 상품 검색과 페이징을 위한 데이터를 저장
		String type = req.getParameter("type");			// 검색 유형
		String keyword = req.getParameter("keyword");	// 검색어
		String detail = req.getParameter("detail");		// 상세 검색 여부
		cri.setType(type);
		cri.setKeyword(keyword);
		cri.setDetail(detail);
		String category1 = null;
		String category2 = null;
		int priceFrom;
		int priceTo;
		String regFrom = null;
		String regTo = null;
		String expose[] = null;
		if(detail != null && detail.equals("yes")) {	// 상세 검색을 요청하면
			category1 = req.getParameter("category1");	// 1차 카테고리 이름
			category2 = req.getParameter("category2");	// 2차 카테고리 이름
			priceFrom = Integer.parseInt(req.getParameter("priceFrom"));	// 판매가격 하한
			priceTo = Integer.parseInt(req.getParameter("priceTo"));		// 판매가격 상한
			regFrom = req.getParameter("regFrom");	// 상품등록일 하한값 : yyyy-MM-dd
			regTo = req.getParameter("regTo");		// 상품등록일 상한값
			expose = req.getParameterValues("expose");	// 진열여부 : 진열(show), 품절(out), 숨김(hide)
			cri.setCategory1(category1);
			cri.setCategory2(category2);
			cri.setPriceFrom(priceFrom);
			cri.setPriceTo(priceTo);
			cri.setRegFrom(regFrom);
			cri.setRegTo(regTo);
			cri.setExpose(expose);
		}

		String pageNumberString = req.getParameter("pageNum");	// 페이징을 위한 현재 페이지 정보 가져오기
		int pageNumber = 1;	// 올라오지 않으면 기본으로 1 페이지로 저장
		if (pageNumberString != null && pageNumberString.length() > 0) {	// 올라오면 값을 저장
			pageNumber = Integer.parseInt(pageNumberString);
		}
		cri.setPageNum(pageNumber);
		
		CommonParameter cp = CommonParameter.getInstance();		// 시스템 파라미터 값을 가져온다.
		int article_per_page = Integer.parseInt(cp.get("article_per_page").toString());
		int page_per_board = Integer.parseInt(cp.get("page_per_board").toString());
		
		String amountString = req.getParameter("amount");	// 한 페이지에 보여줄 게시글 수
		int amount = article_per_page;
		if (amountString != null && amountString.length() > 0) {	// 사용자로부터 올라오지
			amount = Integer.parseInt(amountString);		// 않으면 시스템 파라미터 값을 사용
		}
		cri.setAmount(amount);
		
		ProductService pService = ProductServiceImpl.getInstance();
		List<Product> list = pService.getProductList(cri);	// 검색조건에 따른 상품리스트를 가져온다.

		for(Product product : list) {	// 각 상품별로 적립금을 환산(%->원으로)
			if(product.getDeposit() == -1) {	// 기본 적립금
				PolicyService policyService = PolicyServiceImpl.getInstance();
				// 정책 테이블에 있는 기본 적립금을 얻어 반영
				product.setDeposit(product.getSalePrice() * Integer.parseInt(policyService.getValue("포인트 정책", "구매 포인트")) / 100);
			} else {	// 개별 적립금을 환산
				product.setDeposit(product.getSalePrice() * product.getDeposit() / 100);
			}
		}
		
		ProductListModel productModel = new ProductListModel();
		productModel.setProductList(list);
		Paging paging = new Paging(cri, page_per_board);	// 페이징 처리를 위한 객체 생성
		paging.setCurrentPageNo(pageNumber);	// 현재 페이지 번호 저장
		int totalCount = pService.getTotal();	// 전체 제품 수
		paging.setTotalCount(totalCount);
		int criteriaCount = pService.getCriteriaTotal(cri);	// 검색 결과에 따른 전체 레코드 수
		paging.setNumberOfRecords(criteriaCount);
		paging.makePaging();	// 페이징 처리를 위한 데이터 준비
		productModel.setPaging(paging);
		req.setAttribute("productModel", productModel);
		
		res.setHeader("Pragma", "No-cache");
		res.setHeader("Cache-Control", "no-cache");
		res.addHeader("Cache-Control", "no-store");
		res.setDateHeader("Expires", 1L);
		
		return FORM_VIEW;
	}
}