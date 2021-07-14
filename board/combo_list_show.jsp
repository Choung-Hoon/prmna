<%@ page language="java" contentType="text/xml; charset=utf-8" pageEncoding="utf-8"%>

<%@ page import="lee.common.config.*" %>
<%@ page import="lee.bean.*" %>
<%@ page import="lee.manager.*" %>
<%@ page import="lee.common.util.*" %>
<%@ page import="lee.common.servlet.*" %>
<%@ page import="lee.session.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.net.*" %>
<%@ page import="lee.common.db.JResultSet" %>

<%@ include file="/common/_includeInit.inc" %>
<%@ include file="/common/_includeCommon.inc" %>

<%
	System.out.println("combo_list_show");
	/* 업종/3년 합계/양도가 필드명*/
	String fieldName = request.getParameter( "fieldName" );
	String f2List  = request.getParameter( "f2List" );
	String f13List = request.getParameter( "f13List" );
	String f19List = request.getParameter( "f19List" );
	String orderByF13 = StringUtil.NVL(request.getParameter( "orderByF13" ),"");
	String orderByF19 = StringUtil.NVL(request.getParameter( "orderByF19" ),"");

	/* 업종 텍스트 검색 시 사용함 */
	String searchValue = request.getParameter( "searchValue" );


	// 버그 추적 용
	String userID = StringUtil.NVL(request.getParameter( "userID" ),"None");

	String COMMAND = request.getParameter( "COMMAND" );
	String excelID = StringUtil.NVL(request.getParameter( "excelID" ),"");

	/*
		크롬브라우저에서 한글이 깨져서 UTF8로 컨버젼 해야 함
	*/
	String isChromeBrowser = StringUtil.NVL(request.getParameter( "isChromeBrowser" ),"N") ;
	String returnGubn = StringUtil.NVL(request.getParameter( "returnGubn" ),"G"); // J.json, G.그리드

	HashMap param = new HashMap();
	System.out.println("Cobmo_list_show start");

	if (!StringUtil.NVL(fieldName).equals(""))
		param.put("fieldName", fieldName );

	if (!StringUtil.NVL(f2List).equals(""))
	{
		// 마지막 , 제거
		if ("Y".equals(isChromeBrowser))
			f2List = StringUtil.replace( StringUtil.toUTF8(f2List), "''", "'");
		else
			f2List = StringUtil.replace( StringUtil.toKor(f2List), "''", "'");

		f2List = StringUtil.replace( f2List, "/", "<br>");
		param.put("f2List", f2List);
	}

	String CONSULT_VAL = request.getParameter( "CONSULT_VAL" );
	String MEMO_VAL = request.getParameter( "MEMO_VAL" );
	
	if (!StringUtil.NVL(CONSULT_VAL).equals(""))
	{
		// 마지막 , 제거
		if ("Y".equals(isChromeBrowser))
			CONSULT_VAL = StringUtil.toUTF8(CONSULT_VAL);
		else
			CONSULT_VAL = StringUtil.toKor(CONSULT_VAL);
		
		param.put("CONSULT_VAL", CONSULT_VAL);
	}
	
	if (!StringUtil.NVL(MEMO_VAL).equals(""))
	{
		// 마지막 , 제거
		if ("Y".equals(isChromeBrowser))
			MEMO_VAL = StringUtil.toUTF8(MEMO_VAL);
		else
			MEMO_VAL = StringUtil.toKor(MEMO_VAL);
		
		param.put("MEMO_VAL", MEMO_VAL);
	}
	
	
	if (!StringUtil.NVL(f13List).equals(""))
	{
		// 마지막 , 제거
		if ("Y".equals(isChromeBrowser))
			f13List = StringUtil.replace( StringUtil.toUTF8(f13List), "''", "'");
		else
			f13List = StringUtil.replace( StringUtil.toKor(f13List), "''", "'");

		f13List = StringUtil.replace( f13List, "/", "<br>");
		param.put("f13List", f13List);
	}

	if (!StringUtil.NVL(f19List).equals(""))
	{
		// 마지막 , 제거
		if ("Y".equals(isChromeBrowser))
			f19List = StringUtil.replace( StringUtil.toUTF8(f19List), "''", "'");
		else
			f19List = StringUtil.replace( StringUtil.toKor(f19List), "''", "'");

		f19List = StringUtil.replace( f19List, "/", "<br>");
		param.put("f19List", f19List);
	}

	/* 업종 텍스트 검색 시  */
	if (!StringUtil.NVL(searchValue).equals(""))
	{
		// 마지막 , 제거
		if ("Y".equals(isChromeBrowser))
			searchValue = StringUtil.replace( StringUtil.toUTF8(searchValue), "''", "'");
		else
			searchValue = StringUtil.replace( StringUtil.toKor(searchValue), "''", "'");

		searchValue = StringUtil.replace( searchValue, "/", "<br>");
		param.put("searchValue", searchValue);
	}


	JResultSet jrs = null;
	QueryMgr queryMgr = new QueryMgr();
	String xml = "";
	try {
		System.out.println("param : "  + param);
	} catch (Exception e) {
	}
	
	try {
    	// 조회
		if ( "COMBO".equals(COMMAND) ) {
		  
			jrs = (JResultSet)queryMgr.getSQL(creatFieldSqlStatement(param));
			

			// 지점명|지점코드

      if( jrs != null ) {

          if ("G".equals(returnGubn))
          {
//            System.out.println("convertToXml : " + jrs.getRowCount());
     
            xml = DhxGrid.convertToXml(
                  jrs
                , null
                , new String[] {"CHK"								// 체크박스
  							  ,fieldName							// 지점명
  							  ,fieldName							// 지점코드
  				        } // hidden Column 명 (null 시 hidden column 없음)
                , null                                                  // hidden Column 명 (null 시 hidden column 없음)
                , Integer.MIN_VALUE                                     // row count (Integer.MIN_VALUE 최대)
                , null                                                  // link Column 명  (outbound 칼럼명)
                , false);     
                
            // row no 출력 유무 (true, false)
				  } else if ("J".equals(returnGubn)){
				    
            xml = DhxGrid.convertToJson(
                  jrs
                , new String[] {"CHK"								// 체크박스
							   ,fieldName							// 지점명
							   ,fieldName							// 지점코드
				        } // hidden Column 명 (null 시 hidden column 없음)
                , null                                                  // hidden Column 명 (null 시 hidden column 없음)
                , null                                                  // link Column 명  (outbound 칼럼명)
                );
          }

          System.out.println("Cobmo_list_show : 1");
          out.print(xml);
      } //End of if

		// 그리드 조회 시
		} else if ( "SEARCH".equals(COMMAND) ) {

			returnGubn = "G";

			/* 정렬 체크 값 넘김 */
			param.put("COMMAND", COMMAND);
			param.put("orderByF13", orderByF13);
			param.put("orderByF19", orderByF19);
	
			param.put("CONSULT_VAL", CONSULT_VAL);
			param.put("MEMO_VAL", MEMO_VAL);
			
			jrs = (JResultSet)queryMgr.getSQL(creatSearchSqlStatement(param));

			xml = DhxGrid.convertToXml(
	                  jrs
	                , null
	                , new String[] {
					      "excel_id"    //일련 번호
					     ,"f1"			//날짜
					     ,"f2"		    //업종
					     ,"f3"		    //지역
					     ,"f6"          //시평
					     ,"f8"          //잔액
					     ,"fst_Yr"		//2008
					     ,"scnd_Yr"		//2009
					     ,"f10"         //2009
					     ,"f11"         //2010
					     ,"f12"         //2011
					     ,"f13"         //3년합계
					     ,"sum_of_5yr"  //5년합계
					     ,"f14"         //2012
					     ,"f9"          //2008 ==> 2013
					     ,"f19"         //가격
					     ,"f17"         //기타
					     ,"f18"         //업체
					     ,"f15"         //양도가
					  } // hidden Column 명 (null 시 hidden column 없음)
	                , null                  // hidden Column 명 (null 시 hidden column 없음)
	                , Integer.MIN_VALUE     // row count (Integer.MIN_VALUE 최대)
	                , null                  // link Column 명  (outbound 칼럼명)
	                , false);               // row no 출력 유무 (true, false)
			System.out.println("Cobmo_list_show : 3");
	        out.print(xml);

		// 텍스트 값 조회 시
		} else if ( "TEXT_SEARCH".equals(COMMAND) ) {
			System.out.println("Cobmo_list_show : 4");
			returnGubn = "G";

			/* 정렬 체크 값 넘김 */
			param.put("COMMAND", COMMAND);

			jrs = (JResultSet)queryMgr.getSQL(creatSearchSqlStatement(param));

			xml = DhxGrid.convertToXml(
	                  jrs
	                , null
	                , new String[] {
					      "excel_id"    //일련 번호
					     ,"f1"			//날짜
					     ,"f2"		    //업종
					     ,"f3"		    //지역
					     ,"f6"          //시평
					     ,"f8"          //잔액
					     ,"fst_Yr"		//2008
					     ,"scnd_Yr"		//2009
					     ,"f10"         //2009
					     ,"f11"         //2010
					     ,"f12"         //2011
					     ,"f13"         //3년합계
					     ,"sum_of_5yr"  //5년합계
					     ,"f14"         //2012
					     ,"f9"          //2008 ==> 2013
					     ,"f19"         //가격
					     ,"f17"         //기타
					     ,"f18"         //업체
					     ,"f15"         //양도가
					  } // hidden Column 명 (null 시 hidden column 없음)
	                , null                  // hidden Column 명 (null 시 hidden column 없음)
	                , Integer.MIN_VALUE     // row count (Integer.MIN_VALUE 최대)
	                , null                  // link Column 명  (outbound 칼럼명)
	                , false);               // row no 출력 유무 (true, false)
	        out.print(xml);
		// 상세 조회 시
		} else if ( "DETAIL".equals(COMMAND) ) {
			System.out.println("Cobmo_list_show : 2");
			returnGubn = "G";

			param.put("COMMAND", COMMAND);
			param.put("excelID", excelID);

			jrs = (JResultSet)queryMgr.getSQL(creatSearchSqlStatement(param));

			xml = DhxGrid.convertToXml(
	                  jrs
	                , null
	                , new String[] {
					      "f1"			//날짜
					     ,"f2"		    //업종
					     ,"f3"		    //지역
					     ,"f4"		    //법인/면허
					     ,"f5"		    //자본금
					     ,"f6"          //시평
					     ,"f7"          //좌수
					     ,"f8"          //잔액
					     ,"fst_Yr"		//2008
					     ,"scnd_Yr"		//2009
					     ,"f10"         //2009
					     ,"f11"         //2010
					     ,"f12"         //2011
					     ,"f13"         //3년합계
					     ,"sum_of_5yr"  //5년합계
					     ,"f14"         //2012
					     ,"f9"          //2008 ==> 2013으로 변경함
					     ,"f19"         //가격
					     ,"f16"         //협회
					     ,"f17"         //기타
					     ,"f18"         //업체
					     ,"f15"         //양도가
					  } // hidden Column 명 (null 시 hidden column 없음)
	                , null                                                  // hidden Column 명 (null 시 hidden column 없음)
	                , Integer.MIN_VALUE                                     // row count (Integer.MIN_VALUE 최대)
	                , null                                                  // link Column 명  (outbound 칼럼명)
	                , false);                                                // row no 출력 유무 (true, false)
	        out.print(xml);

		}

	}
	catch(Exception _e) {
		System.out.println("조회실패 : " + _e.getMessage());
        System.out.println("##########################################");
        System.out.println(DhxGrid.getExceptionResult(returnGubn, _e));
        System.out.println("##########################################");
        out.println(DhxGrid.getExceptionResult(returnGubn, _e));

    }
