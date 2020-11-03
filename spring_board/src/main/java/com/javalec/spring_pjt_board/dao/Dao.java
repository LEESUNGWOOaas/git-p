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
	
	public Dao() {
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/orcl");
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
			String query = "select bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIntent from mvc_board order by bGroup desc,bStep asc";
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
			 int bIntent = resultSet.getInt("bIntent");
			 
			 
			 Dto dto = new Dto (bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIntent);
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
