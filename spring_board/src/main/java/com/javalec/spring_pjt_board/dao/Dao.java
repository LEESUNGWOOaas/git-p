package com.javalec.spring_pjt_board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.javalec.spring_pjt_board.dto.Dto;

public class Dao {

DataSource dataSource;

public Dto contentView() {
	return null;
}
	public void write(String bName,String bTitle,String bContent) { //writeCommand���� ������ �����ؼ� ������ �Լ� ���� ���� writeCommand�� dao��ü�� ������ ���� ���İ� �̸��� ���� ���ش� 
		//������ ���̽��� �����ؼ� ���� �Է�
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into mvc_board(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values(mvc_board_seq.nextval,?,?,?,0,mvc_board_seq.currval,0,0)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,bName);
			preparedStatement.setString(2,bTitle);
			preparedStatement.setString(3,bContent);
		
			//������Ʈ ���
			int rn = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{ //�ڿ������� ���� finally����
			
			try {
				if(preparedStatement!=null)preparedStatement.close();
				if(connection!=null)connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
		public Dao() {
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/xe");
		} catch (NamingException e) {			
			e.printStackTrace();
		}
		
	}
	public ArrayList<Dto> list() {
		
		ArrayList<Dto> dtos= new ArrayList<Dto>();//�迭 ArrayList�� ����
		Connection connection = null;//DBCP�� �̿��� Ŀ�ؼ� ��ü ���� 
		PreparedStatement preparedStatement = null;//������ �����Ͽ� ������ �����ϴ� ����� ���
		ResultSet resultSet = null;//Ŀ�ǵ�â�� �ƴѻ��¿��� �ڹٷ� ��ȸ Ŀ���� ����ϴ� ��ü ��� 
		
		try {
			connection = dataSource.getConnection();//Ŀ�ؼ� ��ü�� ����
			String query = "select bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent from mvc_board order by bGroup desc,bStep asc";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			//�������� resultSet�� �и��ؾ߉�
		 while(resultSet.next()) {
			 int bId = resultSet.getInt("bId");
			 String bName = resultSet.getString("bName");
			 String bTitle = resultSet.getString("bTitle");
			 String bContent = resultSet.getString("bContent");
			 Timestamp bDate = resultSet.getTimestamp("bDate");
			 int bHit = resultSet.getInt("bHit");
			 int bStep = resultSet.getInt("bStep");
			 int bGroup = resultSet.getInt("bGroup");
			 int bIndent = resultSet.getInt("bIndent");
			 
			 
			 Dto dto = new Dto (bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			 dtos.add(dto);//while �ݺ����� ���� ��� ���鼭 ���ڵ尪�� ��� dto�� ��´�
		 }
			 
			 
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{//�ڿ� ����
			try {
			if(resultSet != null)resultSet.close();
			if(preparedStatement != null)preparedStatement.close();
			if(connection != null)connection.close();
			}catch(Exception e2){
				 
			}
			
		}
		
		return dtos; //�۾�������� dto�� �Ѱ��� 
	}
}
