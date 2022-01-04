package com.talanton.web.lala.product.service;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.talanton.web.lala.common.listener.CommonParameter;
import com.talanton.web.lala.pds.dao.PdsItemDao;
import com.talanton.web.lala.pds.model.AddRequest;
import com.talanton.web.lala.pds.model.PdsItem;
import com.talanton.web.lala.product.dao.ProductDAO;
import com.talanton.web.lala.product.dao.ProductDAOImpl;
import com.talanton.web.lala.product.dao.ProductHistoryDAO;
import com.talanton.web.lala.product.dao.ProductHistoryDAOImpl;
import com.talanton.web.lala.product.dao.ProductInfoDAO;
import com.talanton.web.lala.product.dao.ProductInfoDAOImpl;
import com.talanton.web.lala.product.dao.ProductOptionDAO;
import com.talanton.web.lala.product.dao.ProductOptionDAOImpl;
import com.talanton.web.lala.product.dto.Criteria;
import com.talanton.web.lala.product.dto.Product;
import com.talanton.web.lala.product.dto.ProductHistory;
import com.talanton.web.lala.product.dto.ProductInfo;
import com.talanton.web.lala.product.dto.ProductOption;
import com.talanton.web.lala.product.dto.WriteForm;
import com.talanton.web.lala.utils.jdbc.JdbcUtil;

public class ProductServiceImpl implements ProductService {
	private static final int PRODUCT_REGISTRATION = 1;
	private static final int PRODUCT_UPDATE = 2;
	private static final int PC_LIST_TYPE = 2;
	private static final int MOBILE_EXPOSE_TYPE = 7;
	private static ProductServiceImpl instance = null;
	public static ProductServiceImpl getInstance() {
		if(instance == null) {
			instance = new ProductServiceImpl();
		}
		return instance;
	}
	private ProductServiceImpl() { }
	