%>

<%!
public String creatFieldSqlStatement(HashMap param)
{
	String fieldName = StringUtil.NVL((String)param.get("fieldName"));
	String f2List  = StringUtil.NVL((String)param.get( "f2List" ));
	String f13List = StringUtil.NVL((String)param.get( "f13List" ));
	String f19List = StringUtil.NVL((String)param.get( "f19List" ));
	String searchValue = StringUtil.NVL((String)param.get("searchValue"));

	if ("".equals(fieldName))
		return "" ;

	StringBuffer sqlQueryBf = new StringBuffer()
	 .append("SELECT DISTINCT REPLACE(" + fieldName + ",'<br>','/') AS " + fieldName ).append("\n")
	 .append(" , '' AS CHK ").append("\n")
	 .append("FROM tb_excel_data ").append("\n")
	 .append("WHERE 1 = 1 ").append("\n");

	/* 업종 */
	if (!"".equals(f2List))
		 sqlQueryBf.append(" AND f2 IN (" + f2List + ") ").append("\n");

	/* 3년 합계 -> 5년 합계*/
	if (!"".equals(f13List))
		 sqlQueryBf.append(" AND sum_of_5yr  IN (" + f13List + ") ").append("\n");

	/* 가격 */
	if (!"".equals(f19List))
		 sqlQueryBf.append(" AND f19 IN (" + f19List + ") ").append("\n");


	/* 업종 텍스트 검색 조건 추가 함 */
	if (!"".equals(searchValue))
		 /* 업종 조건 */
		sqlQueryBf.append(" AND f2 LIKE '%" + searchValue.trim() + "%' ").append("\n");


	if ("f2".equals(fieldName))
	{
		sqlQueryBf.append("ORDER BY " + fieldName );

	} else {
		sqlQueryBf.append(" ORDER BY (	").append("\n")
				  .append("		CASE WHEN " + fieldName + " REGEXP '[0-9]' THEN CAST(" + fieldName + " AS DECIMAL(10,3)) ").append("\n")
				  .append("		ELSE 100000 ").append("\n")
				  .append("		END)	").append("\n");
	}

	return sqlQueryBf.toString();

}

