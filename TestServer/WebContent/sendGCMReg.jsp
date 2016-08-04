<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/json; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.sql.*" %> 
<%
 	String key1 = request.getParameter("KEY");
 	String reg1 = request.getParameter("REG");
    System.out.println("*********************************************************");
    System.out.println("REG 획득");
    System.out.println("KEY : ");
    System.out.println("REG : ");
    System.out.println("*********************************************************");
    
    Map rtnMap = new HashMap();
    
    rtnMap.put("DATA", URLEncoder.encode("등록되었습니다.", "UTF-8"));
    rtnMap.put("DATA1", "END1");
    out.print(rtnMap.toString());
 
    
    Connection conn = null; //초기화
    try{
    String url = "jdbc:mysql://localhost:3306/listdb";      // URL, "jdbc:mysql://localhost:3306/(mySql에서 만든 DB명)" << 입력 이때 3306은 mysql기본 포트
    String id = "root";         // SQL 사용자 이름
    String pw = "roqkf2";     // SQL 사용자 패스워드
    Class.forName("com.mysql.jdbc.Driver");              // DB와 연동하기 위해 DriverManager에 등록한다.
    conn=DriverManager.getConnection(url,id,pw);    // DriverManager 객체로부터 Connection 객체를 얻어온다.
    
    Statement stat = conn.createStatement();
    
    String query = "INSERT INTO test3(device,code)  VALUES('"+key1+"','"+reg1+"')";
    stat.executeUpdate(query);
    
    stat.close();
    conn.close();

    }catch(Exception e){     // 예외 처리
    e.printStackTrace();
    }
    
%>
