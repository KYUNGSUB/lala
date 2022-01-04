package com.talanton.web.lala.category.model;

public class MoveRequest {
	private int step;
	private String direction;
	private int seq;
	private String code;
	private String parent;
	
	public MoveRequest() { }
	public MoveRequest(int step, String direction, int seq, String code, String parent) {
		this.step = step;			// 1:1차 카테고리, 2:2차 카테고리
		this.direction = direction;	// up:위로 이동, down:아래로 이동
		this.seq = seq;				// 현재 카테고리의 배치 순서
		this.code = code;			// 현재 카테고리의 카테고리 코드
		this.parent = parent;		// 현재 카테고리가 2차라면 1차 카테고리 코드
	}
	
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	
	@Override
	public String toString() {
		return "MoveRequest [step=" + step + ", direction=" + direction + ", seq=" + seq + ", code=" + code
				+ ", parent=" + parent + "]";
	}
}