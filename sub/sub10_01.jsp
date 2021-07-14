﻿<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="lee.common.config.ConstantUtil" %>
<%@ page import="lee.common.config.PropertyManager" %>
<%@ page import="lee.common.config.ConfigManager" %>

<%@ page import="lee.common.util.StringUtil" %>
<%@ page import="lee.manager.MgrFactory" %>
<%@ page import="lee.manager.ExcelDataMgr" %>
<%@ page import="lee.bean.ExcelTitle" %>
<%@ page import="java.util.HashMap" %>


<%@ page import="java.io.*" %>
<%@ page import="org.json.simple.parser.*" %>

<%@ include file="../common/inc_import_00.jsp" %>

<%
	// 검색 조건 추가
	String searchValue = request.getParameter( "searchValue" );
	if( searchValue == null || searchValue.equals("null") ) searchValue = "";

	String isChromeBrowser = request.getParameter( "isChromeBrowser" );
	if( isChromeBrowser == null || isChromeBrowser.equals("null") ) isChromeBrowser = "N";
	
	System.out.println("============= isChromeBrowser :  " + isChromeBrowser);
	
	//그리드 타이틀 DB에서 읽어 오는 방식으로 변경 함
	MgrFactory mgr = MgrFactory.getMgrFactory();
	ExcelDataMgr excelDataMgr = mgr.getExcelDataMgr();
	HashMap params = new HashMap();
	ExcelTitle excelTitle = excelDataMgr.getExcelTitle(params);

	// 공지사항 읽어오기
	HashMap param = new HashMap();
	param.put( "userid", "root" );

	AdminMgr adminMgr = mgr.getAdminMgr();
	Admin admin = adminMgr.getAdmin( param );
	String informContent = admin.getInformContent();
	
	Object jsonData = null;

	try {
	  	String filePath = request.getSession().getServletContext().getRealPath("/")+"js" + System.getProperty("file.separator") + "search_filter.json";
	  	System.out.println("filePath : " + filePath);
	  	
	  	BufferedReader reader  =  new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
	  	
//	   	FileReader reader = new FileReader(filePath);
	    JSONParser jsonParser = new JSONParser();
	    
	    jsonData = jsonParser.parse(reader);
	    
	    System.out.println("obj : " + jsonData);
	  	
	} catch(Exception e) {
		e.printStackTrace();
	}
%>

<link rel="stylesheet" type="text/css" href="<%=ConstantUtil.SERVER_HOST%>/dhx/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css">
<script type="text/javascript"  src="<%=ConstantUtil.SERVER_HOST%>/share/js/common.js"></script>
<!--script language="javascript" type="text/javascript"  src="<%=ConstantUtil.SERVER_HOST%>/js/jquery.blockUI.js"></script-->
<script language="javascript" type="text/javascript"  src="<%=ConstantUtil.SERVER_HOST%>/js/common.js"></script>

<%
	//2014.12.23, chrome과  mobile에서 dhxgrid오류가 발행하여 분기 시킴	
	if ("Y".equals(isChromeBrowser)) {
%>
<%@include file="../common/inc_dhxgrid_00_chrome.jsp" %>
<%
	} else {
%>
<%@include file="../common/inc_dhxgrid_00.jsp" %>
<%
	} 
%>

