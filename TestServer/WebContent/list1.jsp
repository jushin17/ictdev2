<%@ page  contentType="text/html;charset=euc-kr" 
        import="java.sql.DriverManager,
                   java.sql.Connection,
                   java.sql.Statement,
                   java.sql.ResultSet,
                   java.sql.SQLException" %>
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

      String query = "SELECT server, msg, date FROM test1";
      rs = stmt.executeQuery(query);
 %>

<form action="delete_do.jsp" method="post">
<table border="1">
<tr>
<td>server</td>
<td>msg</td>
<td>date</td>
<th>비고</th>
</tr>
<%
    while(rs.next()) { //rs 를 통해 테이블 객체들의 필드값을 넘겨볼 수 있다.
%><tr>
<td><%=rs.getString(1)%></td>
<td><%=rs.getString(2)%></td>
<td><%=rs.getString(3)%></td>

<td><a href="delete_list1.jsp?del=<%=rs.getString(1)%>">삭제</a>
</td>
</tr>
<%
    } // end while
%></table>
</form>
<%
  rs.close();        // ResultSet exit
  stmt.close();     // Statement exit
  conn.close();    // Connection exit
}
catch (SQLException e) {
      out.println("err:"+e.toString());
} 
%>