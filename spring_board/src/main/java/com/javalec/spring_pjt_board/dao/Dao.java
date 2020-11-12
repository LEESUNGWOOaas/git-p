package com.javalec.spring_pjt_board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.javalec.spring_pjt_board.dto.Dto;
import com.javalec.spring_pjt_board.util.Constant;

public class Dao {
	
DataSource dataSource; // �����ͼҽ�

JdbcTemplate template = null;//jdbc

public Dao() {//Dao ������  �����Ǵ� ���� ���� �����ϱ� ���� ���������� ����
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/xe");
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		template = Constant.template;
	}
	

	


public Dto contentView(String strID) {//���̵��� �����ͼ� �ش� ������ �Խù��� �����ü� �ִ� id�� �ߺ��� �������� ����� strid�� ����

	//�������� ��ȸ���� ����ϴ� �ڵ� 
	upHit(strID); 
	String query = "select * from mvc_board where bId=?" + strID; // Ŀ�ؼ��� �����ͼ� ���� ������ id���� ������ �־��ش�
	return template.queryForObject(query, new BeanPropertyRowMapper<Dto>(Dto.class));
	
}

	public void write(final String bName,final String bTitle,final String bContent) { //writeCommand���� ������ �����ؼ� ������ �Լ� ���� ���� writeCommand�� dao��ü�� ������ ���� ���İ� �̸��� ���� ���ش� 
		//������ ���̽��� �����ؼ� ���� �Է�
		
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				String query = "insert into mvc_board(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values(mvc_board_seq.nextval,?,?,?,0,mvc_board_seq.currval,0,0)";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				
				return pstmt;//null�� �ƴ� preparedStatement�� ��ȯ�ؾߵȴ�.
			}
		});
		
	}
	public ArrayList<Dto> list() {
		
	 String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_board order by bGroup desc, bStep asc";
	 return (ArrayList<Dto>) template.query(query,new BeanPropertyRowMapper<Dto>(Dto.class)); //���� �ڿ� ���� list �����͸� ������ Ŀ�ǵ� ��ü�� ���
	 	//Ÿ���� ��õ����ʾ� ��������� �Ȱ��̱� ������ Ÿ�Ե� ������ش� 
	
	 // dtos�� �ٷ� ���ϵǴ� ���̱� ������ return ���� ����� ���� �ڵ� �������� ������ �����
		 	
		}
	
	public void modify(final String bId,final String bName,final String bTitle,final String bContent) {//�Ķ���Ͱ� ModifyCommand�� �־������� �����ش�
	
		String query = "update mvc_board set bName = ? , bTitle = ? , bContent = ? where bId = ?";
		
		template.update(query,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps)
					throws SQLException {
				ps.setString(1, bName);
				ps.setString(2, bTitle);
				ps.setString(3, bContent);
				ps.setInt(4, Integer.parseInt(bId));
			}
		});
 
	}
	
	public void delete(final String strID) {
	
		String query = "delete mvc_board where bId =?";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1,strID);
				
			}
		});

	}
	
	public Dto reply_view(String strID) {
		String query= "select * from mvc_board where bId= " + strID;
		return template.queryForObject(query, new BeanPropertyRowMapper<Dto>(Dto.class));
	}
	
	
	public void reply(final String bId, final String bName, final String bTitle, final String bContent, final String bGroup, final String bStep, final String bIndent) {
	
		
		String query="insert into mvc_board(bId,bName,bTitle,bContent,bGroup,bStep,bIndent) values(mvc_board_seq.nextval,?,?,?,?,?,?";
		
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bName);
				ps.setString(2, bTitle);
				ps.setString(3, bContent);
				ps.setInt(4, Integer.parseInt(bGroup));
				ps.setInt(5, Integer.parseInt(bStep) +1);
				ps.setInt(6, Integer.parseInt(bIndent) +1);
				
			}
		});
	}
	
		private void replyShape(final String strGroup,final String strStep) {//�鿩���� ���
			String query = "update mvc_board set bStep + 1 where bGroup = ? and bStep >? ";//where =���� �׷쳻���� Step�� ���纸�� ū����
			template.update(query, new PreparedStatementSetter( ) {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, Integer.parseInt(strGroup));
					ps.setInt(2, Integer.parseInt(strStep));
				}
			});
			
		}
	
		
		private void upHit(final String bId) {
			String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
			
			template.update(query, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					// TODO Auto-generated method stub
					ps.setInt(1,Integer.parseInt(bId));
					// ó�� ��ϸӽ� Ŭ������ ����� ������ ��� upHit(String bId)�� bId��
					//parseInt�� bId�� �������־� ���� ���ÿ� ����Ǳ� ���� ������ ���ȭ�� �Ѵ�.
				}
			});
		}
}
