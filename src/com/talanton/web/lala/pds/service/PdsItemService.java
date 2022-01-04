package com.talanton.web.lala.pds.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.talanton.web.lala.pds.model.AddRequest;
import com.talanton.web.lala.pds.model.ListPdsItem;
import com.talanton.web.lala.pds.model.PdsItem;

public interface PdsItemService {
	List<PdsItem> getPdsItemByGid(String fileType, int bid) throws SQLException, PdsItemNotFoundException;
	PdsItem getPdsItem(String uuid) throws PdsItemNotFoundException;
	boolean increaseCount(String id) throws NamingException;
	int add(AddRequest request);
	ListPdsItem getPdsItemList(int pageNumber) throws NamingException;
	int getTotalPageNumber() throws NamingException;
	int modifyPdsItem(PdsItem pdsItem) throws NamingException;
	int removePdsItem(int bid) throws SQLException, NamingException;
	int removePdsItemByUuid(PdsItem pdsItem) throws SQLException, NamingException;
}