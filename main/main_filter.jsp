<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.io.*" %>
<%@ page import="org.json.simple.parser.*" %>
<%@ page errorPage="../common/error.jsp"%>
<%@ include file="../common/inc_import_00.jsp"%>
<%@ include file="../common/_includeInit.inc"%>
<%@ include file="../common/_includeCommon.inc"%>
<%@ include file="../common/_checkLogin.inc"%>
<%@ include file="../include/global_head.jsp"%>
<%
Object jsonData = null;

try {
  	String filePath = request.getSession().getServletContext().getRealPath("/")+"js" + System.getProperty("file.separator") + "search_filter.json";
  	System.out.println("filePath : " + filePath);
  	
  	BufferedReader reader  =  new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
  	
//   	FileReader reader = new FileReader(filePath);
    JSONParser jsonParser = new JSONParser();
    
    jsonData = jsonParser.parse(reader);
    
    //System.out.println("obj : " + jsonData);
  	
} catch(Exception e) {
	e.printStackTrace();
}
%>

<script type="text/javascript">


$(document).ready( function() {
	var data = JSON.parse('<%=jsonData%>');
	
	for (var i=0; i<46; i++) {
		//console.log($("#"+data.ids[i].id).val());
		//console.log(data.ids[i].value, data.ids[i].text);
		
		$("#"+data.ids[i].id).val( (data.ids[i].value == undefined ? data.ids[i].text : data.ids[i].value));
		$("#"+data.ids[i].id)[0].nextSibling.nodeValue = data.ids[i].text;	
	}
	
	$('.search_con').click(function() {
		//console.log(data);
		var targetID = $(this).find('input')[0].id;
		
		//console.log("input id : " + targetID);
 	
		var userInput = prompt("새로운 종목명을 입력하세요!","종목명");
 	
		if (userInput) {
			data.ids.filter(function(obj) {
				
				if (obj["id"] === targetID) {
					obj["text"] = userInput;
				}
			});
			
			var jsonData = JSON.stringify(data);
			
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "saveJson.jsp",
				data : {json : jsonData},
				dataType : "text",
				success : function(data) {
					//alert("연결 성공");
					window.location.reload();
				},
				error : function(e) {
					alert('서버 연결 도중 에러가 났습니다. 다시 시도해 주십시오.');
				}
			});
		}
	});
	
	
	/*
	$.ajax({
		url: "<%=ConstantUtil.SERVER_HOST%>/js/search_filter.json",
		dataType: 'json',
		success: function( data ) {
			alert( "SUCCESS:  " + data );
		},
		error: function( data ) {
			alert( "ERROR:  " + data );
		}
	});
	
	$.getJSON("<%=ConstantUtil.SERVER_HOST%>/js/search_filter.json", function(data) {
	      
		for (var i=0; i<46; i++) {
			console.log($("#"+data.ids[i].id).val());
			$("#"+data.ids[i].id).val(data.ids[i].text);
			$("#"+data.ids[i].id)[0].nextSibling.nodeValue = data.ids[i].text;		
		}
		
		$('.search_con').click(function() {
			//console.log(data);
			var targetID = $(this).find('input')[0].id;
			console.log("input id : " + targetID);
	 	
			var userInput = prompt("새로운 종목명을 입력하세요!");
	 	
			if (userInput) {
				data.ids.filter(function(obj) {
					
					if (obj["id"] === targetID) {
						obj["text"] = userInput;
					}
				});
				
				var jsonData = JSON.stringify(data);
				
				$.ajax({
					type : "POST",
					dataType : "json",
					url : "saveJson.jsp",
					data : {json : jsonData},
					dataType : "text",
					success : function(data) {
						alert("연결 성공");
					},
					error : function(e) {
						alert('서버 연결 도중 에러가 났습니다. 다시 시도해 주십시오.');
						console.log(e);
					}
				});		
		 	
			//window.location.reload();
			}
		});
	}); 
	*/
		
});
</script>

