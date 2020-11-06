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
	public void write(String bName,String bTitle,String bContent) { //writeCommand에서 역할을 전달해서 수행할 함수 생성 그후 writeCommand에 dao객체에 보내는 값의 형식과 이름을 같이 써준다 
		//데이터 베이스에 접근해서 글을 입력
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into mvc_board(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values(mvc_board_seq.nextval,?,?,?,0,mvc_board_seq.currval,0,0)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,bName);
			preparedStatement.setString(2,bTitle);
			preparedStatement.setString(3,bContent);
		
			//업데이트 명령
			int rn = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{ //자원해제를 위해 finally생성
			
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
		
		ArrayList<Dto> dtos= new ArrayList<Dto>();//배열 ArrayList로 만듬
		Connection connection = null;//DBCP를 이용한 커넥션 객체 생성 
		PreparedStatement preparedStatement = null;//변수를 대입하여 쿼리를 수행하는 방식을 사용
		ResultSet resultSet = null;//커맨드창이 아닌상태에서 자바로 조회 커리문 사용하는 객체 사용 
		
		try {
			connection = dataSource.getConnection();//커넥션 객체를 구함
			String query = "select bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent from mvc_board order by bGroup desc,bStep asc";
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
			 int bIndent = resultSet.getInt("bIndent");
			 
			 
			 Dto dto = new Dto (bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
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
