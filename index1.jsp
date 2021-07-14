<%@ page contentType="text/html;charset=utf-8" %>

<%@ page import="java.io.*" %> 
<%@ page import="java.nio.charset.Charset" %> 
<%@ page import="com.ibatis.common.resources.Resources" %> 
<%@ page import="com.ibatis.sqlmap.client.SqlMapClient" %> 
<%@ page import="com.ibatis.sqlmap.client.SqlMapClientBuilder" %> 

<%
  //MgrFactory mgr = MgrFactory.getMgrFactory();
  out.println("HIS");
  
  // 현재 로그인된 관리자 세션 정보
  //Admin curAdminInfo = (Admin)session.getAttribute(ADMIN_KEY);
%>

<html>
<title> 관리자 로그인</title>
<script type="text/javascript" src="../share/js/cookie.js"></script>
<script language="javascript">
</script> 
<body onLoad="fnInit();">
<form name="myform" method="post">

</form>
</body>
</html>

<%!
static {
    try {
      String strResourceAres = "map/sqlMapConfig_lee.xml";

      Resources.setCharset(Charset.forName("UTF-8"));

      Reader readerAres = Resources.getResourceAsReader(strResourceAres);

      SQL_MAP_CLIENT_MDWEB = SqlMapClientBuilder.buildSqlMapClient(readerAres);
    } catch (Exception e) {
      System.out.println(e);
      throw new RuntimeException("Description. Cause: " + e, e);
    }
  }

%>