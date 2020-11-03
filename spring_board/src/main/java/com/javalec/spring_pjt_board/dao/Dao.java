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
		
		ArrayList<Dto> dtos= new ArrayList<Dto>();//배열 ArrayList로 만듬
		Connection connection = null;//DBCP를 이용한 커넥션 객체 생성 
		PreparedStatement preparedStatement = null;//변수를 대입하여 쿼리를 수행하는 방식을 사용
		ResultSet resultSet = null;//커맨드창이 아닌상태에서 자바로 조회 커리문 사용하는 객체 사용 
		
		try {
			connection = dataSource.getConnection();//커넥션 객체를 구함
			String query = "select bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIntent from mvc_board order by bGroup desc,bStep asc";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			//쿼리문을 resultSet을 분리해야됌
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
			 dtos.add(dto);//while 반복문을 통해 계속 돌면서 레코드값을 계속 dto에 담는다
		 }
			 
			 
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{//자원 해제
			try {
			if(resultSet != null)resultSet.close();
			if(preparedStatement != null)preparedStatement.close();
			if(connection != null)connection.close();
			}catch(Exception e2){
				 
			}
			
		}
		
		return dtos; //작업결과물을 dto로 넘겨줌 
	}
}