	@Override
	public int insert(WriteForm wf) {
		Connection conn = null;
		int result = -1;
		try {
			conn = JdbcUtil.getConnection();
			ProductDAO pdao = ProductDAOImpl.getInstance();
			Product product = wf.toProduct();
			int rowId = pdao.addProduct(conn, product);
			wf.setPid(rowId);
			// 상품 이력 관리 : 등록 추가
			ProductHistoryDAO hdao = ProductHistoryDAOImpl.getInstance();
			ProductHistory history = new ProductHistory();
			history.setItem(PRODUCT_REGISTRATION);
			history.setUserid(wf.getUserid());
			history.setPid(rowId);
			hdao.insert(conn, history);
			
			ProductInfoDAO idao = ProductInfoDAOImpl.getInstance();
			List<ProductInfo> infoList = wf.toProductInfo();
			for(ProductInfo info : infoList) {
				idao.insert(conn, info);
			}
			ProductOptionDAO odao = ProductOptionDAOImpl.getInstance();
			List<ProductOption> optionList = wf.toProductOption();
			for(ProductOption option : optionList) {
				odao.insert(conn, option);
			}
			PdsItemDao fdao = new PdsItemDao();
			for(AddRequest request : wf.getList()) {
				PdsItem pds = request.toPdsItem();
				pds.setGid(rowId);
				fdao.insert(conn, pds);
			}
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

	// 검색조건에 따른 상품 목록 가져오기
	@Override
	public List<Product> getProductList(Criteria cri) {
		ProductDAO dao = ProductDAOImpl.getInstance();
		Connection conn = null;
		List<Product> list = null;
		try {
			conn = JdbcUtil.getConnection();
			list = dao.select(conn, cri);
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
	public List<ProductInfo> getInfoList(int pid) {
		ProductInfoDAO dao = ProductInfoDAOImpl.getInstance();
		Connection conn = null;
		List<ProductInfo> list = null;
		try {
			conn = JdbcUtil.getConnection();
			list = dao.select(conn, pid);
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
	public List<ProductOption> getOptionList(int pid) {
		ProductOptionDAO dao = ProductOptionDAOImpl.getInstance();
		Connection conn = null;
		List<ProductOption> list = null;
		try {
			conn = JdbcUtil.getConnection();
			list = dao.select(conn, pid);
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
	public List<ProductHistory> getHistoryList(int pid) {
		ProductHistoryDAO dao = ProductHistoryDAOImpl.getInstance();
		Connection conn = null;
		List<ProductHistory> list = null;
		try {
			conn = JdbcUtil.getConnection();
			list = dao.select(conn, pid);
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
	public int getTotal() {	// 전체 상품 갯수 검색
		ProductDAO dao = ProductDAOImpl.getInstance();
		Connection conn = null;
		int count = -1;
		try {
			conn = JdbcUtil.getConnection();
			count = dao.selectCount(conn);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return count;
	}
	
	@Override
	public int getCriteriaTotal(Criteria cri) {	// 검색조건에 따른 상품 갯수 검색
		ProductDAO dao = ProductDAOImpl.getInstance();
		Connection conn = null;
		int count = -1;
		try {
			conn = JdbcUtil.getConnection();
			count = dao.selectCriteriaTotalCount(conn, cri);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return count;
	}
	
	@Override
	public int updateExpose(int pid, String expose) {
		ProductDAO dao = ProductDAOImpl.getInstance();
		Connection conn = null;
		int result = -1;
		try {
			conn = JdbcUtil.getConnection();
			result = dao.updateExpose(conn, pid, expose);
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
	public int remove(int pid) {
		ProductDAO dao = ProductDAOImpl.getInstance();
		Connection conn = null;
		int result = -1;
		try {
			conn = JdbcUtil.getConnection();
			Product product = dao.getProduct(conn, pid);
			if(product.isInfo()) {
				ProductInfoDAO idao = ProductInfoDAOImpl.getInstance();
				idao.delete(conn, pid);
			}
			if(product.isOpt()) {
				ProductOptionDAO odao = ProductOptionDAOImpl.getInstance();
				odao.delete(conn, pid);
			}
			// 제품 이미지 파일 삭제
			PdsItemDao pDao = new PdsItemDao();
			for(int i = PC_LIST_TYPE;i <= MOBILE_EXPOSE_TYPE;i++) {	// 배너 삭제 주의
				List<PdsItem> list = pDao.selectByGid(conn, "" + i, pid);
				for(PdsItem pds : list) {
					removeFile(pds);
				}
			}
			
			pDao.deletePdsItem(conn, pid);
			ProductHistory ph = new ProductHistory();
			ph.setItem(ProductHistory.REMOVE);	// 삭제
			ph.setPid(pid);
			ph.setUserid(product.getUserid());
			ProductHistoryDAO hDao = ProductHistoryDAOImpl.getInstance();
			hDao.insert(conn, ph);
			result = dao.delete(conn, pid);
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
	public Product getProduct(int pid) {
		ProductDAO dao = ProductDAOImpl.getInstance();
		Connection conn = null;
		Product product = null;
		try {
			conn = JdbcUtil.getConnection();
			product = dao.getProduct(conn, pid);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} catch (NamingException e) {
			throw new RuntimeException("DB 처리 오류:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
		return product;
	}
	
	@Override
	public int update(WriteForm wf) {
		Connection conn = null;
		int result = -1;
		try {
			conn = JdbcUtil.getConnection();
			ProductDAO pdao = ProductDAOImpl.getInstance();
			Product original = pdao.getProduct(conn, wf.getPid());
			// 기본 상품 정보 변경
			Product product = wf.toProduct();
			pdao.updateProduct(conn, product);
			// 상품 이력 관리 : 등록 추가
			ProductHistoryDAO hdao = ProductHistoryDAOImpl.getInstance();
			ProductHistory history = new ProductHistory();
			history.setItem(PRODUCT_UPDATE);
			history.setUserid(wf.getUserid());
			history.setPid(product.getPid());
			hdao.insert(conn, history);
			
			// 기존 상품 정보를 삭제하고, 새로운 상품 정보를 추가 
			ProductInfoDAO idao = ProductInfoDAOImpl.getInstance();
			if(original.isInfo()) {
				idao.delete(conn, wf.getPid());
			}
			if(wf.getInfoBtn().equals("yes")) {
				List<ProductInfo> infoList = wf.toProductInfo();
				for(ProductInfo info : infoList) {
					idao.insert(conn, info);
				}
			}
			// 기존 옵션 정보를 삭제하고, 새로운 옵션 정보를 추가
			ProductOptionDAO odao = ProductOptionDAOImpl.getInstance();
			if(original.isOpt()) {
				odao.delete(conn, wf.getPid());
			}
			if(wf.getOptionBtn().equals("yes")) {
				List<ProductOption> optionList = wf.toProductOption();
				for(ProductOption option : optionList) {
					odao.insert(conn, option);
				}
			}
			// 관리자가 삭제한 상품 이미지 정보를 삭제
			PdsItemDao fdao = new PdsItemDao();
			List<PdsItem> pdsList = fdao.selectByGid(conn, null, wf.getPid());
			for(PdsItem pds : pdsList) {	// 데이터베이스에 저장된 상품 이미지 정보 중
				if(notExistPdsItem(pds, wf.getAttachList())) {	// 브라우저로부터 올라오지 않은 이미지를 찾음
					fdao.deletePdsItem(conn, pds.getUuid());	// 데이터베이스 삭제
					removeFile(pds);							// 파일 삭제
				}
			}
			// 새로 추가된 상품 이미지 정보를 데이터베이스에 추가
			for(AddRequest request : wf.getList()) {
				PdsItem pds = request.toPdsItem();
				pds.setGid(wf.getPid());
				fdao.insert(conn, pds);
			}
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

	private boolean notExistPdsItem(PdsItem pds, List<PdsItem> attachList) {
		for(PdsItem up : attachList) {
			if(pds.getUuid().equals(up.getUuid())) {
				return false;
			}
		}
		return true;
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