public String creatSearchSqlStatement(HashMap param)
{
	String COMMAND  = StringUtil.NVL((String)param.get( "COMMAND" ));
	String f2List  = StringUtil.NVL((String)param.get( "f2List" ));
	String f13List = StringUtil.NVL((String)param.get( "f13List" ));
	String f19List = StringUtil.NVL((String)param.get( "f19List" ));
	String orderByF13 = StringUtil.NVL((String)param.get( "orderByF13" ));
	String orderByF19 = StringUtil.NVL((String)param.get( "orderByF19" ));
	String excelID = StringUtil.NVL((String)param.get( "excelID" ));
	
	String CONSULT_VAL = StringUtil.NVL((String)param.get( "CONSULT_VAL" ));
	String MEMO_VAL = StringUtil.NVL((String)param.get( "MEMO_VAL" ));



	StringBuffer sqlQueryBf = new StringBuffer()
	 	.append("SELECT  					").append("\n")
	 	.append("	 excel_id AS excelId	").append("\n")  
		.append("	,load_id  AS loadId     ").append("\n")  //  로드순번               
		.append("  	,f1 	                ").append("\n")  //  날짜                 
		.append("	,f2 	                ").append("\n")  //  업종                 
		.append("	,f3                     ").append("\n")  //  지역                 
		.append("	,f4                     ").append("\n")  //  법인/면허              
		.append("	,f5                     ").append("\n")  //  자본금                
		.append("	,f6                     ").append("\n")  //  시평                 
		.append("	,f7                     ").append("\n")  //  좌수                 
		.append("	,f8                     ").append("\n")  //  잔액   
		.append("	,fst_yr  AS fst_Yr      ").append("\n")  //  2008
		.append("	,scnd_yr AS scnd_Yr     ").append("\n")  //  2009                      
		.append("	,f9                     ").append("\n")  //  2008 ==> 2013 변경   
		.append("	,f10                    ").append("\n")  //  2009               
		.append("	,f11                    ").append("\n")  //  2010               
		.append("	,f12                    ").append("\n")  //  2011               
		.append("	,f13                    ").append("\n")  //  3년합계 
		.append("	,sum_of_5yr AS sumOf5yr ").append("\n")  //  5년합계                 
		.append("	,f14                    ").append("\n")  //  2012               
		// 20210404 수정
		.append("	,f19                    ").append("\n")  //  가격
		.append("	,f16                    ").append("\n")  //  협회                 
		.append("	,f17                    ").append("\n")  //  기타                 
		.append("	,f18                    ").append("\n")  //  업체    
		.append("	,f15                    ").append("\n")  //  양도가   
	    .append("    ,DATE_FORMAT(reg_date, '%Y/%m/%d') as regDate ").append("\n")
	 .append("FROM tb_excel_data ").append("\n")
	 .append("WHERE 1 = 1 ").append("\n");

	if ("DETAIL".equals(COMMAND))
	{
		sqlQueryBf.append(" AND excel_id = " + excelID).append("\n");

	}
	else if ("TEXT_SEARCH".equals(COMMAND))
	{
		/* 업종 조건 */
		sqlQueryBf.append(" AND f2 LIKE '%" + f2List.trim() + "%' ").append("\n");

		/********** 정렬 조건 조립***************/
		/* 가격 정렬 필드 추가 함 */
		sqlQueryBf.append(" ORDER BY (	").append("\n")
				  .append("		CASE WHEN f19 REGEXP '[0-9]' THEN CAST(f19 AS DECIMAL(10,3)) ").append("\n")
				  .append("		ELSE 100000 ").append("\n")
				  .append("		END) ").append("\n");

	} else {

		/********** 조건에 대한 조립***************/
		/* 업종 조건 */
		if (!"".equals(f2List))
			 sqlQueryBf.append(" AND f2 IN (" + f2List + ") ").append("\n");

		/* 3년 합계 조건 -> 5년*/
		if (!"".equals(f13List))
			 sqlQueryBf.append(" AND sum_of_5yr IN (" + f13List + ") ").append("\n");
		 
		/* 가격 조건 : 20210404*/
		if (!"".equals(f19List))
			 sqlQueryBf.append(" AND f19 IN (" + f19List + ") ").append("\n");
	  
	  /* 컨설팅 조건 */
		if (!"".equals(CONSULT_VAL))
			 sqlQueryBf.append(" AND f18 LIKE '%" + CONSULT_VAL.trim() + "%' ").append("\n");
	  
	  /* 메모 조건 */
		if (!"".equals(MEMO_VAL)) {
			 sqlQueryBf.append(" AND f17 LIKE '%" + MEMO_VAL.trim() + "%' ").append("\n");
	  }
	  

		/********** 정렬 조건 조립***************/
		/* 양도가 정렬 필드 추가 함 */
		sqlQueryBf.append(" ORDER BY (	").append("\n")
				  .append("		CASE WHEN f19 REGEXP '[0-9]' THEN CAST(f19 AS DECIMAL(10,3)) ").append("\n")
				  .append("		ELSE 100000 ").append("\n")
				  .append("		END) ").append("\n");
	}

	System.out.println("SQL : " + sqlQueryBf.toString());
	
	return sqlQueryBf.toString();

}

