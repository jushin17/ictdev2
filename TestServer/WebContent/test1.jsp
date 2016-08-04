<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ page import = "java.sql.*" %>     <!-- JSP에서 JDBC의 객체를 사용하기 위해 java.sql 패키지를 import 한다 -->
<%
Connection conn = null; //초기화
try{
String url = "jdbc:mysql://localhost:3306/listdb";      // URL, "jdbc:mysql://localhost:3306/(mySql에서 만든 DB명)" << 입력 이때 3306은 mysql기본 포트
String id = "root";         // SQL 사용자 이름
String pw = "roqkf2";     // SQL 사용자 패스워드
Class.forName("com.mysql.jdbc.Driver");              // DB와 연동하기 위해 DriverManager에 등록한다.
conn=DriverManager.getConnection(url,id,pw);    // DriverManager 객체로부터 Connection 객체를 얻어온다.
out.println("연결됨");      // 커넥션이 제대로 연결되면 수행된다.

Statement stat = conn.createStatement();
String query = "INSERT INTO test1(device,code)  VALUES('device2','qewrqwer5555')";
//INSERT into member(id,name,pwd,email) VALUES ('id','name','pwd','email') 쿼리문
stat.executeUpdate(query);
stat.close();
conn.close();

}catch(Exception e){     // 예외 처리
e.printStackTrace();
}
%>