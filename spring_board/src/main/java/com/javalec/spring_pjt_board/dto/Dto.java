package com.javalec.spring_pjt_board.dto;

import java.sql.Timestamp;

public class Dto {
	int bId;//�Խ���id
	String bName;//�ۼ���
	String bTitle;//������
	String bContent;//����
	Timestamp bDate;// Date������ü�� timestamp�� ��� (java.sql)
	int bHit;	//��ȸ��
	int bGroup;	//���(����)�� �׷��ȣ
	int bStep;	//���� ������ ������� ��ġ����
	int bIndent; // �鿩���� ����� ��ġbit ���̸� �༭ ���� �������� ��

	public Dto() {// default ������

	}

	public Dto(int bId,String bName,String bTitle,String bContent,Timestamp bDate,int bHit,int bGroup,int bStep,int bIndent) {// �����ڸ� ���� �ٷιٷ� ���� ������ �ְ� �Ķ���� ����
		this.bId = bId;
		this.bName = bName;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bTitle = bTitle;
		this.bHit = bHit; 
		this.bGroup = bGroup;
		this.bStep = bStep;
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


	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
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

	public int getbStep() {
		return bStep;
	}

	public void setbStep(int bStep) {
		this.bStep = bStep;
	}

	public int getbGroup() {
		return bGroup;
	}

	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}

	public int getbIndent() {
		return bIndent;
	}

	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}

}
