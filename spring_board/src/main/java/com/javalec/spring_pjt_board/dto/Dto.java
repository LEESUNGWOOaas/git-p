package com.javalec.spring_pjt_board.dto;

import java.sql.Timestamp;

public class Dto {
	int bId;//게시판id
	String bName;//작성자
	String bTitle;//글제목
	String bContent;//내용
	Timestamp bDate;// Date하위객체의 timestamp를 사용 (java.sql)
	int bHit;	//조회수
	int bGroup;	//답글(리플)의 그룹번호
	int bStep;	//글이 위에서 몇번에 위치한지
	int bIndent; // 들여쓰기 답글의 위치bit 차이를 줘서 글이 띄어써지는 것
	
	public Dto() {// default 생성자

	}

	public Dto(int bId,String bName,String bTitle,Timestamp bDate,int bHit,int bGroup,int bIntent, String bContent, int bIndent) {// 생성자를 통해 바로바로 값을 넣을수 있게 파라미터 받음
		this.bId = bId;
		this.bName = bName;
		this.bDate = bDate;
		this.bContent = bContent;
		this.bTitle = bTitle;
		this.bHit = bHit; 
		this.bStep = bStep;
		this.bGroup = bGroup;
		this.bIndent = bIndent;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}


	public Timestamp getbDate() {
		return bDate;
	}

	public void setbDate(Timestamp bDate) {
		this.bDate = bDate;
	}

	public int getbHit() {
		return bHit;
	}

	public void setbHit(int bHit) {
		this.bHit = bHit;
	}

	public int getbGroup() {
		return bGroup;
	}

	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public int getbStep() {
		return bStep;
	}

	public void setbStep(int bStep) {
		this.bStep = bStep;
	}

	public int getbIndent() {
		return bIndent;
	}

	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}



}
