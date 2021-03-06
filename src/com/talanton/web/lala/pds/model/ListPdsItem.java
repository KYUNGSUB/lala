package com.talanton.web.lala.pds.model;

import java.util.ArrayList;
import java.util.List;

public class ListPdsItem {
	private List<PdsItem> pdsItemList;
	private int requestPage;
	private int totalPageCount;
	private int startRow;
	private int endRow;

	public ListPdsItem() {
		this(new ArrayList<PdsItem>(), 0, 0, 0, 0);
	}
	
	public ListPdsItem(List<PdsItem> PdsItemList, int requestPageNumber,
			int totalPageCount, int startRow, int endRow) {
		this.pdsItemList = PdsItemList;
		this.requestPage = requestPageNumber;
		this.totalPageCount = totalPageCount;
		this.startRow = startRow;
		this.endRow = endRow;
	}

	public List<PdsItem> getPdsItemList() {
		return pdsItemList;
	}
	
	public boolean isHasPdsItem() {
		return ! pdsItemList.isEmpty();
	}

	public int getRequestPage() {
		return requestPage;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}
}