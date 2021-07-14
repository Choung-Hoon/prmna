<%@ page contentType="text/html;charset=utf-8" %>

<%@ page import="lee.common.util.MD5Cryptor" %>
<%@ page import="java.io.*" %>

<%@ include file="../common/inc_import_00.jsp" %>
<%@ page errorPage="../common/error.jsp" %>
<%@ include file="../common/_includeInit.inc" %>
<%@ include file="../common/_includeCommon.inc" %>
<%@ include file="../common/_checkLogin.inc" %>


<%
try {
  	String jsonParam = request.getParameter( "json" );
  	//System.out.println("jsonParam : " + jsonParam);

  	// 파일수정 F:\git_repositories\c123\WebContent\js\search_filter.json 을 받아오 json으로 변경
  	String filePath = request.getSession().getServletContext().getRealPath("/")+"js" + System.getProperty("file.separator") + "search_filter.json";
  	
  	
  	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8"));
  	writer.write(jsonParam);
  	writer.close();

  	//System.out.println("File Path : " + filePath);
	
	out.println("완료했습니다.");

} catch(Exception e) {
	e.printStackTrace();
}
%>
