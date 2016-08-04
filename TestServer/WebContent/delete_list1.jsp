<%@ page contentType="text/html;charset=utf-8" import="java.sql.*" %>
<%
 request.setCharacterEncoding("utf-8");
 String server = request.getParameter("del");
 try{
  Class.forName("com.mysql.jdbc.Driver");
  String url = "jdbc:mysql://localhost:3306/listdb";
  Connection con = DriverManager.getConnection(url,"root","roqkf2");
  Statement stat = con.createStatement();
  String query = "DELETE FROM test1 where server='" + request.getParameter("del")+"'";
  //쿼리문 전송
  stat.executeUpdate(query); //return 1.
  stat.close();
  con.close();
  response.sendRedirect("list1.jsp") ;
 }

  catch(Exception e){
  out.println( e );
 }
 %>