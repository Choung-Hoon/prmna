<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ page import="kr.go.seoul.ssf.manager.*" %>
<%@ page import="java.util.List" %>

<%
	MgrFactory mgr = MgrFactory.getMgrFactory();
	BoardMgr boardMgr = mgr.getBoardMgr	();
	List list = boardMgr.getTestList();
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
list count : <%=list.size() %>
</body>
</html>