<table border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="27" align="left" valign="top">&nbsp;</td>
			<td>
		<!-- 컨텐츠 시작-->
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<!-- 제목&페이지 위치-->
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<td>
									  <table  border="0" cellspacing="0" cellpadding="0">
  										<tr>
  										  <td>
      									<b>※ 체크박스에서 업종명을 선택 후, 아래의 [검색하기]버튼을 클릭하시면, 검색결과를 확인 할 수 있습니다.</b>
      									</td>
      								</tr>
										</table>
										</td>
									<td valign="bottom" class="btnLine">
										<!-- 버튼 시작 -->
										<table border="0" align="right" cellpadding="0" cellspacing="0">
											<tr>
												<!-- 등록 버튼 시작 -->
												<!-- 등록 버튼 끝 -->
											</tr>
										</table>
										<!-- 버튼 끝 -->
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<!-- 조회테이블 시작-->
							<table width="100%" border="0" cellspacing="0" cellpadding="9">
			                    <tr>
			                        <td class="search">
			                            <table width="900" border="0" align="center" cellpadding="0" cellspacing="0">
			                                <tr>
			                                    <td width="140" class="search_li" bgcolor="#EBF3F5">일반건설</td>
			                                    <td class="search_con"><input ID="CONDITON" NAME="CONDITON" type="checkbox" value="전체" ><font color=blue><b>전체</b></font></td>
			                                    <td class="search_con"><input ID="CONDITON_1" NAME="CONDITON_1" type="checkbox" value="토건" >토건</td>
			                                    <td class="search_con"><input ID="CONDITON_2" NAME="CONDITON_2" type="checkbox" value="토목" >토목</td>
			                                    <td class="search_con"><input ID="CONDITON_3" NAME="CONDITON_3" type="checkbox" value="건축" >건축</td>
			                                    <td class="search_con"><input ID="CONDITON_4" NAME="CONDITON_4" type="checkbox" value="조경" >조경</td>
			                                    <td class="search_con"><input ID="CONDITON_5" NAME="CONDITON_5" type="checkbox" value="산업설비" >산업설비</td>
											</tr>
											<tr>
			                                    <td colspan="7" bgcolor="cccccc" height="1"></td>
											</tr>
											<tr>
			                                    <td width="140" class="search_li" bgcolor="#EBF3F5">지반조성</br>포장공사업</td>
			                                    <td class="search_con"><input ID="CONDITON_6" NAME="CONDITON_6" type="checkbox" value="토공" >토공</td>
			                                    <td class="search_con"><input ID="CONDITON_8" NAME="CONDITON_8" type="checkbox" value="포장" >포장</td>
			                                    <td class="search_con"><input ID="CONDITON_18" NAME="CONDITON_18" type="checkbox" value="보링" >보링</td>
												<td width="140" class="search_li" bgcolor="#EBF3F5">금속창호</br>지붕건축물조립공사업</td>
			                                    <td class="search_con"><input ID="CONDITON_27"	NAME="CONDITON_27"	type="checkbox" value="금속" >금속</td>
			                                    <td class="search_con"><input ID="CONDITON_25"	NAME="CONDITON_25"	type="checkbox" value="지붕판금" >지붕판금</td>
											</tr>
											<tr>
			                                    <td colspan="7" bgcolor="cccccc" height="1"></td>
											</tr>											
											<tr>
			                                    <td width="140" class="search_li" bgcolor="#EBF3F5">철근콘크리트 공사업</td>
			                                    <td class="search_con"><input ID="CONDITON_12" NAME="CONDITON_12" type="checkbox" value="철콘" >철콘</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td width="140" class="search_li" bgcolor="#EBF3F5">실내건축공사업</td>
												<td class="search_con"><input ID="CONDITON_28"	NAME="CONDITON_28"	type="checkbox" value="실내건축" >실내건축</td>
												<td>&nbsp;</td>
											</tr>											
											<tr>
			                                    <td colspan="7" bgcolor="cccccc" height="1"></td>
											</tr>
											<tr>
			                                    <td width="140" class="search_li" bgcolor="#EBF3F5">상.하수도 설비공사업</td>
			                                    <td class="search_con"><input ID="CONDITON_17"	NAME="CONDITON_17"	type="checkbox" value="상하" >상하</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td width="140" class="search_li" bgcolor="#EBF3F5">조경식재</br>시설물공사업</td>
												<td class="search_con"><input ID="CONDITON_29" NAME="CONDITON_29" type="checkbox" value="조경식재" >조경식재</td>
												<td class="search_con"><input ID="CONDITON_24" NAME="CONDITON_24" type="checkbox" value="조경시설" >조경시설</td>
											</tr>
											<tr>
			                                    <td colspan="7" bgcolor="cccccc" height="1"></td>
											</tr>											
											<tr>
			                                    <td width="140" class="search_li" bgcolor="#EBF3F5">도장.습식.방수.석공사업</td>
												<td class="search_con"><input ID="CONDITON_22" NAME="CONDITON_22" type="checkbox" value="석공" >석공</td>
												<td class="search_con"><input ID="CONDITON_7"	NAME="CONDITON_7"	type="checkbox" value="도장" >도장</td>												
												<td class="search_con"><input ID="CONDITON_13" NAME="CONDITON_13" type="checkbox" value="미방" >방수</td>
												<td width="140" class="search_li" bgcolor="#EBF3F5">가스난방공사업</td>
												<td class="search_con"><input ID="CONDITON_15" NAME="CONDITON_15" type="checkbox" value="난방" >가스난방</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
			                                    <td colspan="7" bgcolor="cccccc" height="1"></td>
											</tr>											
											<tr>
			                                    <td width="140" class="search_li" bgcolor="#EBF3F5">구조물해체비계공사업</td>
												<td class="search_con"><input ID="CONDITON_23" NAME="CONDITON_23" type="checkbox" value="비계" >비계</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td width="140" class="search_li" bgcolor="#EBF3F5">수중.준설공사업</td>
												<td class="search_con"><input ID="CONDITON_9" NAME="CONDITON_9" type="checkbox" value="수중" >수중</td>
												<td class="search_con"><input ID="CONDITON_30" NAME="CONDITON_30" type="checkbox" value="준설" >준설</td>
											</tr>
											<tr>
			                                    <td colspan="7" bgcolor="cccccc" height="1"></td>
											</tr>											
											<tr>
			                                    <td width="140" class="search_li" bgcolor="#EBF3F5">기계가스설비공사업</td>
												<td class="search_con"><input ID="CONDITON_19" NAME="CONDITON_19" type="checkbox" value="기계" >기계</td>
												<td class="search_con"><input ID="CONDITON_20"	NAME="CONDITON_20"	type="checkbox" value="가스" >가스</td>
												<td>&nbsp;</td>
												<td width="140" class="search_li" bgcolor="#EBF3F5">승강기.삭도공사업</td>
												<td class="search_con"><input ID="CONDITON_26" NAME="CONDITON_26" type="checkbox" value="승강기" >승강기</td>
												<td class="search_con"><input ID="CONDITON_31" NAME="CONDITON_31" type="checkbox" value="삭도" >삭도</td>
											</tr>
											<tr>
			                                    <td colspan="7" bgcolor="cccccc" height="1"></td>
											</tr>											
											<tr>
			                                    <td width="140" class="search_li" bgcolor="#EBF3F5">철도.궤도공사업</td>
												<td class="search_con"><input ID="CONDITON_16" NAME="CONDITON_16" type="checkbox" value="철도" >철도</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td width="140" class="search_li" bgcolor="#EBF3F5">철강구조물 공사업</td>
												<td class="search_con"><input ID="CONDITON_21"	NAME="CONDITON_21"	type="checkbox" value="강구조" >강구조</td>
												<td class="search_con"><input ID="CONDITON_10"	NAME="CONDITON_10"	type="checkbox" value="철강" >철강</td>
											</tr>
											<tr>
			                                    <td colspan="7" bgcolor="cccccc" height="1"></td>
											</tr>											
											<tr>
			                                    <td width="140" class="search_li" bgcolor="#EBF3F5">유지보수</td>
												<td class="search_con"><input ID="CONDITON_14" NAME="CONDITON_14" type="checkbox" value="시설물" >시설물</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td width="140" class="search_li" bgcolor="#EBF3F5">전문공사업</td>
												<td class="search_con"><input ID="CONDITON_11" NAME="CONDITON_11" type="checkbox" value="전문1종목" >전문1종목</td>
			                                    <td class="search_con"><input ID="CONDITON_42" NAME="CONDITON_42" type="checkbox" value="전문2종목" >전문2종목</td>
											</tr>
											<tr>
			                                    <td colspan="7" bgcolor="cccccc" height="1"></td>
											</tr>											
											<tr>
			                                    <td width="140" class="search_li" rowspan="3" bgcolor="#EBF3F5" >건설관련</td>
			                                    <td class="search_con"><input ID="CONDITON_32" NAME="CONDITON_32" type="checkbox" value="전기" >전기</td>
			                                    <td class="search_con"><input ID="CONDITON_33" NAME="CONDITON_33" type="checkbox" value="통신" >통신</td>
			                                    <td class="search_con"><input ID="CONDITON_34" NAME="CONDITON_34" type="checkbox" value="소방" >소방</td>
			                                    <td class="search_con"><input ID="CONDITON_35" NAME="CONDITON_35" type="checkbox" value="지하수" >지하수</td>
			                                    <td class="search_con"><input ID="CONDITON_36" NAME="CONDITON_36" type="checkbox" value="주택건설" >주택건설</td>
			                                    <td class="search_con"><input ID="CONDITON_37" NAME="CONDITON_37" type="checkbox" value="대지조성" >대지조성</td>
			                                    <td class="search_con">&nbsp;</td>					              
											</tr>													              
											<tr>													              
			                                    <td class="search_con"><input ID="CONDITON_38" NAME="CONDITON_38" type="checkbox" value="5년법인" >5년법인</td>
			                                    <td class="search_con"><input ID="CONDITON_39" NAME="CONDITON_39" type="checkbox" value="일반법인" >일반법인</td>
			                                    <td class="search_con"><input ID="CONDITON_40" NAME="CONDITON_40" type="checkbox" value="공법인" >공법인</td>
			                                    <td class="search_con"><input ID="CONDITON_41" NAME="CONDITON_41" type="checkbox" value="부동산개발" >부동산개발</td>												
			                                    <td class="search_con"><input ID="CONDITON_43" NAME="CONDITON_43" type="checkbox" value="숲가꾸기" >숲가꾸기</td>
			                                    <td class="search_con">&nbsp;</td>					              
											</tr>													              
											<tr>													              
			                                    <td class="search_con"><input ID="CONDITON_44" NAME="CONDITON_44" type="checkbox" value="산림토목" >산림토목</td>
			                                    <td class="search_con"><input ID="CONDITON_45" NAME="CONDITON_45" type="checkbox" value="산림사업" >산림사업</td>
			                                    <td class="search_con"><input ID="CONDITON_46" NAME="CONDITON_46" type="checkbox" value="도시림" >도시림</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>

											</tr>
											<tr>
			                                    <td colspan="7" bgcolor="cccccc" height="1"></td>
											</tr>
											<!-- 
											<tr>
			                                    <td class="search_con" colspan="7" align="center">
			                                    	<input ID="INQUERY_R" NAME="INQUERY_R" type="radio" value="1" checked>선택한 업종만 검색
													<font color=white>_____</font>
													<input ID="INQUERY_R" NAME="INQUERY_R" type="radio" value="2">선택한 업종 포함한 검색	
			                                    </td>
											</tr>
											-->
											
											<tr>											
												<td class="search_con" colspan="7">
												<input name="INQUERY_R" id="INQUERY_R" type="radio" value="1" checked>선택한 업종만 검색
												<font color="white">_____</font>
												<input name="INQUERY_R" id="INQUERY_R" type="radio" value="2">선택한 업종 포함한 검색
													
												<font color="white">_____</font>
												<input name="INQUERY_L" id="INQUERY_L" type="radio" value="1">높은 가격순
												<font color="white">_____</font>
												<input name="INQUERY_L" id="INQUERY_L" type="radio" checked value="2">낮은 가격순
												</td>
											</tr>
											<tr>
			                                    <td colspan="7" bgcolor="cccccc" height="1"></td>
											</tr>
											<tr>
			                                    <td width="140" class="search_li"  bgcolor="#EBF3F5" >가격</td>
			                                    <td  colspan=7>&nbsp;&nbsp;
			                                    	<input type="text" id="sAmount" name="sAmount" title="억 이상" class="inputbox6" style="line-height:17px" onblur="this.value=this.value.only_number();" value="" maxlength="11" />&nbsp;억이상
													~
													<input type="text" id="eAmount" name="eAmount" title="억 이하" class="inputbox6" style="line-height:17px" onblur="this.value=this.value.only_number();" value="" maxlength="11" />&nbsp;억이하
			                                    </td>
											</tr>
											<tr>
			                                    <td colspan="7" bgcolor="cccccc" height="1"></td>
											</tr>
											
											<tr>
			                                    <td width="140" class="search_li" bgcolor="#ebf3f5">메모</td>
			                                    <td colspan="7">&nbsp;&nbsp;
			                                    	<input name="sMemo" title="메모" class="inputbox6" id="sMemo" style="width: 315px; line-height: 17px;" type="text" maxlength="100" value=""></td>
											</tr>
											<tr>
			                                    <td colspan="7" bgcolor="cccccc" height="1"></td>
											</tr>
											<tr>
												<td colspan="8" >
													<!-- 버튼 시작 -->
													<table border="0" align="center" cellpadding="0" cellspacing="0">
														<tr>
															<!-- 등록 버튼 시작 -->
															<td class="btn_box" >
																<table border="0" id="BTN_SEARCH" cellpadding="0" cellspacing="0" style="cursor:pointer;">
																	<tr>
																	<!--
																		<td class="btn-left"><img src="<%=ConstantUtil.SERVER_HOST%>/images/button/kaml_btn_dat_search.gif"></td>
																		<td class="btn-right"><a href="#" onfocus="this.blur()">검색하기</a></td>
																	-->	
																		<td><p class="left_input"> 
																			</p><button type="button" class="admin_text_btn06" >검색하기</button> &nbsp;
																		</td>
																	</tr>
																</table>
															</td>
															<!-- 등록 버튼 끝 -->
														</tr>
													</table>
													<!-- 버튼 끝 -->
												</td>	
											</tr>
			                            </table>
			                        </td>
			                    </tr>
			                </table>
							<!-- 조회테이블 끝-->
						</td>
					</tr>
					<tr>
						<td class="topSpace10">
							<!-- 결과테이블 시작-->
							<table width="100%" border="0" cellspacing="0" cellpadding="9">
							  <tr>
									<td colspan="2" id = "informContent"><marquee><%=informContent%></marquee>
									</td>
								</tr>
								<tr>
									<td  align="center"> <b>* 타이틀 클릭시 정렬됩니다.</b>
									</td>
									<td  align="center"> <b>* 검색 결과를 더블 클릭 하면, 상세화면으로 이동합니다. [단위(억)]</b>
									</td>
								</tr>
							</table>
							<!-- 결과테이블 끝-->
						</td>
					</tr>
					<tr>
						<td>
							<!-- 결과테이블 시작-->
							<table width="100%" border="0" cellspacing="0" cellpadding="9">
								<tr>
									<td class="search">
										<table width="760" border="0" align="center" cellpadding="0" cellspacing="0">
											<tr>
												<td class="search_con">
													<!-- 그리드 시작 -->
													<table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
														<tr>
															<td class="taList">
																<!-- <div id="gridT" height="300"> -->
																<!-- <div id="gridT" style="width:760px; height:350px; background-color:white;"> -->
																<!--<div id="gridT" style="width:910px; height:350px; background-color:white;"> -->
																<div id="gridT" style="width:890px; height:1000px; background-color:white;"> 
																</div>
															</td>
														</tr>
														<tr><td class="taCon1Cen" id="pagingArea" colspan="3"></td></tr><!--align="center" -->
													</table>
													<!-- 그리드 끝 -->
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<!-- 결과테이블 끝-->
						</td>
					</tr>
				</table>
			<!--

				<img src="<%=ConstantUtil.SERVER_HOST%>/images/admin/prime_logo.gif" width="760px" height="95" border="0" usemap="#Map">
				<map name="Map">
				  <area shape="rect" coords="807,44,859,88" href="/common/login.jsp">
				</map>
			-->			
