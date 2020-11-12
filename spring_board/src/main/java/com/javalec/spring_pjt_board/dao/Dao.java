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
	
DataSource dataSource; // 데이터소스

JdbcTemplate template = null;//jdbc

public Dao() {//Dao 생성자  생성되는 순간 일을 진행하기 위해 전역변수로 생성
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/xe");
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		template = Constant.template;
	}
	

	


public Dto contentView(String strID) {//아이디값을 가져와서 해당 컨텐츠 게시물을 가져올수 있다 id의 중복이 있을것을 대비해 strid로 변경

	//컨텐츠의 조회수를 담당하는 코드 
	upHit(strID); 
	String query = "select * from mvc_board where bId=?" + strID; // 커넥션을 가져와서 쓰기 때문에 id값의 정보를 넣어준다
	return template.queryForObject(query, new BeanPropertyRowMapper<Dto>(Dto.class));
	
}

	public void write(final String bName,final String bTitle,final String bContent) { //writeCommand에서 역할을 전달해서 수행할 함수 생성 그후 writeCommand에 dao객체에 보내는 값의 형식과 이름을 같이 써준다 
		//데이터 베이스에 접근해서 글을 입력
		
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				String query = "insert into mvc_board(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values(mvc_board_seq.nextval,?,?,?,0,mvc_board_seq.currval,0,0)";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				
				return pstmt;//null이 아닌 preparedStatement를 반환해야된다.
			}
		});
		
	}
	public ArrayList<Dto> list() {
		
	 String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_board order by bGroup desc, bStep asc";
	 return (ArrayList<Dto>) template.query(query,new BeanPropertyRowMapper<Dto>(Dto.class)); //쿼리 뒤엔 받을 list 데이터를 가져올 커맨드 객체를 명시
	 	//타입이 명시되지않아 노란색으로 된것이기 떄문에 타입도 명시해준다 
	
	 // dtos는 바로 리턴되는 것이기 때문에 return 으로 만들면 더욱 코드 가독성에 이점이 생긴다
		 	
		}
	
	public void modify(final String bId,final String bName,final String bTitle,final String bContent) {//파라미터가 ModifyCommand에 주어졌으니 맞춰준다
	
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
	
		private void replyShape(final String strGroup,final String strStep) {//들여쓰기 기능
			String query = "update mvc_board set bStep + 1 where bGroup = ? and bStep >? ";//where =같은 그룹내에서 Step이 현재보다 큰것을
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
					// 처음 어나니머스 클래스로 만들면 오류가 뜬다 upHit(String bId)에 bId와
					//parseInt의 bId이 연동되있어 값이 동시에 변경되기 때문 때문에 상수화를 한다.
				}
			});
		}
}