<body>
	<div id="wrap">
		<%@ include file="../include/top_navi.jsp"%>
		<hr />
		<div id="container">
			<%@ include file="../include/left.jsp"%>
			<div class="contents_wrap">
				<div class="location">
					<h1>검색조건 관리 - 수정할 종목을 클릭하세요</h1>
					<p>
						<a href="main.jsp">검색조건 관리</a> &gt; <strong>메인</strong>
					</p>
				</div>
				<div class="contents">
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="27" align="left" valign="top">&nbsp;</td>
							<td>
								<!-- 컨텐츠 시작-->
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<!-- 조회테이블 시작-->
											<table width="100%" border="0" cellspacing="0"
												cellpadding="9">
												<tr>
													<td class="search">
														<table width="760" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr id="tr_1">
																<td width="100" class="search_li" bgcolor="#EBF3F5">일반건설</td>
																<td class="search_con"><input ID="CONDITON"
																	NAME="CONDITON" type="hidden" value="전체"> <font
																	color=blue><b>전체</b> </font></td>
																<td class="search_con" click>
																	<input ID="CONDITON_1" NAME="CONDITON_1" type="hidden" value="토건">토건
																</td>
																<td class="search_con"><input ID="CONDITON_2"
																	NAME="CONDITON_2" type="hidden" value="토목">토목</td>
																<td class="search_con"><input ID="CONDITON_3"
																	NAME="CONDITON_3" type="hidden" value="건축">건축</td>
																<td class="search_con"><input ID="CONDITON_4"
																	NAME="CONDITON_4" type="hidden" value="조경">조경</td>
																<td class="search_con"><input ID="CONDITON_5"
																	NAME="CONDITON_5" type="hidden" value="산업설비">산업설비</td>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td colspan="8" bgcolor="cccccc" height="1"></td>
															</tr>
															<tr id="tr_2">
																<td width="100" class="search_li" rowspan="5"
																	bgcolor="#EBF3F5">전문건설</td>
																<td class="search_con"><input ID="CONDITON_6"
																	NAME="CONDITON_6" type="hidden" value="가스">가스</td>
																<td class="search_con"><input ID="CONDITON_7"
																	NAME="CONDITON_7" type="hidden" value="도장">도장</td>
																<td class="search_con"><input ID="CONDITON_8"
																	NAME="CONDITON_8" type="hidden" value="상하">상하</td>
																<td class="search_con"><input ID="CONDITON_9"
																	NAME="CONDITON_9" type="hidden" value="실내건축">실내건축</td>
																<td class="search_con"><input ID="CONDITON_10"
																	NAME="CONDITON_10" type="hidden" value="철강">철강</td>
																<td class="search_con"><input ID="CONDITON_11"
																	NAME="CONDITON_11" type="hidden" value="단종신규">단종신규</td>
															</tr>
															<tr id="tr_3">
																<td class="search_con"><input ID="CONDITON_12"
																	NAME="CONDITON_12" type="hidden" value="강구조">강구조</td>
																<td class="search_con"><input ID="CONDITON_13"
																	NAME="CONDITON_13" type="hidden" value="미방">미방</td>
																<td class="search_con"><input ID="CONDITON_14"
																	NAME="CONDITON_14" type="hidden" value="석공">석공</td>
																<td class="search_con"><input ID="CONDITON_15"
																	NAME="CONDITON_15" type="hidden" value="조경시설">조경시설</td>
																<td class="search_con"><input ID="CONDITON_16"
																	NAME="CONDITON_16" type="hidden" value="철도">철도</td>
																<td class="search_con">&nbsp;</td>
															</tr>
															<tr id="tr_4">
																<td class="search_con"><input ID="CONDITON_17"
																	NAME="CONDITON_17" type="hidden" value="금속">금속</td>
																<td class="search_con"><input ID="CONDITON_18"
																	NAME="CONDITON_18" type="hidden" value="보링">보링</td>
																<td class="search_con"><input ID="CONDITON_19"
																	NAME="CONDITON_19" type="hidden" value="수중">수중</td>
																<td class="search_con"><input ID="CONDITON_20"
																	NAME="CONDITON_20" type="hidden" value="조경식재">조경식재</td>
																<td class="search_con"><input ID="CONDITON_21"
																	NAME="CONDITON_21" type="hidden" value="철콘">철콘</td>
																<td class="search_con">&nbsp;</td>
															</tr>
															<tr id="tr_5">
																<td class="search_con"><input ID="CONDITON_22"
																	NAME="CONDITON_22" type="hidden" value="기계">기계</td>
																<td class="search_con"><input ID="CONDITON_23"
																	NAME="CONDITON_23" type="hidden" value="비계">비계</td>
																<td class="search_con"><input ID="CONDITON_24"
																	NAME="CONDITON_24" type="hidden" value="승강기">승강기</td>
																<td class="search_con"><input ID="CONDITON_25"
																	NAME="CONDITON_25" type="hidden" value="준설">준설</td>
																<td class="search_con"><input ID="CONDITON_26"
																	NAME="CONDITON_26" type="hidden" value="토공">토공</td>
																<td class="search_con">&nbsp;</td>
															</tr>
															<tr id="tr_6">
																<td class="search_con"><input ID="CONDITON_27"
																	NAME="CONDITON_27" type="hidden" value="난방">난방</td>
																<td class="search_con"><input ID="CONDITON_28"
																	NAME="CONDITON_28" type="hidden" value="삭도">삭도</td>
																<td class="search_con"><input ID="CONDITON_29"
																	NAME="CONDITON_29" type="hidden" value="시설물">시설물</td>
																<td class="search_con"><input ID="CONDITON_30"
																	NAME="CONDITON_30" type="hidden" value="지붕판금">지붕판금</td>
																<td class="search_con"><input ID="CONDITON_31"
																	NAME="CONDITON_31" type="hidden" value="포장">포장</td>
																<td class="search_con">&nbsp;</td>
															</tr>
															<tr>
																<td colspan="8" bgcolor="cccccc" height="1"></td>
															</tr>
															<tr id="tr_7">
																<td width="100" class="search_li" rowspan="3"
																	bgcolor="#EBF3F5">건설관련</td>
																<td class="search_con"><input ID="CONDITON_32"
																	NAME="CONDITON_32" type="hidden" value="전기">전기</td>
																<td class="search_con"><input ID="CONDITON_33"
																	NAME="CONDITON_33" type="hidden" value="통신">통신</td>
																<td class="search_con"><input ID="CONDITON_34"
																	NAME="CONDITON_34" type="hidden" value="소방">소방</td>
																<td class="search_con"><input ID="CONDITON_35"
																	NAME="CONDITON_35" type="hidden" value="지하수">지하수</td>
																<td class="search_con"><input ID="CONDITON_36"
																	NAME="CONDITON_36" type="hidden" value="주택건설">주택건설</td>
																<td class="search_con"><input ID="CONDITON_37"
																	NAME="CONDITON_37" type="hidden" value="대지조성">대지조성</td>
																<td class="search_con">&nbsp;</td>
															</tr>
															<tr id="tr_8">
																<td class="search_con"><input ID="CONDITON_38"
																	NAME="CONDITON_38" type="hidden" value="5년법인">5년법인</td>
																<td class="search_con"><input ID="CONDITON_39"
																	NAME="CONDITON_39" type="hidden" value="일반법인">일반법인</td>
																<td class="search_con"><input ID="CONDITON_40"
																	NAME="CONDITON_40" type="hidden" value="공법인">공법인</td>
																<td class="search_con"><input ID="CONDITON_41"
																	NAME="CONDITON_41" type="hidden" value="부동산개발">부동산개발</td>
																<td class="search_con"><input ID="CONDITON_42"
																	NAME="CONDITON_42" type="hidden" value="전문1종목">전문1종목</td>
																<td class="search_con"><input ID="CONDITON_43"
																	NAME="CONDITON_43" type="hidden" value="숲가꾸기">숲가꾸기</td>
																<td class="search_con">&nbsp;</td>
															</tr>
															<tr id="tr_9">
																<td class="search_con"><input ID="CONDITON_44"
																	NAME="CONDITON_44" type="hidden" value="산림토목">산림토목</td>
																<td class="search_con"><input ID="CONDITON_45"
																	NAME="CONDITON_45" type="hidden" value="산림사업">산림사업</td>
																<td class="search_con"><input ID="CONDITON_46"
																	NAME="CONDITON_46" type="hidden" value="도시림">도시림</td>
																<td class="search_con" colspan="4">&nbsp;</td>

															</tr>
														</table>
													</td>
												</tr>
											</table> <!-- 조회테이블 끝-->
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<hr />
	</div>