</td>
</tr>
</table>


<!-- Start of DHTMLX Component Initializations -->
<script type="text/javascript">

	var grid_font_size = "font-size:13px";
	var inintArrLS; //처음 로딩된
	var arrLS;	// 대분류 기 선택 값
	var arrMS;	// 중분류 기 선택 값
	var arrSS;	// 소분류 기 선택 값
	var onChromeBrowser = false ;
	var searchValueTemp ; // 업종검색값 임시 저장소

	// 결과 그리드 시작
	var gridT = new dhtmlXGridObject("gridT");
	gridT.setImagePath("<%=ConstantUtil.SERVER_HOST%>/dhx_new/imgs/");

	var idArrT = new Array();
	var idxT   = 0;
	var conArrT = new Array(); // head, width, align, sorting, type

	idArrT[idxT++] = "S";
	idArrT[idxT++] = "날짜";
	idArrT[idxT++] = "업종";
	idArrT[idxT++] = "지역";
	//idArrT[idxT++] = "법인/면허";
	//idArrT[idxT++] = "자본금";
	idArrT[idxT++] = "시평";
	idArrT[idxT++] = "좌수";
	idArrT[idxT++] = "잔액";
	//idArrT[idxT++] = "FIRST_YEAR";
	//idArrT[idxT++] = "SECOND_YEAR";
	idArrT[idxT++] = "2009";
	idArrT[idxT++] = "2010";
	idArrT[idxT++] = "2011";
	idArrT[idxT++] = "3년합계";
	idArrT[idxT++] = "5년합계";
	idArrT[idxT++] = "2012";
	//idArrT[idxT++] = "2013"; // 2008 ==> 2013
	idArrT[idxT++] = "가격";
	//idArrT[idxT++] = "협회";
	idArrT[idxT++] = "메모";
	//idArrT[idxT++] = "업체";
	//idArrT[idxT++] = "연락처";

	conArrT["S" 		]	= Array("S"	, "0",  "center",  "str", "ro");
	conArrT["날짜"   	]	= Array("<%=excelTitle.getF1()%>"	, "60",  "center",  "str", "ro");
	conArrT["업종"   	]	= Array("<%=excelTitle.getF2()%>"	, "80", "center",  "str", "ro");
	conArrT["지역"   	]	= Array("<%=excelTitle.getF3()%>"	, "60", "center",  "str", "ro");
	conArrT["시평"   	]	= Array("<%=excelTitle.getF6()%>" , "60", "center",  "str", "ro");
	conArrT["좌수"  	]	= Array("<%=excelTitle.getF7()%>"	, "60", "center",  "str", "ro");
	conArrT["잔액"   	]	= Array("<%=excelTitle.getF8()%>"	, "60", "center",  "str", "ro");
	conArrT["2009" 		]	= Array("<%=excelTitle.getF9()%>"	, "45", "center",  "str", "ro");
	conArrT["2010" 		]	= Array("<%=excelTitle.getF10()%>"	, "45", "center",  "str", "ro");
	conArrT["2011" 		]	= Array("<%=excelTitle.getF11()%>"	, "45", "center",  "str", "ro");
	conArrT["3년합계" 	]	= Array("<%=excelTitle.getF12()%>", "60", "center",  "str", "ro");
	conArrT["5년합계" 	]	= Array("<%=excelTitle.getSumOf5yr()%>", "60", "center",  "str", "ro");
	conArrT["2012" 		]	= Array("<%=excelTitle.getF13()%>"	, "45", "center",  "str", "ro");
	conArrT["가격"  	    ]	= Array("<%=excelTitle.getF19()%>" , "60", "center",  "str", "ro");
	conArrT["메모"   	]	= Array("<%=excelTitle.getF17()%>"	, "150", "left",  "str", "ro");

	l_initGrid(gridT,idArrT,conArrT);	    
	    
	gridT.setSkin("dhx_skyblue");
	gridT.enableMultiline(true);
	gridT.setDelimiter("/");
	gridT.enableAutoHeight(true);
	gridT.enablePaging(true,15,10,"pagingArea",true,"recinfoArea");

	gridT.attachEvent("onRowDblClicked",onRowDblClicked);        //row DoubleClick
	gridT.attachEvent("onRowSelect", onSelected);
	gridT.attachEvent("onHeaderClick", onHeaderClick);
	gridT.attachHeader(["",
	  "정렬<img src='./images/sortascend.png'>","","",
	  "정렬<img src='./images/sortascend.png'>","","","","","",
	  "정렬<img src='./images/sortascend.png'>",
	  "정렬<img src='./images/sortascend.png'>","",
	  "정렬<img src='./images/sortascend.png'>",""]);

	
	gridT.init();
	//gridT.setColumnHidden(gridT.getColIndexById("업체"),true);
	//gridT.setColumnHidden(gridT.getColIndexById("연락처"),true);
	//gridT.enableDistributedParsing(true,12,100);

	//
	// add by nobel
	// set bold text on specific columns
	//
	gridT.attachEvent("onRowCreated", function(rId,rObj,rXml){
	  var colNum = gridT.getColumnsNum();
	  for (var i=1 ; i <= colNum; i++) {
	    gridT.setCellTextStyle(rId,i,grid_font_size)
	  }
		gridT.setCellTextStyle(rId,4,"font-family: 돋움;font-weight: bold;"+grid_font_size);
		gridT.setCellTextStyle(rId,5,"font-family: 돋움;font-weight: bold;"+grid_font_size);
		gridT.setCellTextStyle(rId,6,"font-family: 돋움;font-weight: bold;"+grid_font_size);
		gridT.setCellTextStyle(rId,10,"font-family: 돋움;font-weight: bold;"+grid_font_size);
		gridT.setCellTextStyle(rId,12,"font-family: 돋움;font-weight: bold;"+grid_font_size);
		
		//gridT.setCellTextStyle(rId,4,"font-family: 돋움;font-weight: bold;"+grid_font_size);
		//gridT.setCellTextStyle(rId,5,"font-family: 돋움;font-weight: bold;"+grid_font_size);
		//gridT.setCellTextStyle(rId,6,"font-family: 돋움;font-weight: bold;"+grid_font_size);
		//gridT.setCellTextStyle(rId,12,"font-family: 돋움;font-weight: bold;"+grid_font_size);
		//gridT.setCellTextStyle(rId,15,"font-family: 돋움;font-weight: bold;"+grid_font_size);
		
	});
	// 결과 그리드 끝
	
	function resetCheckbox() {
		var data = JSON.parse('<%=jsonData%>');
		
		for (var i=0; i<46; i++) {
			// console.log($("#"+data.ids[i].id).val());
			// 1. input.value 설정
			$("#"+data.ids[i].id).val( (data.ids[i].value == undefined ? data.ids[i].text : data.ids[i].value));
			// 2. td.text 설정
			$("#"+data.ids[i].id)[0].nextSibling.nodeValue = data.ids[i].text;
		}
	}
	
	$(document).ready( function(){
		
		checkChromeBrowser();
		doInitSearch();
		resetCheckbox();
		
		// 조회
		$('#BTN_SEARCH').click(function() {
			doSearch();
		});
		
		// 전체 체크 시 모든 체크 박스 CHECK함
	  	$("#CONDITON").click(function(){
			if($("#CONDITON").is(":checked")){
				$("input:checkbox").attr("checked","checked");
			}else{
				$("input:checkbox").attr("checked","");
			}
		
		});
		
		$(document).keydown(function(event) {
      if (event.keyCode == '13') {
        doSearch();
      }
    });
		
	
  	
	});
  
	function onHeaderClick(colIdx, a2) {
	  
    switch(colIdx) {
      case 1:
      case 4: 
      case 10: 
      case 11: 
      case 13: 
        return true;
      default : 
        return false;
  	}
	}
  function onSelected(rowId,cellId){
		fnDetail(rowId,cellId);
	}
		
	function onRowDblClicked(rowId,cellId){
		fnDetail(rowId,cellId);
	}
  function fnDetail(rowId,cellId) {
		var excelID = gridT.cells(rowId, gridT.getColIndexById("S")).getValue();
		$("#excelID").attr("value", excelID);

		var param = "?excelID="   + excelID;		

		if (onChromeBrowser)
			param += "&isChromeBrowser=Y";
			
		var openUrl = "/print/printContent2.jsp" + param;
		l_viewPopup2(openUrl,'print', '870','850' );
  }
	function initCheckBoxOnChrome(grid,rowCnt)
	{
		for (var i=1 ; i <= rowCnt; i++) {
			grid.cells(i, grid.getColIndexById("선택")).setValue("0");
		}

	}

	function checkChromeBrowser()
	{
		if(navigator.userAgent.indexOf("Chrome") > -1 ||
		   navigator.userAgent.indexOf("Android") > -1
		) {
			onChromeBrowser = true ;
		}
	}

	function doInitSearch() {

		var params = "?COMMAND=FIRST";
	    var callUrl = "/board/combo_list_show_main_2.jsp" + params;

	    gridT.clearAndLoad(callUrl,function(){
			//alert("조회 후 그리드 생성");
			var rowCnt = gridT.getRowsNum();

        	if ( rowCnt == 0 ) {
        		alert('조회결과가 없습니다. ');
        	}
			//alert("조회 후 그리드 완성");
            //$("#totalCount").text(rowCnt);
            //$.unblockUI(); //처리후 unblock
        });
	}
	
	// 상단 조회버튼 클릭 시 처리 함수
	function doSearch() {

		var f2List ;
		var chkval = [];
		var searchCond ;
		// 2014.02.15 추가됨
		var sAmount;
		var eAmount;
		var sMemo;	// 메모(기타) 검색

        $.each($(":checked"), function(i, val){
            
            //전체와 RADIO 버튼 값 제외 함
			if ( $(this).val() == "전체" || $(this).val() == "1" || $(this).val() == "2" ) 
			{
				//continue;
			} else 
			{
				chkval.push($(this).val());
			}
			
		});
		
		searchCond = $("input:radio[name=INQUERY_R]:checked").val();	// 선택업종만/선택업종 포함 라디오 버튼
		searchCond2 = $("input:radio[name=INQUERY_L]:checked").val();	// 높은/낮은 가격순 라디오 버튼

		sAmount    = $("#sAmount").val()
		eAmount    = $("#eAmount").val()
		sMemo      = $("#sMemo").val();

    var params = "COMMAND=SEARCH";
        params += "&f2List="+chkval;
        params += "&searchCond="+searchCond;
        params += "&searchCond2="+searchCond2;
        params += "&sAmount="+sAmount + "&eAmount="+eAmount;
        params += "&sMemo="+sMemo;
        
		if (onChromeBrowser)
			params += "&isChromeBrowser=Y";
		
	    var callUrl = "/board/combo_list_show_main_2.jsp?" + params;
		
        gridT.clearAndLoad(callUrl,function(){
			//alert("조회 후 그리드 생성");
			var rowCnt = gridT.getRowsNum();

        	if ( rowCnt == 0 ) {
        		alert('조회결과가 없습니다. ');
        	}
			//alert("조회 후 그리드 완성");
            //$("#totalCount").text(rowCnt);
            //$.unblockUI(); //처리후 unblock
        });
	}


	function nullChk(param) {
		//
		if ( param == "" || param == null || param == undefined || param == "undefined" ) {
			return false;
		}
		else {
			return true;
		}
	}

	//replace
	String.prototype.simpleReplace = function(oldStr, newStr) {
		var rStr = oldStr;

		rStr = rStr.replace(/\\/g, "\\\\");
		rStr = rStr.replace(/\^/g, "\\^");
		rStr = rStr.replace(/\$/g, "\\$");
		rStr = rStr.replace(/\*/g, "\\*");
		rStr = rStr.replace(/\+/g, "\\+");
		rStr = rStr.replace(/\?/g, "\\?");
		rStr = rStr.replace(/\./g, "\\.");
		rStr = rStr.replace(/\(/g, "\\(");
		rStr = rStr.replace(/\)/g, "\\)");
		rStr = rStr.replace(/\|/g, "\\|");
		rStr = rStr.replace(/\,/g, "\\,");
		rStr = rStr.replace(/\{/g, "\\{");
		rStr = rStr.replace(/\}/g, "\\}");
		rStr = rStr.replace(/\[/g, "\\[");
		rStr = rStr.replace(/\]/g, "\\]");
		rStr = rStr.replace(/\-/g, "\\-");

	  	var re = new RegExp(rStr, "g");
	    return this.replace(re, newStr);
	}

	//Grid setting
	function l_initGrid(grid, idArr, conArr){
	    var headerArr  =  Array(); //header
	    var widthArr  =  Array(); //width
	    var alignArr   =  Array(); //align
	    var sortingArr =  Array(); //sorting
	    var typeArr   =  Array(); //type
	    for(var i = 0 ; i < idArr.length; i++){
	        headerArr[i]  = conArr[idArr[i]][0];
	        widthArr[i]   = conArr[idArr[i]][1];
	        alignArr[i]   = conArr[idArr[i]][2];
	        sortingArr[i] = conArr[idArr[i]][3];
	        typeArr[i]    = conArr[idArr[i]][4];
	    }
	    grid.setColumnIds(idArr.join(","));
	    grid.setHeader(headerArr.join(","));
	    grid.setInitWidths(widthArr.join(","));
	    grid.setColAlign(alignArr.join(","));
	    grid.setColSorting(sortingArr.join(","));
	    grid.setColTypes(typeArr.join(","));

	}
	
	// 숫자 확인
  	String.prototype.only_number = function(){
   		return this.replace(/[^0-9,.]/gi, '');
  	}
  	
  	function onlyNum(input) {
	    var chars = ".0123456789"; //입력가능한 문자 지정
	    return containsCharsOnly(input, chars);
	}
	
	function containsCharsOnly(input, chars) {
	    alert(input.value.length);
	    for (var inx = 0; inx <= input.value.length; inx++) {
	
	        if (inx == 0) {//최초입력한 문자
	            e = window.event;
	            if (window.event) {
	                key = e.keyCode;
	            } else if (e) {
	                key = e.which;
	            } else {
	                return true;
	            }
	            keychar = String.fromCharCode(key);
	            if (chars.indexOf(keychar) == -1)// window.event 에서 받은 keychar 로 유효성 검사.
	                return false;
	        }
	        else {//최초입력 문자가 아니면, input 의 text를 읽어서 처리한다.
	            if (chars.indexOf(input.value.charAt(inx)) == -1) {
	                return false;
	            }
	        }
	    }
	    return true;
	}

	function chkFilterKey(filter)
	{
		if( filter )
		{
		 var sKey = String.fromCharCode(event.keyCode);
		 alert(event.keyCode);
		 alert(sKey);
		 var re = new RegExp(filter);
				       
		 if(!re.test(sKey))
		 {
		   event.returnValue=false;
		
		   if(filter.match("0-9"))
		   {
		     if(event.keyCode > 95 && event.keyCode < 106)
		     {
		       event.returnValue=true;
		     }
		   }
		  }
		 }
		
		 if(event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 13 || event.keyCode == 20 || event.keyCode == 17 || event.keyCode == 18){
		   event.returnValue=true;
		}
	}
	
</script>
<!-- End of DHTMLX Component Initializations -->