public String convertToJson(ExcelData[] excelDataList, String fieldName)
{


	StringBuffer defaultValue = new StringBuffer();

	defaultValue.append("{\n")
				.append("  data:\n")
				.append("  [	\n");

	String code = null;
	String name = null;
	for(int count = 0 ; excelDataList.length > count ; count++)
	{
		boolean needsAppendComma = false;

		// 업종
		if ("f2".equals(fieldName))
		{
			code = excelDataList[count].getF2();
			name = excelDataList[count].getF2();
		} // 3년 합계
		else if ("sum_of_5yr".equals(fieldName))
		{
			code = excelDataList[count].getF13();
			name = excelDataList[count].getF13();
		} 
		// 가격으로 변경(2021.04.04)
		else if ("f19".equals(fieldName))
		{
			code = excelDataList[count].getF19();
			name = excelDataList[count].getF19();
		}

		needsAppendComma = count < (excelDataList.length - 1);

		defaultValue.append("{");

		defaultValue.append("	\"").append("CODE").append("\"")
					.append(":")
					.append("\"").append(code).append("\"")
					.append(",")
					.append("	\"").append("NAME").append("\"")
					.append(":")
					.append("\"").append(name).append("\"");

		defaultValue.append(" 	}\n");

		if (needsAppendComma) {
			defaultValue.append(",");
		}

	}

	defaultValue.append("  ]\n")
				.append("}\n");

	return defaultValue.toString();
}

%>