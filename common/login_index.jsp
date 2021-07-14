﻿<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%@ page import="lee.common.config.ConstantUtil" %>
<%@ page import="lee.common.config.PropertyManager" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr" />
<meta name="subject" content=" 관리자모드" />
<meta name="title" content=" 관리자모드페이지" />
<meta name="keywords" content=", 관리자모드" />
<meta name="date" content="2011-08-11" />
<title> <%=ConstantUtil.HTML_TITLE%></title>
<link rel="stylesheet" type="text/css" href="<%=ConstantUtil.SERVER_HOST%>/share/css/base.css" />
<link rel="stylesheet" type="text/css" href="<%=ConstantUtil.SERVER_HOST%>/share/css/admin.css" />
<script type="text/javascript"  src="<%=ConstantUtil.SERVER_HOST%>/share/js/common.js"></script>
<script language="javascript" type="text/javascript"  src="<%=ConstantUtil.SERVER_HOST%>/js/jquery-1.5.2.min.js"></script>
<script language="javascript" type="text/javascript"  src="<%=ConstantUtil.SERVER_HOST%>/js/common.js"></script>

<script language="javascript">
<!--

$(document).ready(function() {
	focus();
	//if (getCookie('popup_stop_donate') != 'no')	document.onload = open_win();
	//if (getCookie('popup_donate') != 'no')	document.onload = open_win();
	//if (getCookie('popup_notice_20120329') != 'no')	document.onload = open_win1();
	//2012.07.16
	//if (getCookie('popup_stop_donate') != 'no')	document.onload = open_win();
});

function open_win(){
	//window.open('../../popup/popup_donate.jsp','popup2', 'width=380, height=264,left=275,top=10,toolbar=no, location=no, directories=no, status=no, menubar=no, resizable=no, scrollbars=no, copyhistory=no');
	window.open('../../popup/popup_stop_donate.jsp','popup2', 'width=380, height=400,left=275,top=10,toolbar=no, location=no, directories=no, status=no, menubar=no, resizable=no, scrollbars=no, copyhistory=no');
}

function open_win1(){
	window.open('../../popup/popup_notice_20120329.jsp','popup3', 'width=550, height=405,left=660,top=10,toolbar=no, location=no, directories=no, status=no, menubar=no, resizable=no, scrollbars=no, copyhistory=no');
}

function focus()
{
	$("#userid").focus();
	if(Common.isValue($("#userid").val())){
		$("#userpw").focus();
	}
}

function keyEvent(){
	if(event.keyCode == '13'){
		form_check();
	}
}

function enter()
{
	if(event.keyCode==13) form_check();
}

function form_check()
{
	if(!Common.isValue($("#userid").val()))
	{
		alert("아이디를 입력하세요.");
		$("#userid").focus();
		return;
	}

	if( !Common.isValue($("#userpw").val()) )
	{
		alert("비밀번호를 입력하세요.");
		$("#userpw").focus();
		return;
	}

	//
	$("#myform").attr("action", "do_login.jsp");
	$("#myform").submit();
}

//-->
</script>
</head>

<body>
	<form name="myform" id="myform" method="post" class="form_login">
	<input type="hidden" id="MAC" name="MAC" value="<%=request.getParameter("MAC") == null ? "" : request.getParameter("MAC")%>">
		<fieldset>
			<legend>사용자 로그인</legend>
			<p class="form_legend">사용자 로그인</p>
			<div>
				<p><strong> 사용자 모드</strong><br />시스템 관련 문의 :  <strong class="blue_color"></strong><br />(Tel : )</p>
				<ul>
					<li><label for="Login_ID">아이디 :</label><input type="text" name="userid" id="userid" value="" autocomplete=off tabindex="1" class="text_input" /></li>
					<li><label for="Login_PW">패스워드 :</label><input type="password" name="userpw" id="userpw" value="" onKeydown="enter();" autocomplete=off tabindex="2" class="text_input" /></li>
					<li class="login_btn">
						<a href="javascript:form_check();">
							<img  src="<%=ConstantUtil.SERVER_HOST%>/images/admin/btn_adminLogin.gif" alt="로그인" />
						</a>
					</li>
				</ul>
			</div>
		</fieldset>
	</form>
</body>
</html>

</html>