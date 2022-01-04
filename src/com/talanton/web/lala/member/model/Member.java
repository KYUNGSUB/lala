package com.talanton.web.lala.member.model;

import java.sql.Timestamp;

public class Member {
	private String name;
	private String id;
	private String pwd;
	private String mobile;
	private String email;
	private String marketing;
	private String rcv;
	private int grade;
	private int visited;
	private int pursuit;
	private int accum;
	private Timestamp createdAt;
	private Timestamp modifiedAt;
	
	public Member() { }

	public Member(String name, String id, String pwd, String email, int grade, String marketing) {
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.grade = grade;
		this.marketing = marketing;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMarketing() {
		return marketing;
	}
	public void setMarketing(String marketing) {
		this.marketing = marketing;
	}
	public String getRcv() {
		return rcv;
	}
	public void setRcv(String rcv) {
		this.rcv = rcv;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getVisited() {
		return visited;
	}
	public void setVisited(int visited) {
		this.visited = visited;
	}
	public int getPursuit() {
		return pursuit;
	}
	public void setPursuit(int pursuit) {
		this.pursuit = pursuit;
	}
	public int getAccum() {
		return accum;
	}
	public void setAccum(int accum) {
		this.accum = accum;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}