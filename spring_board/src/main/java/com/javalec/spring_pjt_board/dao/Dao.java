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

public Dto contentView(String strID) {//���̵��� �����ͼ� �ش� ������ �Խù��� �����ü� �ִ� id�� �ߺ��� �������� ����� strid�� ����

	//�������� ��ȸ���� ����ϴ� �ڵ� 
	upHit(strID);
	
	
	Dto dto = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;//����� ���� view�� �Ѹ��⶧���� �ʿ�
	
	try {
		 	connection = dataSource.getConnection();
		 	
		 	String query = "select * from mvc_board where bId=?";
		 	preparedStatement = connection.prepareStatement(query);
		 	preparedStatement.setInt(1,Integer.parseInt(strID));//bId�� dto�ܿ��� int������ �ޱ� ������ ĳ����
		 	resultSet=preparedStatement.executeQuery();
		 	
		 	if(resultSet.next()) {
		 		int bId=resultSet.getInt("bId");
		 		String bName = resultSet.getString("bName");
		 		String bTitle = resultSet.getString("bTitle");
		 		String bContent = resultSet.getString("bContent");
		 		Timestamp bDate = resultSet.getTimestamp("bDate");
		 		int bHit = resultSet.getInt("bHit");
		 		int bGroup = resultSet.getInt("bGroup");
		 		int bStep = resultSet.getInt("bStep");
		 		int bIndent = resultSet.getInt("bIndent");
		 		
		 		//dto�� ������ ���
		 		dto = new Dto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
		 	
		 	}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally {
		try {
			if(resultSet!=null)resultSet.close();
			if(preparedStatement!=null)preparedStatement.close();
			if(connection!=null)connection.close();
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}
	
	
	return dto;
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
			 int bGroup = resultSet.getInt("bGroup");
			 int bStep = resultSet.getInt("bStep");
			 int bIndent = resultSet.getInt("bIndent");
			 
			 
			 Dto dto = new Dto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
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
				 e2.printStackTrace();
			}
			
		}
		
		return dtos; //�۾�������� dto�� �Ѱ��� 
	}
	
	public void modify(String bId, String bName, String bTitle, String bContent) {//�Ķ���Ͱ� ModifyCommand�� �־������� �����ش�
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		try {
			
			connection = dataSource.getConnection();
			
			String query = "update mvc_board set bName =? , bTitle = ? , bContent = ? where bId = ?";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4,Integer.parseInt(bId));
			
			int rn = preparedStatement.executeUpdate();//resultNumber��ȯ���� ������ Int ���� ������Ʈ�� 1�� ��ȯ
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement!=null)preparedStatement.close();
				if(connection!=null)connection.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
		private void upHit(String bId) {
			//TODO Auto-generated method stub
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = dataSource.getConnection();
				String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1,bId);
				
				int rn = preparedStatement.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try {
					if(preparedStatement != null)preparedStatement.close();
					if(connection != null)connection.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
}
