package com.talanton.web.lala.pds.model;

public class AddRequest {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private String fileType;
	private long fileSize;
	private int gid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public PdsItem toPdsItem() {
		PdsItem item = new PdsItem();
		item.setUuid(uuid);
		item.setUploadPath(uploadPath);
		item.setFileName(fileName);
		item.setFileType(fileType);
		item.setFileSize(fileSize);
		item.setGid(gid);
		return item;
	}
}