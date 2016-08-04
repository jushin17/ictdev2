<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.sql.*" %>

<%
  response.setContentType("text/html;charset=euc-kr;");
  request.setCharacterEncoding("euc-kr");     //charset, Encoding 설정
  Class.forName("com.mysql.jdbc.Driver");    // load the drive
  String DB_URL = 
          "jdbc:mysql://localhost:3306/listdb";
                 // 주의 : test by changing mydb to name that you make
  String DB_USER = "root";
  String DB_PASSWORD= "roqkf2";
  Connection conn= null;
  Statement stmt = null;
  ResultSet rs   = null;

  try {
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
      stmt = conn.createStatement();

      String query = "SELECT device, code FROM test3";
      rs = stmt.executeQuery(query);
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Push Alarm Message</title>
</head>
<body>
<br>
<br>
<br>
<br>
<br>
<p align="center"><img src="shin.jpg">
<br>
<br>
<br>

<form id="form2" method=post action="list.jsp">
		<center><input type="submit" style="WIDTH: 150pt; HEIGHT: 20pt" value="Registered Deivces"/></center>
</form>
<br>

<form id="form3" method=post action="list1.jsp">
		<center><input type="submit" style="WIDTH: 150pt; HEIGHT: 20pt" value="error msg log"/></center>
</form>
<br>

<form id="form1" method=post action="sendGCM.jsp">

<table border="1" width="800" height="200" align="center">




<tr>
 <%
 	int i=1;
    while(rs.next()) { //rs 를 통해 테이블 객체들의 필드값을 넘겨볼 수 있다.
%>

<td>device<%=i%><input type="checkbox" name="c1" style="WIDTH: 50pt; HEIGHT: 20pt" value="<%=i+9%>"/></td>

<%
	i++;
    } // end while
%>
</tr>

<tr>
 <td>
	&nbsp; server1
	<br>
	<center>error message1<input type="checkbox" name="c1" style="WIDTH: 50pt; HEIGHT: 20pt" value="1"/></center>
	<center>error message2<input type="checkbox" name="c1" style="WIDTH: 50pt; HEIGHT: 20pt" value="2"/></center>
	<center>error message3<input type="checkbox" name="c1" style="WIDTH: 50pt; HEIGHT: 20pt" value="3"/></center>
 </td>
 <td>
	 &nbsp; server2
	 <br>
	<center>error message1<input type="checkbox" name="c1" style="WIDTH: 50pt; HEIGHT: 20pt" value="4"/></center>
	<center>error message2<input type="checkbox" name="c1" style="WIDTH: 50pt; HEIGHT: 20pt" value="5"/></center>
	<center>error message3<input type="checkbox" name="c1" style="WIDTH: 50pt; HEIGHT: 20pt" value="6"/></center>
 </td>
 <td>
	 &nbsp; server3
	 <br>
	<center>error message1<input type="checkbox" name="c1" style="WIDTH: 50pt; HEIGHT: 20pt" value="7"/></center>
	<center>error message2<input type="checkbox" name="c1" style="WIDTH: 50pt; HEIGHT: 20pt" value="8"/></center>
	<center>error message3<input type="checkbox" name="c1" style="WIDTH: 50pt; HEIGHT: 20pt" value="9"/></center>
 </td>
</tr>

<tr>
 <td colspan="3">
	<center><input type="submit" style="WIDTH: 50pt; HEIGHT: 20pt" value="push"/></center>
 </td>
</tr>
</table>

</form>

</body>
</html>


<%
  rs.close();        // ResultSet exit
  stmt.close();     // Statement exit
  conn.close();    // Connection exit
}
catch (SQLException e) {
      out.println("err:"+e.toString());
} 
%>

