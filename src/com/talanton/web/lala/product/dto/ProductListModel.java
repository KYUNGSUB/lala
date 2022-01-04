package com.talanton.web.lala.product.dto;

import java.util.List;

public class ProductListModel {
	private List<Product> productList;
	private Paging paging;

	public List<Product> getProductList() {
		return productList;
	}
	
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public boolean isHasProduct() {
		return !productList.isEmpty();
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}
}