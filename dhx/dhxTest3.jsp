﻿<%@ page contentType="text/html;charset=utf-8" %>

<%@ page errorPage="../common/error.jsp" %>
<%@ include file="../include/global_head.jsp" %>

<%@ include file="../common/_includeInit.inc" %>
<%@ include file="../common/_includeCommon.inc" %>
<%@ include file="../common/_checkLogin.inc" %>
<%@include file="../common/inc_dhxgrid_00.jsp" %>


<script type="text/javascript">


</script>

<body>

<!--
<link rel="stylesheet" type="text/css" href="<%=ConstantUtil.SERVER_HOST%>/dhx/skins/dhtmlxgrid_dhx_skyblue.css">
<link rel="STYLESHEET" type="text/css" href="<%=ConstantUtil.SERVER_HOST%>/dhx/dhtmlxcombo.css">
<script  src="<%=ConstantUtil.SERVER_HOST%>/dhx/dhtmlxcommon.js"></script>
<script  src="<%=ConstantUtil.SERVER_HOST%>/dhx/dhtmlxgrid.js"></script>
<script  src="<%=ConstantUtil.SERVER_HOST%>/dhx/ext/dhtmlxgrid_srnd.js"></script>
<script  src="<%=ConstantUtil.SERVER_HOST%>/dhx/ext/dhtmlxgrid_filter.js"></script>
<script  src="<%=ConstantUtil.SERVER_HOST%>/dhx/dhtmlxgridcell.js"></script>
<script  src="<%=ConstantUtil.SERVER_HOST%>/dhx/dhtmlxcombo.js"></script>
-->

<div id="wrap">

 	<%@ include file="../include/top_navi.jsp" %>
	<hr />
	<div id="container">
		<%@ include file="../include/left.jsp" %>
		<div class="contents_wrap">

			<div class="location">
				<h1>DHX테스트3</h1>
				<p><a href="main.jsp">홈</a> &gt; <strong>DHX</strong></p>
			</div>

			<div class="contents">


				<div id="gridbox" style="width: 1200px; height: 500px; background-color:white;"></div>

<script>dhx_globalImgPath = "<%=ConstantUtil.SERVER_HOST%>/dhx/dhtmlxCombo/imgs/";
mygrid = new dhtmlXGridObject('gridbox');
mygrid.setImagePath("<%=ConstantUtil.SERVER_HOST%>/dhx/imgs/");
mygrid.setHeader("Sales,Book Title,Author,Price,In Store,Date of Publication");
//mygrid.attachHeader("&nbsp;,#combo_filter,#combo_filter,&nbsp;,#cspan,#cspan");
mygrid.attachHeader("&nbsp;,#select_filter,#combo_filter,&nbsp;,#cspan,#combo_filter");
mygrid.setInitWidths("80,150,120,80,80,220");
mygrid.enableAutoWidth(true);
mygrid.setColAlign("right,left,left,right,center,center");
mygrid.setColTypes("dyn,ed,ed,price,ch,ro");
mygrid.setColSorting("int,str,str,int,str,str");
mygrid.setSkin("dhx_skyblue");
mygrid.init();
mygrid.enableSmartRendering(true);
//mygrid.loadXML("<%=ConstantUtil.SERVER_HOST%>/dhx/sample/500.xml");
mygrid.load("<%=ConstantUtil.SERVER_HOST%>/dhx/sample/data.json","json");
//mygrid.load("<%=ConstantUtil.SERVER_HOST%>/dhx/sample/samle_data.json","json");

/*
var js={
    rows:[
        { id:1001,
     data:[
          "100",
          "A Time to Kill",
          "John Grisham",
          "12.99",
          "1",
          "05/01/1998"] },
       { id:1002,
     data:[
          "1000",
          "Blood and Smoke",
          "Stephen King",
          "0",
          "1",
          "01/01/2000"] }
    ]}
mygrid.parse(js,"json");
*/
</script>

			</div>
		</div>
	</div>
	<hr />
</div>
</body>
</html>