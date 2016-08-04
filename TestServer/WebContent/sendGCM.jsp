<%@page import="testpackage.GCMUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="testpackage.GCMVo"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.sql.*" %> 
<%@ page import="java.util.*, java.text.*"  %>
    
<%
 
    //전달할 PUSH 내용
    String[] str = request.getParameterValues("c1");
    String title = "Push alarm";
    String msg = "Push error message";
    int device_flag1=0;
    int device_flag2=0;
    int device_flag3=0;

	Connection conn = null; //초기화
	
try{
	String url = "jdbc:mysql://localhost:3306/listdb";      // URL, "jdbc:mysql://localhost:3306/(mySql에서 만든 DB명)" << 입력 이때 3306은 mysql기본 포트
	String id = "root";         // SQL 사용자 이름
	String pw = "roqkf2";     // SQL 사용자 패스워드
	Class.forName("com.mysql.jdbc.Driver");              // DB와 연동하기 위해 DriverManager에 등록한다.
	conn=DriverManager.getConnection(url,id,pw);    // DriverManager 객체로부터 Connection 객체를 얻어온다.

	Statement stat = conn.createStatement();   
    
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
	String today = formatter.format(new java.util.Date());
	
    String query = "SELECT code FROM test3";
    ResultSet rs = stat.executeQuery(query);
	
    String[] dev=new String[5];
    int k=0;
    while(rs.next()) {
    	dev[k++]=rs.getString("code");  	
    }
	
	
	for(int i=0;i<str.length;i++){
	    int checkmsg=0;
    	if(str[i].equals("10")){
    		device_flag1=1;
    		continue;
    	}
		if(str[i].equals("11")){
    		device_flag2=1;
    		continue;
    	}
		if(str[i].equals("12")){
    		device_flag3=1;
    		continue;
    	}
    	if(str[i].equals("1")){
    		title="server1";
    		msg="error s1.message1";
    		checkmsg=1;
    		
    	    // GCM 정보 셋팅
    	    GCMVo gcmVo = new GCMVo();
    	    gcmVo.setTitle(title);
    	    gcmVo.setMsg(msg);
    	    gcmVo.setTypeCode("");
    	    
    	    // GCM reg id 셋팅
    	    List<String> reslist = new ArrayList<String>();
    	    if(device_flag1==1)
    	    	reslist.add(dev[0]);	    
    	    if(device_flag2==1)
    	    	reslist.add(dev[1]);
    	    if(device_flag3==1)
    	    	reslist.add(dev[2]);
    	    
    	    GCMUtil gcmUtil = new GCMUtil(reslist, gcmVo);
    	    
    	   for(int j=0; j<gcmUtil.rtnList.size(); j++){
    	      GCMVo rtnGcmVo = gcmUtil.rtnList.get(j); 
    	   } 
    	}
    	if(str[i].equals("2")){
    		title="server1";
    		msg="error s1.message2";
    		checkmsg=1;
    		
    	    // GCM 정보 셋팅
    	    GCMVo gcmVo = new GCMVo();
    	    gcmVo.setTitle(title);
    	    gcmVo.setMsg(msg);
    	    gcmVo.setTypeCode("");
    	    
    	    // GCM reg id 셋팅
    	    List<String> reslist = new ArrayList<String>();
    	    if(device_flag1==1)
    	    	reslist.add(dev[0]);	    
    	    if(device_flag2==1)
    	    	reslist.add(dev[1]);
    	    if(device_flag3==1)
    	    	reslist.add(dev[2]);
    	    
    	    GCMUtil gcmUtil = new GCMUtil(reslist, gcmVo);
    	    
    	   for(int j=0; j<gcmUtil.rtnList.size(); j++){
    	      GCMVo rtnGcmVo = gcmUtil.rtnList.get(j); 
    	   } 
    	}
    	if(str[i].equals("3")){
    		title="server1";
    		msg="error s1.message3";
    		checkmsg=1;
    		
    	    // GCM 정보 셋팅
    	    GCMVo gcmVo = new GCMVo();
    	    gcmVo.setTitle(title);
    	    gcmVo.setMsg(msg);
    	    gcmVo.setTypeCode("");
    	    
    	    // GCM reg id 셋팅
    	    List<String> reslist = new ArrayList<String>();
    	    if(device_flag1==1)
    	    	reslist.add(dev[0]);	    
    	    if(device_flag2==1)
    	    	reslist.add(dev[1]);
    	    if(device_flag3==1)
    	    	reslist.add(dev[2]);
    	    
    	    GCMUtil gcmUtil = new GCMUtil(reslist, gcmVo);
    	    
    	   for(int j=0; j<gcmUtil.rtnList.size(); j++){
    	      GCMVo rtnGcmVo = gcmUtil.rtnList.get(j); 
    	   } 
    	}
    	if(str[i].equals("4")){
    		title="server2";
    		msg="error s2.message1";
    		checkmsg=1;
    		
    	    // GCM 정보 셋팅
    	    GCMVo gcmVo = new GCMVo();
    	    gcmVo.setTitle(title);
    	    gcmVo.setMsg(msg);
    	    gcmVo.setTypeCode("");
    	    
    	    // GCM reg id 셋팅
    	    List<String> reslist = new ArrayList<String>();
    	    if(device_flag1==1)
    	    	reslist.add(dev[0]);	    
    	    if(device_flag2==1)
    	    	reslist.add(dev[1]);
    	    if(device_flag3==1)
    	    	reslist.add(dev[2]);
    	    
    	    GCMUtil gcmUtil = new GCMUtil(reslist, gcmVo);
    	    
    	   for(int j=0; j<gcmUtil.rtnList.size(); j++){
    	      GCMVo rtnGcmVo = gcmUtil.rtnList.get(j); 
    	   } 
    	}
    	if(str[i].equals("5")){
    		title="server2";
    		msg="error s2.message2";
    		checkmsg=1;
    		
    	    // GCM 정보 셋팅
    	    GCMVo gcmVo = new GCMVo();
    	    gcmVo.setTitle(title);
    	    gcmVo.setMsg(msg);
    	    gcmVo.setTypeCode("");
    	    
    	    // GCM reg id 셋팅
    	    List<String> reslist = new ArrayList<String>();
    	    if(device_flag1==1)
    	    	reslist.add(dev[0]);	    
    	    if(device_flag2==1)
    	    	reslist.add(dev[1]);
    	    if(device_flag3==1)
    	    	reslist.add(dev[2]);
    	    
    	    GCMUtil gcmUtil = new GCMUtil(reslist, gcmVo);
    	    
    	   for(int j=0; j<gcmUtil.rtnList.size(); j++){
    	      GCMVo rtnGcmVo = gcmUtil.rtnList.get(j); 
    	   } 
    	}
    	if(str[i].equals("6")){
    		title="server2";
    		msg="error s2.message3";
    		checkmsg=1;
    		
    	    // GCM 정보 셋팅
    	    GCMVo gcmVo = new GCMVo();
    	    gcmVo.setTitle(title);
    	    gcmVo.setMsg(msg);
    	    gcmVo.setTypeCode("");
    	    
    	    // GCM reg id 셋팅
    	    List<String> reslist = new ArrayList<String>();
    	    if(device_flag1==1)
    	    	reslist.add(dev[0]);	    
    	    if(device_flag2==1)
    	    	reslist.add(dev[1]);
    	    if(device_flag3==1)
    	    	reslist.add(dev[2]);
    	    
    	    GCMUtil gcmUtil = new GCMUtil(reslist, gcmVo);
    	    
    	   for(int j=0; j<gcmUtil.rtnList.size(); j++){
    	      GCMVo rtnGcmVo = gcmUtil.rtnList.get(j); 
    	   } 
    	}
    	if(str[i].equals("7")){
    		title="server3";
    		msg="error s3.message1";
    		checkmsg=1;
    		
    	    // GCM 정보 셋팅
    	    GCMVo gcmVo = new GCMVo();
    	    gcmVo.setTitle(title);
    	    gcmVo.setMsg(msg);
    	    gcmVo.setTypeCode("");
    	    
    	    // GCM reg id 셋팅
    	    List<String> reslist = new ArrayList<String>();
    	    if(device_flag1==1)
    	    	reslist.add(dev[0]);	    
    	    if(device_flag2==1)
    	    	reslist.add(dev[1]);
    	    if(device_flag3==1)
    	    	reslist.add(dev[2]);
    	    
    	    GCMUtil gcmUtil = new GCMUtil(reslist, gcmVo);
    	    
    	   for(int j=0; j<gcmUtil.rtnList.size(); j++){
    	      GCMVo rtnGcmVo = gcmUtil.rtnList.get(j); 
    	   } 
    	}
    	if(str[i].equals("8")){
    		title="server3";
    		msg="error s3.message2";
    		checkmsg=1;
    		
    	    // GCM 정보 셋팅
    	    GCMVo gcmVo = new GCMVo();
    	    gcmVo.setTitle(title);
    	    gcmVo.setMsg(msg);
    	    gcmVo.setTypeCode("");
    	    
    	    // GCM reg id 셋팅
    	    List<String> reslist = new ArrayList<String>();
    	    if(device_flag1==1)
    	    	reslist.add(dev[0]);	    
    	    if(device_flag2==1)
    	    	reslist.add(dev[1]);
    	    if(device_flag3==1)
    	    	reslist.add(dev[2]);
    	    
    	    GCMUtil gcmUtil = new GCMUtil(reslist, gcmVo);
    	    
    	   for(int j=0; j<gcmUtil.rtnList.size(); j++){
    	      GCMVo rtnGcmVo = gcmUtil.rtnList.get(j); 
    	   } 
    	}
    	if(str[i].equals("9")){
    		title="server3";
    		msg="error s3.message3";
    		checkmsg=1;
    		
    	    // GCM 정보 셋팅
    	    GCMVo gcmVo = new GCMVo();
    	    gcmVo.setTitle(title);
    	    gcmVo.setMsg(msg);
    	    gcmVo.setTypeCode("");
    	    
    	    // GCM reg id 셋팅
    	    List<String> reslist = new ArrayList<String>();
    	    if(device_flag1==1)
    	    	reslist.add(dev[0]);	    
    	    if(device_flag2==1)
    	    	reslist.add(dev[1]);
    	    if(device_flag3==1)
    	    	reslist.add(dev[2]);
    	    
    	    GCMUtil gcmUtil = new GCMUtil(reslist, gcmVo);
    	    
    	   for(int j=0; j<gcmUtil.rtnList.size(); j++){
    	      GCMVo rtnGcmVo = gcmUtil.rtnList.get(j); 
    	   } 
    	}
    	if(checkmsg==1)
    		stat.executeUpdate("INSERT INTO test1(server,msg,date)  VALUES('"+title+"','"+msg+"','"+today+"')");

    }
	
	stat.close();
	conn.close();
	

}catch(Exception e){     // 예외 처리
	e.printStackTrace();
}
    
    
 %>
