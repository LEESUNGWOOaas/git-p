package com.javalec.spring_pjt_board.dto;

import java.sql.Timestamp;

public class Dto {
	int bId;
	String bName;
	String bTitle;
	Timestamp bDate;// date������ü�� timestamp�� ��� (java.sql)
	int bHit;
	int bGroup;
	int bIntent;

	public Dto() {// default ������

	}

	public Dto(int bId,String bName,String bTitle,Timestamp bDate,int bHit,int bGroup,int bIntent) {// �����ڸ� ���� �ٷιٷ� ���� ������ �ְ� �Ķ���� ����
		this.bId = bId;
		this.bName = bName;
		this.bDate = bDate;
		this.bTitle = bTitle;
		this.bHit = bHit; 
		this.bGroup = bGroup;
		this.bIntent = bIntent;
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

	public int getbIntent() {
		return bIntent;
	}

	public void setbIntent(int bIntent) {
		this.bIntent = bIntent;
	}

}
