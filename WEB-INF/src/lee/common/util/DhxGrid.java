/*******************************************************************************
* 시  스  템  명 : 
* 업    무    명 : KAIS grid 유틸    
* 프로그램명(ID) : KaisGrid.java
* 설          명 : KAIS 관련 grid 지원 util  
* 작    성    일 : 2008.12.21
* 작    성    자 : 이 강 기 
*
* 수  정  내  역 :
* 수 정 자      수 정 일 자          수    정    내    역
* ---------   ---------------  -------------------------------------------------
*      			A. 거래자명/고객명 : 앞에서 2~3까지, 7~8까지 글자를 "XX" 처리(예:대한XX주식XX)
*	   			B. 실명번호 : - 주민번호, 외국인등록번호 등 : 8~12까지 "XXXXX" 처리(예:123456-1XXXXXX)
*	       			- 사업자번호 : 6~9까지 "XXXX" 처리(123-81-XXXXX)
*	   			C. 계좌번호 : 3번째 1자리를 “X”, 6~7번째 "XX" 처리(예: 10X-20-XX8910)
*******************************************************************************/
package lee.common.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import lee.common.db.JResultSet;
import lee.common.util.dhxgrid.GridData;
import lee.common.util.dhxgrid.GridModel;
import lee.common.util.dhxgrid.Row;
import lee.common.util.jsp.HtmlUtility;

public class DhxGrid {

	public static final String CHECKBOX_0 = "@CHECKBOX_0@";
	public static final String CHECKBOX_1 = "@CHECKBOX_1@";
	public static final String ROW_ID= "@ROW_ID@";	
	
	public static String createJSon(JResultSet jrs) throws Throwable {
		return createJSon(jrs, null, null, Integer.MIN_VALUE);
	}
	
	public static String createJSon(JResultSet jrs, int rowCount) throws Throwable {
		return createJSon(jrs, null, null, rowCount);
	}
	
	public static String createJSon(
							JResultSet jrs, 
							String rowIdColumn) throws Throwable {
		
		return createJSon(jrs, rowIdColumn, null, Integer.MIN_VALUE);
	}
	
	public static String createJSon(
							JResultSet jrs, 
							String rowIdColumn,
							int rowCount) throws Throwable {
				
		return createJSon(jrs, rowIdColumn, null, rowCount);
	}

	public static String createJSon(
							JResultSet jrs, 
							String[] exportColumnList) throws Throwable {
		
		return createJSon(jrs, null, exportColumnList, Integer.MIN_VALUE);
	}
	
	public static String createJSon(
							JResultSet jrs, 
							String[] exportColumnList,
							int rowCount) throws Throwable {

		return createJSon(jrs, null, exportColumnList, rowCount);
	}

	public static String createJSon(
							JResultSet jrs, 
							String rowIdColumn,
							String[] exportColumnList) throws Throwable {

		return createJSon(jrs, rowIdColumn, exportColumnList, Integer.MIN_VALUE);
	}
	

	/**
	 * <p> JResultSet -> JSON 변환 함수 (dhxGrid) - by 검사정보</p>
	 *
	 * @param jrs 				JResultSet	Resultset 객체
	 * @param rowIdColumn		String		row id 로 사용할 Column 명 (null 시 1,2,3,4,5 ~ 100, 200 으로 부여)
	 * @param exportColumnList	String[]	TITLE Print Column 명
	 * @param rowCount			int			row count (Integer.MIN_VALUE 최대)  
	 * 
	 * @throws Throwable
	 * @return String
	 * @description :
	 * JResultSet 데이터를 dhxGrid 형식의 XML 형식의 String으로 변환한다.   
	 * 
	 */		
	public static String createJSon(
								JResultSet jrs, 
								String rowIdColumn,
								String[] exportColumnList,
								int rowCount) throws Throwable {
		
		StringBuffer sbuf = new StringBuffer();

		if (!(jrs instanceof JResultSet)) {
			throw new IllegalArgumentException(
						"createJSon supports only JResultSet type.");
		}

		if (rowCount == Integer.MIN_VALUE) {
			rowCount = jrs.getRowCount();
		}
		
		String rowId;
		
		sbuf.append("{\n");
		sbuf.append("  rows:[\n");
		
		for (int i = 0; jrs.next(); i++) {

			if (rowIdColumn == null) {
				rowId = "@ROW" + i;
			} else {				
				rowId = jrs.getString(rowIdColumn).toString();
			}

			sbuf.append("    {\n");
			sbuf.append("      id:\"").append(rowId).append("\",\n");
			sbuf.append("      data:[\n");
			
			int elementCount = 0;
			
			if (exportColumnList == null) {
				elementCount = jrs.getColumnCount(); 
			} else {
				elementCount = exportColumnList.length;
			}
			
			for (int k = 0; k < elementCount; k++) {
				String field = null;
				
				boolean isCheckboxField = false;
				boolean isChecked = false;
				boolean needsAppendComma = false;
				
				if (exportColumnList == null) {
					field = jrs.getString(k);
					needsAppendComma = k < (jrs.getColumnCount() - 1);
				} else {
					if (CHECKBOX_0.equals(exportColumnList[k])) {
						isChecked = false;
						isCheckboxField = true;
					} else if (CHECKBOX_1.equals(exportColumnList[k])) {
						isChecked = true;
						isCheckboxField = true;
					} else {
						field = jrs.getString(exportColumnList[k]);
					}
					needsAppendComma = k < (jrs.getColumnCount() - 1);
				}
				
				if (isCheckboxField) {
					sbuf.append("        \"").append((isChecked) ? "1" : "0").append("\"");
				} else {
					sbuf.append("        \"").append(field).append("\"");
				}
				
				if (needsAppendComma) {
					sbuf.append(",");
				}
				
				sbuf.append("\n");
			}
			
			sbuf.append("      ]\n");
			
			if (i == rowCount - 1) {
				sbuf.append("    }\n");
			} else {
				sbuf.append("    },\n");
			}
		}
		
		sbuf.append("  ]\n");
		sbuf.append("}\n");
		
		return sbuf.toString();
	}	
	
	public static GridModel createGridModel(JResultSet jrs) throws Throwable {
		return createGridModel(jrs, null, null, null, Integer.MIN_VALUE);
	}
	
	public static GridModel createGridModel(JResultSet jrs, int rowCount) throws Throwable {
		return createGridModel(jrs, null, null, null, rowCount);
	}
	
	public static GridModel createGridModel(
							JResultSet jrs, 
							String rowIdColumn) throws Throwable {
		
		return createGridModel(jrs, rowIdColumn, null, null, Integer.MIN_VALUE);
	}
	
	public static GridModel createGridModel(
							JResultSet jrs, 
							String rowIdColumn,
							int rowCount) throws Throwable {
				
		return createGridModel(jrs, rowIdColumn, null, null, rowCount);
	}

	public static GridModel createGridModel(
							JResultSet jrs, 
							String[] exportColumnList) throws Throwable {
		
		return createGridModel(jrs, null, exportColumnList, null, Integer.MIN_VALUE);
	}
	
	public static GridModel createGridModel(
							JResultSet jrs, 
							String[] exportColumnList,
							int rowCount) throws Throwable {

		return createGridModel(jrs, null, exportColumnList, null, rowCount);
	}

	public static GridModel createGridModel(
							JResultSet jrs, 
							String rowIdColumn,
							String[] exportColumnList) throws Throwable {

		return createGridModel(jrs, rowIdColumn, exportColumnList, null, Integer.MIN_VALUE);
	}
	
	public static GridModel createGridModel(
							JResultSet jrs, 
							String rowIdColumn,
							String[] exportColumnList,
							int rowCount) throws Throwable {
				
		return createGridModel(jrs, rowIdColumn, exportColumnList, null, rowCount);
	}

	public static GridModel createGridModel(
							JResultSet jrs, 
							String rowIdColumn,
							String[] exportColumnList,
							String[] exportColumnListByUserData) throws Throwable {
		
		return createGridModel(
							jrs, 
							rowIdColumn, 
							exportColumnList, 
							exportColumnListByUserData, 
							Integer.MIN_VALUE);
	}

	/**
	 * <p> JResultSet -> XML 변환 함수(링크포함) (dhxGrid)</p>
	 *
	 * @param  jrs 						JResultSet	ResultSet DATA
	 * @param  rowIdColumn				row id 로 사용할 Column 명 (null 시 1,2,3,4,5 ~ 100, 200 으로 부여)
	 * @param  exportColumnList			TITLE Print Column 명 	    
	 * @param  exportColumnListByUserData	hidden Column 명 (null 시 hidden column 없음),출력은 안되고 GRID에 값이 존재함 
	 * @param  rowCount					row count (Integer.MIN_VALUE 최대)
	 * @throws Throwable
	 * @return String
	 * @description :
	 * ResultSet 데이터를 dhxGrid의 GridModel으로 변환한다.   
	 * 
	 */	
	public static GridModel createGridModel(
							JResultSet jrs, 
							String rowIdColumn,
							String[] exportColumnList,
							String[] exportColumnListByUserData,
							int rowCount) throws Throwable {
		
		if (!(jrs instanceof JResultSet)) {
			throw new IllegalArgumentException(
						"createGridModel supports only JResultSet type.");
		}
	
		if (rowCount == Integer.MIN_VALUE) {
			rowCount = jrs.getRowCount();
		}
		
		GridModel gridModel = new GridModel();
		GridData rows = gridModel.getData();
		
		String rowId;
		
		Row row;
		
		// row LOOP
		for (int i = 0; jrs.next() ; i++) {
						
			// row id add    ex) @ROW0, @ROW1, @ROW9999
			if (rowIdColumn == null) {
				rowId = "@ROW" + i;
			} else {
				rowId = jrs.getString(rowIdColumn).trim();
			}
			
			row = rows.addRow(rowId);
			
			// User Data add
			if (exportColumnListByUserData != null) {
				for (int k = 0; k < exportColumnListByUserData.length; k++) {
					row.addUserData(
							exportColumnListByUserData[k],
							jrs.getString(exportColumnListByUserData[k]).trim());
				}
			}
			
			int elementCount = 0;
			
			// Column count
			if (exportColumnList == null) {
				elementCount = jrs.getColumnCount(); 
			} else {
				elementCount = exportColumnList.length;
			}
			
			// column LOOP 
			for (int k = 0; k < elementCount; k++) {
				String field = null;
				
				boolean isCheckboxField = false;
				boolean isChecked = false;
				
				if (exportColumnList == null) {
					field = jrs.getString(k);
				} else {
					if (CHECKBOX_0.equals(exportColumnList[k])) {
						isChecked = false;
						isCheckboxField = true;
					} else if (CHECKBOX_1.equals(exportColumnList[k])) {
						isChecked = true;
						isCheckboxField = true;
					} else {
						field = jrs.getString(exportColumnList[k]);
					}
				}
				
				if (isCheckboxField) {
					row.addCell((isChecked) ? "1" : "0");
				} else {
					if (!(field instanceof String)) {
						throw new IllegalArgumentException(
								"createGridModel dose not support "
								+ "Multi-Dimension array.");
					}
					
					row.addCell(field.trim());
				}
			}
		}
		
		return gridModel;
	}	
	
	
	public static String convertToXml( 
								JResultSet jrs, 
								String rowIdColumn,
								String[] exportColumnList,
								String[] exportColumnListByUserData,
								String[] exportColumnLinkList,
								boolean indexPrint) throws Throwable{
		
		return convertToXml(jrs, rowIdColumn, exportColumnList, exportColumnListByUserData, Integer.MIN_VALUE, exportColumnLinkList, indexPrint, null);
	}
	
	/**
	 * <p> JResultSet -> XML 변환 함수(링크포함) (dhxGrid)</p>
	 *
	 * @param  jrs 						JResultSet	ResultSet DATA
	 * @param  rowIdColumn				row id 로 사용할 Column 명 (null 시 1,2,3,4,5 ~ 100, 200 으로 부여)
	 * @param  exportColumnList			TITLE Print Column 명 	    
	 * @param  exportColumnListByUserData	hidden Column 명 (null 시 hidden column 없음),출력은 안되고 GRID에 값이 존재함 
	 * @param  rowCount					row count (Integer.MIN_VALUE 최대)
	 * @param  exportColumnLinkList		link Column 명  (TITLE 칼럼명)
	 * @param  indexPrint				row no 출력 유무 (true, false)
	 * @throws Throwable
	 * @return String
	 * @description :
	 * ResultSet 데이터를 dhxGrid의 XML String으로 변환한다.   
	 * 
	 */	
	public static String convertToXml(			
			JResultSet jrs, 
			String rowIdColumn,
			String[] exportColumnList,
			String[] exportColumnListByUserData,
			int rowCount,
			String[] exportColumnLinkList,
			boolean indexPrint) throws Throwable{	
		return convertToXml(jrs, rowIdColumn, exportColumnList, exportColumnListByUserData, rowCount, exportColumnLinkList, indexPrint, null);	
	}
	
	/**
	 * <p> JResultSet -> XML 변환 함수(링크포함) (dhxGrid)</p>
	 *
	 * @param  jrs 						JResultSet	ResultSet DATA
	 * @param  rowIdColumn				row id 로 사용할 Column 명 (null 시 1,2,3,4,5 ~ 100, 200 으로 부여)
	 * @param  exportColumnList			TITLE Print Column 명 	    
	 * @param  exportColumnListByUserData	hidden Column 명 (null 시 hidden column 없음),출력은 안되고 GRID에 값이 존재함 
	 * @param  rowCount					row count (Integer.MIN_VALUE 최대)
	 * @param  exportColumnLinkList		link Column 명  (TITLE 칼럼명)
	 * @param  indexPrint				row no 출력 유무 (true, false)
	 * @param  gridName					grid 명	
	 * @throws Throwable
	 * @return String
	 * @description :
	 * ResultSet 데이터를 dhxGrid의 XML String으로 변환한다.   
	 * 
	 */	
	public static String convertToXml(			
								JResultSet jrs, 
								String rowIdColumn,
								String[] exportColumnList,
								String[] exportColumnListByUserData,
								int rowCount,
								String[] exportColumnLinkList,
								boolean indexPrint,
								String gridName) throws Throwable{  
				
		if(jrs == null){
			throw new NullPointerException("[KausGrid.convertToXml] jrs is NULL");
		}
		
		if (!(jrs instanceof JResultSet)) {
			throw new IllegalArgumentException("supports only JResultSet type.");
		}
		
		if(gridName == null || "".equals(gridName)){
			gridName = "";
		}
		
		// row count
		if (rowCount == Integer.MIN_VALUE) {
			rowCount = jrs.getRowCount();
		}
		
		
		// column count
		int colCount = 0;
		if(exportColumnList == null){
			if(jrs != null && rowCount != 0) {
				colCount = jrs.getColumnCount();
			}
		}else{
			colCount = exportColumnList.length;
		}		
		
		// cell link setting
		boolean[] linkBoolean = new boolean[colCount]; 
		if(exportColumnLinkList != null){
			for(int i=0; i < colCount; i++){

				linkBoolean[i] = false;				
				
				// export Column 명이 CHECKBOX_0 or CHECKBOX_1 일 경우 loop continue;
				if(CHECKBOX_0.equals(exportColumnList[i]) || CHECKBOX_1.equals(exportColumnList[i]) || ROW_ID.equals(exportColumnList[i]) ) {
					continue;
				}
				
				try{
					for(int k=0; k< exportColumnLinkList.length; k++){
							
						if(exportColumnList == null){
							
							if(jrs.getColumnName(i) == exportColumnLinkList[k]){
								linkBoolean[i] = true;
								break;
							}
						}else{
						
							// fommating 지원에 따른 수정 : [flield명:fommater]형식으로 넘어옴
							String arrTmp[] = getFieldNameAndAttribute(exportColumnList[i]);
							
							if(arrTmp[0] == exportColumnLinkList[k]){
								linkBoolean[i] = true;
								break;
							}
						}
					}
				}catch (Exception e){
					e.toString();					
					throw new Exception("cell link setting error");
				}
			}
		}

		String rowId = "";
				
		// XML Data Converting
		StringBuffer strXML = new StringBuffer("");
		try {
			strXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			//strXML.append("<?xml version=\"1.0\" encoding=\"EUC-KR\"?>");
			strXML.append("<rows>");
			// xml data making
			for ( int i = 0; jrs.next() ; i++ ) {
				
				// row id set
				if (rowIdColumn == null) {
					rowId = i+1+"";
				} else {
					rowId = jrs.getString(rowIdColumn).trim();
				}
				
				strXML.append("<row id=\"").append(rowId).append("\">");
				
				// row id cell add
				if(indexPrint == true) {
					strXML.append("<cell>").append(rowId).append("</cell>");
				}
				
				// data cell add
				for(int k = 0; k < colCount ; k++) {
					
					// export Column 명을 지정하지 않았을 때 
					if (exportColumnList == null) {
						if(linkBoolean[k] == false){						
							strXML.append("<cell>")
							.append(convertSpecialCharacter(jrs.getString(k).trim()))
							.append("</cell>");
						}else{
							strXML.append("<cell>")
							.append(convertSpecialCharacter(jrs.getString(k).trim()))
							.append("^").append("javascript:onClickCol_").append(gridName).append(k).append("(").append("\"").append(rowId).append("\"").append(")")
							.append("^").append("_self")
							.append("</cell>");					
						}
					}else{
						
					// export column 명을 지정했을 때 
						if (CHECKBOX_0.equals(exportColumnList[k])) {
							strXML.append("<cell>")
							.append("0")
							.append("</cell>");
						} else if (CHECKBOX_1.equals(exportColumnList[k])) {
							strXML.append("<cell>")
							.append("1")
							.append("</cell>");
						} else if (ROW_ID.equals(exportColumnList[k])) {
							strXML.append("<cell>")
							.append(rowId)
							.append("</cell>");							
						} else {
							String arrTmp[] = getFieldNameAndAttribute(exportColumnList[k]);
							
							if(linkBoolean[k] == false){						
								strXML.append("<cell>")
								.append(convertSpecialCharacter(
										(arrTmp.length == 1 ? jrs.getString(arrTmp[0]).trim() : convertFormat(jrs.getString(arrTmp[0]).trim(), arrTmp[1])))
									)
								.append("</cell>");
							}else{
								strXML.append("<cell>")
								.append(convertSpecialCharacter(
										(arrTmp.length == 1 ? jrs.getString(arrTmp[0]).trim() : convertFormat(jrs.getString(arrTmp[0]).trim(), arrTmp[1])))
									)
								.append("^").append("javascript:onClickCol_").append(gridName).append(k).append("(").append("\"").append(rowId).append("\"").append(")")
								.append("^").append("_self")
								.append("</cell>");
							}							
						}
					}
				}
				
				// hidden cell add 
				if(exportColumnListByUserData != null){
					for(int k = 0; k < exportColumnListByUserData.length ; k++) {
						strXML.append("<cell>")
						.append(convertSpecialCharacter(jrs.getString(exportColumnListByUserData[k]).trim()))
						.append("</cell>");
					}
				}
				
				strXML.append("</row>");	
			}
			strXML.append("</rows>");
		} catch (Throwable e){
			System.out.println("Grid to xml converting error.");
			e.printStackTrace();
		}

		return strXML.toString();
	}
	
	
	/**
	 * <p> JResultSet -> HTML TABLE 변환 함수 </p>
	 *
	 * @param  jrs 						JResultSet	ResultSet DATA
	 * @param  exportColumnList			해당 필드
	 * @param  exportColumnHeader       헤더에 표시할 컬럼 타이틀
     * @param  exportColumnFormat       값 포맷 (숫자에 대해서만 포맷을 준다.)
	 * @param  rowCount					row count (Integer.MIN_VALUE 최대)
	 * @return String
	 * @description :
	 * ResultSet 데이터를 HTML Table String으로 변환한다.
	 *
	 */
	public static String convertToTable(
								JResultSet jrs,
								String[] exportColumnList,
                                String[] exportColumnHeader,
                                String[] exportColumnFormat,
								int rowCount) throws Throwable{

		if(jrs == null){
			return "";
		}

		if (!(jrs instanceof JResultSet)) {
			throw new IllegalArgumentException("supports only JResultSet type.");
		}

		// row count
		if (rowCount == Integer.MIN_VALUE) {
			rowCount = jrs.getRowCount();
		}


        // column 추출 관련 정보 확인
		int colCount = 0;
		if(exportColumnList == null){
			colCount = jrs.getColumnCount();
            exportColumnList = new String[colCount];
            for(int i = 0; i < colCount ; i++) {
                exportColumnList[i] = jrs.getColumnName(i);
            }
		}else{
			colCount = exportColumnList.length;
		}

        if(exportColumnHeader==null){
            exportColumnHeader = (String[])exportColumnList.clone();
        }

        if(exportColumnFormat==null){
            exportColumnFormat = new String[colCount];
            for(int i = 0; i < colCount ; i++) {
                exportColumnFormat[i] = "";
            }
        }

		// TABLE HTML CODE GENERATING
		StringBuffer sbHTML = new StringBuffer("");
        String[] exportColumnClass = new String[colCount];
        String tmpStr = null;

		try {
			sbHTML.append("<TABLE>");

            //set header
            sbHTML.append("<TR>");
            for(int i = 0; i < colCount ; i++) {
                sbHTML.append(HtmlUtility.translate(exportColumnHeader[i]));

                //컬럼 style 매칭
                if(exportColumnFormat[i].equalsIgnoreCase("TEXT")){
                    exportColumnClass[i]="mso-number-format:\\@";
                } else if(exportColumnFormat[i].equalsIgnoreCase("SSN")){
                    exportColumnClass[i]="mso-number-format:000000\\-0000000";
                } else if(exportColumnFormat[i].equalsIgnoreCase("TAXID")){
                    exportColumnClass[i]="mso-number-format:000\\-00\\-00000";
                } else if(exportColumnFormat[i].equalsIgnoreCase("ACCTNO")){
                    exportColumnClass[i]="mso-number-format:000\\-00\\-000000";
                } else if(exportColumnFormat[i].equalsIgnoreCase("CLERKNO")){
                    exportColumnClass[i]="mso-number-format:0000000";
                } else if(exportColumnFormat[i].equalsIgnoreCase("AMT0")){
                    exportColumnClass[i]="mso-number-format:\\#\\,\\#\\#0";
                } else if(exportColumnFormat[i].equalsIgnoreCase("AMT2")){
                    exportColumnClass[i]="mso-number-format:\\#\\,\\#\\#0\\.00";
                } else if(exportColumnFormat[i].equalsIgnoreCase("DATE")){
                    exportColumnClass[i]="mso-number-format:0000\\-00\\-00";
                } else {
                    exportColumnClass[i]="";
                }
            }
            sbHTML.append("</TR>");

            //fill data
			for ( int i = 0; jrs.next() ; i++ ) {

				sbHTML.append("<TR>");

                // data cell add
				for(int k = 0; k < colCount ; k++) {
                    tmpStr = jrs.getString(exportColumnList[k]).trim();
					// format이 정해져 있지 않으면 문자열
					if (exportColumnFormat[k].equals("")) {
						sbHTML.append("<TD>").append(HtmlUtility.translate(tmpStr));
                        
					} else {
                        sbHTML.append("<TD style='");
                        if(exportColumnFormat[k].equals("SSNTAX")) {
                            if(tmpStr.length()==13){    //주민번호
                                sbHTML.append("mso-number-format:000000\\-0000000");
                            } else if(tmpStr.length()==10){ //사업자번호
                                sbHTML.append("mso-number-format:000\\-00\\-00000");
                            }
                        } else {
                            sbHTML.append(exportColumnClass[k]);
                        }

                        sbHTML.append("'>").append(tmpStr);
                    }

                    sbHTML.append("</TD>");
				} // end of column loop

                sbHTML.append("</TR>");
			} //end of record loop
			
            sbHTML.append("</TABLE>");

		} catch (Throwable e){
			System.out.println("Grid to html table converting error.");
			e.printStackTrace();
		}

		return sbHTML.toString();
	}
	
	
	// <, >, & 문자 존재시 <![CDATA[ ]]> 로 감싸기 
	public static String convertSpecialCharacter(String value) {
		
		// 2009.01.13, Ajax호출시 &nbsp로 인해 xml parsing에러가 발행해서 주석  처리함  
		if (value == null || value == "") {
			//return "&nbsp;";
			return "";
		}
		
		StringBuffer sbuf = new StringBuffer("");
			
		if ( (value.indexOf('<') >= 0) || (value.indexOf('>') >= 0) || (value.indexOf('&') >= 0)){
			sbuf.append("<![CDATA[").append(value).append("]]>\n");
		} else {
			sbuf.append(value).append("\n");
		}
		
		return sbuf.toString();
	}
	
	/**
	 * 값에 특정 포맷을 지정할 수 있도록 한다.<br>
	 * 사용방법 필드명:FMT인자
	 *	  예) 계좌번호포맷 ==> ACCT_NO:FMT_ACCT_NO
	 * 	  FORMAT 인자
     *			FMT_ACCT_NO        : 계좌번호 포맷 
	 *		    	입력 : 123455678902
     *		    	결과 : 123-45-678901 
     *		 	FMT_DOUBLE	       : DOUBLE 포맷
     *		 		입력 : 1234556789.00
     *		 		결과 : 123,456,789.00
     *		 	FMT_NUMBER	       : NUMBER 포맷
     *		 		입력 : 1234556789
     *		 		결과 : 123,456,789
     *		 	FMT_DATE		   : 날짜포맷 
     *		 		입력 : 20080730		
     *		 		결과 : 2008-07-30	
     *			FMT_MONTH : 월포맷 
     *		 		입력 : 200809
     *		 		결과 : 2008-09
     *		 	FMT_TIME : 시간포맷
     *		 		입력 : 200809
     *		 		결과 : 20:08:09
     *		 	FMT_CARD_NO        : 신용카드포맷
     *		 		입력 : 123456789012345
     *		 		결과 : 123-4567-8901-2345
     *		 	FMT_NUMBER_UNIT : 1000자리 잘림
     *		 		입력 : 123456789.00
     *		 		결과 : 123,456
     *		 	FMT_POST_NO
     *		 		입력 : 390100
     *		 		결과 : 390-100
     *		 	FMT_SSN_TAXID
     *		 		입력 : 1234567890(사업자번호)  	입력 : 1234567890123(주민번호)
     *		 		결과 : 123-45-67890				결과 : 123456-7890123
     *		 	FMT_BR
     *		 		입력 : SSSSS\nssss  		
     *		 		결과 : SSSSS<BR>ssss		
     *		 	FMT_BRNCH_NM
     *		 		입력 : 111
     *		 		결과 : 제일
     *		 	KAML_코드명
     *		 		예) JOB_CHRGPERS:KAML_AML담당업무
     *		 		결과 : TMU관리자
     *			FMT_INT  : ","구분없이 출력됨 
     *				입력 : 123456789.00
     *		 		결과 : 123456789
     *			FMT_FILE  : 파일첨부시  [원래파일명_yyyyMMddHHmmss.확장자] ==> [원래파일명.확장자]로 변홤
     *				입력 : 원래파일명_yyyyMMddHHmmss.확장자
     *		 		결과 : 원래파일명.확장자
     *			FMT_MEGA  : 입력된 size를 mega단위로 리턴
     *				입력 : 1024
     *		 		결과 : 1			
     *          FMT_CLERK_NM_S : 연속된 행번을 성명으로 출력 함(2010.04.19, 재산상이익 프로젝트 필요 
	 *              입력 : 139297,139076
	 *              결과 : 김민광,안상엽
	 * 작성자 : 지 연 구<br>
	 * 작성일자 : 2008. 09. 26<br>
	 * @param inputData 입력문자열(포맷을 가질 수 있음)
	 * @return
	 */
	private static String convertFormat(String inputData, String fmt) {
		if ( fmt == null ) return inputData;
		
		String fmtString = fmt.toUpperCase();
		String returnValue = "";
		
		try {
			if ( "FMT_ACCT_NO".equals(fmtString) ) {
				returnValue = StringUtil.setGyejaNO(inputData,1);
			}
			else if ( "FMT_NUMBER".equals(fmtString) ) {
				returnValue = StringUtil.formatNumber(inputData,1);
			}
			else if ( "FMT_DOUBLE".equals(fmtString) ) {
				returnValue = StringUtil.formatNumber(inputData);
			}			
			else if ( "FMT_TIME".equals(fmtString) ) {
				returnValue = StringUtil.makeTimeType(inputData);
			}			
			else if ( "FMT_DATE".equals(fmtString) ) {
				returnValue = StringUtil.formatDate(inputData);
			}
			else if ( "FMT_TIMESTAMP".equals(fmtString) ) {
				returnValue = StringUtil.makeDateTimeType(inputData);
			}
			else if ( "FMT_MONTH".equals(fmtString) ) {
				returnValue = StringUtil.formatDate(inputData);
			}
			else if ( "FMT_CARD_NO".equals(fmtString) ) {
				returnValue = StringUtil.setGyejaNO(inputData,1);
			}
			else if ( "FMT_SSN_TAXID".equals(fmtString) ) {				
				returnValue = StringUtil.setJuMinNO(inputData);
			}
			else if ( "FMT_BR".equals(fmtString) ) {				
				returnValue = inputData.replaceAll("\n", "<br>").replaceAll("\r", "");
			}
			else if ( "FMT_HTML_BR".equals(fmtString) ) {				
				returnValue = inputData.replaceAll("<br>", "&bnsp;");
			}
			else if ( "FMT_INT".equals(fmtString) ) {				
				returnValue = StringUtil.dFmtToIntFmt(inputData);
			}
			else if ( "FMT_MEGA".equals(fmtString) ) {				
				long megaSize = StringUtil.stol(inputData) ;
				returnValue = megaSize > 0 ? StringUtil.ltos(megaSize/1024) : inputData ;
			}
			/* 2011.08.22, 마스킹 처리 */
			// 실명번호 마스킹
			else if ( "FMT_MASK_SSN".equals(fmtString) ) {				
				returnValue = StringUtil.maskingPersonalInfo(inputData,"SSN");
			}
			// 거래자명/고객명 마스킹
			else if ( "FMT_MASK_NAME".equals(fmtString) ) {				
				returnValue = StringUtil.maskingPersonalInfo(inputData,"NAME");
			}
			// 계좌번호 마스킹
			else if ( "FMT_MASK_ACCT".equals(fmtString) ) {				
				returnValue = StringUtil.maskingPersonalInfo(inputData,"ACCT");
			}			
		}
		catch ( Throwable t) {}
		
		return returnValue;
	}
	
	/**
	 * Commarea의 속성 값과 포맷타입을 분리하여 리턴한다.
	 * <br>
	 * 작성자 : 지 연 구<br>
	 * 작성일자 : 2008. 09. 27<br>
	 * @param inputData
	 * @return
	 */
	private static String[] getFieldNameAndAttribute(String inputData) {
		return inputData.split(":");
	}
	
	/**
	 * <p> ajax 페이지에서 JSON데이터 포맷 구현시 사용
	 *     jrs가 null시 사용자정의 필드의 값만 세팅됨
	 *     "rusult":"true" 는 default로 사용됨
	 * @param jrs	   			JResultSet 	Resultset Object
	 * @param jrsColumnList	   	String [] 	Resultset의 출력 필드 
	 * @param exportColumnList	String [] 	사용자 정의 필드
	 * @param values	   		String [] 	사용자 정의 필드 value 
	 * @return
	 * 		JSON 데이터 형식으로 리턴함 
	 * 		{
     *           data:
     *           [
     *               {
     *                   "result" : '<%=result%>',
     *                   "sernos" : '<%=sernos%>'
     *               }
     *           ]
     *       }
	 */
	public static String convertToJson(JResultSet jrs, String [] jrsColumnList, String [] exportColumnList, String [] values)
	{
		boolean isDefault = false ;
		String resultName  = "result";
		String resultValue = "true";
		
		if (exportColumnList == null)
		{
			exportColumnList = new String[]{};
			values = new String[]{};
		}
		
		if  (exportColumnList.length != values.length)
		{
			//Logger.error("[convertToJson] 사용자정의 필드와 값의 수가 일치 하지 않습니다.");
			return getExceptionResult("J",new Exception("[convertToJson] 사용자정의 필드와 값의 수가 일치 하지 않습니다."));
		}
		
		//1. default "result"값이 세팅 되어 있는지 확인함
		for(int count = 0 ; exportColumnList.length > count ; count++)
		{
			if ( resultName == exportColumnList[count])
			{
				isDefault = true;
				break;
			}
		}
		
		StringBuffer defaultValue = new StringBuffer();
		
		// JSON 선언 부분
		defaultValue.append("{\n")
					.append("  data:\n")
					.append("  [	\n")
					.append("	{\n");
		
		//2. default "result"값이 세팅 되어 있지 않으면 세팅해줌
		if (!isDefault)
		{
			defaultValue.append("	\"").append(resultName).append("\"")
						.append(":")
						.append("\"").append(resultValue).append("\"");
			if (exportColumnList.length > 0) 
				defaultValue.append(",").append("\n");	
		}
		
		String column = null;
		String value = null;
		//3. 사용자 정의 필드 세팅
		for(int count = 0 ; exportColumnList.length > count ; count++)
		{	
			boolean needsAppendComma = false;
			
			//fommating 지원에 따른 수정 : [flield명:fommater]형식으로 넘어옴
			String arrTmp[] = getFieldNameAndAttribute(exportColumnList[count]);
			column = arrTmp.length == 1 ? exportColumnList[count] : arrTmp[0];
			value = "null".equals(values[count].toLowerCase()) ? "" : values[count];
			value = arrTmp.length == 1 ? value : convertFormat(value, arrTmp[1]);
			
			//null이라는 값이 들어 오면 ""처리 함
			needsAppendComma = count < (exportColumnList.length - 1);
			
			defaultValue.append("	\"").append(column).append("\"")
						.append(":")
						.append("\"").append(value).append("\"");
			
			if (needsAppendComma) {
				defaultValue.append(",");
			}
			
			defaultValue.append("\n");
		}	
		
		//4.Jresultset이 있으면 이어서 붙여 준다.
		if (jrs != null)
		{
			if (!(jrs instanceof JResultSet)) {
				throw new IllegalArgumentException("supports only JResultSet type.");
			}
			
			defaultValue.append(",");
			
			//jrs에서 데이터가 없을 경우 NullPointerException이 발행하여 수정
			int jrsRowCnt = jrs.getRowCount();
			for(int count = 0 ; jrsColumnList.length > count ; count++)
			{		
				boolean needsAppendComma = false;
				
				//	fommating 지원에 따른 수정 : [flield명:fommater]형식으로 넘어옴
				String arrTmp[] = getFieldNameAndAttribute(jrsColumnList[count]);
				column = arrTmp.length == 1 ? jrsColumnList[count] : arrTmp[0];
				
				// jrs의 결과값이 0인 경우 빈값을 세팅함
				if (jrsRowCnt == 0)
				{ 
					value = "";	
				}
				else
				{	
					value = arrTmp.length == 1 ? jrs.getString(column).trim() : convertFormat(jrs.getString(column).trim(), arrTmp[1]);
				}
				
				needsAppendComma = count < (jrsColumnList.length - 1);
				
				defaultValue.append("	\"").append(column).append("\"")
							.append(":")
							.append("\"").append(value).append("\"");
				
				if (needsAppendComma) {
					defaultValue.append(",");
				}
				
				defaultValue.append("\n");
			}	
		}
		
		// JSON 선언 부분
		defaultValue.append(" 	}\n")
					.append("  ]\n")
					.append("}\n");
			
		return defaultValue.toString();
	}
	
	/**
	 * <p> ajax 페이지에서 JSON데이터 포맷 구현시 사용
	 * @param exportColumnList	String [] 	사용자 정의 필드
	 * @param values	   		String [] 	사용자 정의 필드 value 
	 * @return
	 * 		JSON 데이터 형식으로 리턴함 
	 * 		{
     *           data:
     *           [
     *               {
     *                   "result" : '<%=result%>',
     *                   "sernos" : '<%=sernos%>'
     *               }
     *           ]
     *       }
	 */
	public static String convertToJson(String [] exportColumnList, String [] values)
	{
		return convertToJson(null, null,exportColumnList,values);
	}	
	
	/**
	 * <p> ajax 페이지에서 JSON데이터 포맷 구현시 사용
	 *     DEFAULT값만 RETURN
	 * @return
	 * 		JSON 데이터 형식으로 리턴함 
	 * 		{
     *           data:
     *           [
     *               {
     *                   "result" : 'true',
     *               }
     *           ]
     *       }
	 */
	public static String convertToJson()
	{
		String [] exportColumnList = {"result"};
		String [] values = {"true"};
		return convertToJson(null, null,exportColumnList,values);
	}
	
	/**
	 * <p> ajax 페이지에서 Exception 발생시 return 값을 세팅해 줌
	 * @param returnGubun  			String 		J:json  G:grid
	 * @param exportColumnList	   	String [] 	name 부분
	 * @param values	   			String [] 	value 부분
	 * @param e			   			Exception   에러내용, 향후 필요시 로직으로 사용함	
	 * @return
	 * 		1. JSON 리턴시 
	 * 		{
     *           data:
     *           [
     *               {
     *                   "result" : '<%=result%>',
     *                   "sernos" : '<%=sernos%>'
     *               }
     *           ]
     *       }
     *      2. GRID 리턴시  
     *      <?xml version='1.0'?><rows></rows> 
	 */
	public static String getExceptionResult(String returnGubun, String [] exportColumnList, String [] values, Exception e)
	{
		StringBuffer defaultValue = new StringBuffer();
		
		if ("J".equals(returnGubun))
		{
			defaultValue.append("{\n")
						.append("  data:\n")
						.append("  [	\n")
						.append("	{\n");
			
			String column = null;
			String value = null;
			for(int count = 0 ; exportColumnList.length > count ; count++)
			{	
				boolean needsAppendComma = false;
				
				column = exportColumnList[count];
				value = values[count];
				
				needsAppendComma = count < (exportColumnList.length - 1);
				
				defaultValue.append("	\"").append(column).append("\"")
							.append(":")
							.append("\"").append(value).append("\"");
				
				if (needsAppendComma) {
					defaultValue.append(",");
				}
				
				defaultValue.append("\n");
			}	
			
			defaultValue.append(" 	}\n")
						.append("  ]\n")
						.append("}\n");
			
		}
		else if ("G".equals(returnGubun))
		{
			defaultValue.append("<?xml version='1.0'?><rows></rows><rows></rows> ");
		}
		
		return defaultValue.toString();
	}
	
	/**
	 * <p> ajax 페이지에서 Exception 발생시 return 값을 세팅해 줌
	 *     기본값으로 result만 가질 경우 사용함
	 * @param returnGubun  			String 		J:json  G:grid
	 * @param e			   			Exception   에러내용, 향후 필요시 로직으로 사용함	
	 * @return
	 * 		1. JSON 리턴시 
	 * 		{
     *           data:
     *           [
     *               {
     *                   "result" : '<%=result%>',
     *                   "sernos" : '<%=sernos%>'
     *               }
     *           ]
     *       }
     *      2. GRID 리턴시  
     *      <?xml version='1.0'?><rows></rows> 
	 */
	public static String getExceptionResult(String returnGubun,Exception e)
	{
		String [] exportColumnList = {"result"};
		String [] values = {"false"};
		return getExceptionResult(returnGubun,exportColumnList,values,e);
	}
	
	/**
	 * <p> ajax 페이지에서 Exception 발생시 return 값을 세팅해 줌
	 *     기본값으로 result와 message를 세팅할 때 사용
	 * @param returnGubun  			String 		J:json  G:grid
	 * @param message  				String 		메세지
	 * @param e			   			Exception   에러내용, 향후 필요시 로직으로 사용함	
	 * @return
	 * 		1. JSON 리턴시 
	 * 		{
     *           data:
     *           [
     *               {
     *                   "result" : '<%=result%>',
     *                   "sernos" : '<%=sernos%>'
     *               }
     *           ]
     *       }
     *      2. GRID 리턴시  
     *      <?xml version='1.0'?><rows></rows> 
	 */
	public static String getExceptionResult(String returnGubun,String message, Exception e)
	{
		String [] exportColumnList = {"result","message"};
		String [] values = {"false",StringUtil.NVL(message)};
		return getExceptionResult(returnGubun,exportColumnList,values,e);
	}
	
	private static Connection getConnection()
	{
		String DB_DRIVER = "COM.ibm.db2.jdbc.net.DB2Driver" ;
		String DB_URL = "jdbc:db2://23.24.14.159:6789/AIS";              
		//String DB_DRIVER = "COM.ibm.db2.jdbc.app.DB2Driver" ;
	    //String DB_URL = "jdbc:db2:MIS_TEST";              
	    String DB_USER = "uaiss11";                                      
	    String DB_PASSWORD= "uaiss11";
	    
	    Connection conn = null 	;
	    
	    try {
			// The newInstance() call is a work around for some
			// broken Java implementations
	    
			Class.forName(DB_DRIVER).newInstance(); 
		}
		catch (Exception E) {
			System.out.println("Unable to load driver.");
			E.printStackTrace();
		}
	    
		try{
			conn = java.sql.DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		}
		catch(SQLException sqle)
		{
			System.out.println("Unable to connect to DB.");
			sqle.printStackTrace();
		}
		
	    return conn;
	}
	
	private static JResultSet getSQL(String sql){
	        
        Connection conn = null;
        Statement  stmt = null;
        JResultSet jrs = null;
        
        try {
        
            conn = getConnection();

            stmt = conn.createStatement();

            System.out.println(sql);
           jrs =  new JResultSet(stmt.executeQuery(sql)) ;

        } catch(Exception e){
            
                System.out.println(e.toString());

        } finally {
        
              if ( stmt != null ) try {stmt.close(); }catch(Exception e){} 
              if ( conn != null ) 
                    try{
                        conn.close();
                    }catch(Exception e){} 

        }        
        
        return jrs;
    }
	
	public static void main(String args [])
	{
		String [] jrsColumnList = null;
		String [] exportColumnList = null;
		String [] values = null;
		String ajaxGubun = "J" ;
		JResultSet jrs = null ;
		
		//String clerks = "www" ;
		
		//System.out.println(DhxGrid.convertFormat(clerks,"FMT_CLERK_NM_S")) ;
		
		/*
		exportColumnList = new String [] {"result","message","serno2","DATE:FMT_DATE"};
		values = new String [] {"true","조회된 데이터가 존재하지 않습니다.","111","20090101"};
		//exportColumnList = new String [] {"message","serno2"};
		//values = new String [] {"조회된 데이터가 존재하지 않습니다.","111"};
		
		//System.out.println(DhxGrid.getExceptionResult(ajaxGubun,new Exception("error occured")));
		
		StringBuffer sqlBuf = new StringBuffer(""); 
		sqlBuf = new StringBuffer(""); 
		sqlBuf.append("\n SELECT  RTRIM(scnr_id) AS scnr_id                                   ");  //시나리오 ID
		sqlBuf.append("\n      ,RTRIM(scnr_ctgry) AS scnr_ctgry                               ");  //시나리오 분류
		//sqlBuf.append("\n      ,REPLACE(REPLACE(scnr_cntnt,CHR(10),''),CHR(13),'')            ");  //시나리오 내용
		sqlBuf.append("\n      ,scnr_cntnt											            ");  //시나리오 내용
		sqlBuf.append("\n      ,scnr_summ                                                     ");  //시나리오 요약
		sqlBuf.append("\n      ,astrt_cycl                                                    ");  //추출주기
		sqlBuf.append("\n      ,astrt_range                                                   ");  //추출범위
		sqlBuf.append("\n      ,astrt_unit                                                    ");  //추출단위
		sqlBuf.append("\n      ,inspct_cycl                                                   ");  //점검주기
		sqlBuf.append("\n      ,RTRIM(grp_scnr_id) AS grp_scnr_id                             ");  //그룹시나리오 ID
		sqlBuf.append("\n      ,risk_grade                                                    ");  //리스크등급
		sqlBuf.append("\n      ,prfrnc_rank                                                   ");  //우선순위
		sqlBuf.append("\n      ,regist_chng_rsn                                               ");  //등록 및 변경사유
		sqlBuf.append("\n      ,use_flg                                                       ");  //사용여부
		sqlBuf.append("\n      ,vrble_nm10                                                    ");  //고객구분10
		sqlBuf.append("\n      ,vrble_11                                                      ");  //개인고객 - EDD
		sqlBuf.append("\n      ,vrble_12                                                      ");  //개인고객 - 기타
		sqlBuf.append("\n      ,vrble_13                                                      ");  //법인고객 - EDD
		sqlBuf.append("\n      ,vrble_14                                                      ");  //법인고객 - 기타
		sqlBuf.append("\n      ,vrble_nm20                                                    ");  //고객구분20
		sqlBuf.append("\n      ,vrble_21                                                      ");  //개인고객 - EDD
		sqlBuf.append("\n      ,vrble_22                                                      ");  //개인고객 - 기타
		sqlBuf.append("\n      ,vrble_23                                                      ");  //법인고객 - EDD
		sqlBuf.append("\n      ,vrble_24                                                      ");  //법인고객 - 기타
		sqlBuf.append("\n      ,vrble_nm30                                                    ");  //고객구분30
		sqlBuf.append("\n      ,vrble_31                                                      ");  //개인고객 - EDD
		sqlBuf.append("\n      ,vrble_32                                                      ");  //개인고객 - 기타
		sqlBuf.append("\n      ,vrble_33                                                      ");  //법인고객 - EDD
		sqlBuf.append("\n      ,vrble_34                                                      ");  //법인고객 - 기타
		sqlBuf.append("\n      ,vrble_nm40                                                    ");  //고객구분40
		sqlBuf.append("\n      ,vrble_41                                                      ");  //개인고객 - EDD
		sqlBuf.append("\n      ,vrble_42                                                      ");  //개인고객 - 기타
		sqlBuf.append("\n      ,vrble_43                                                      ");  //법인고객 - EDD
		sqlBuf.append("\n      ,vrble_44                                                      ");  //법인고객 - 기타
		sqlBuf.append("\n    FROM aml.tm_scnr_info                                            ");
		sqlBuf.append("\n    WHERE scnr_id = 'GR003K'                                         ");
		sqlBuf.append("\n    WITH UR;                                                         ");
		
		jrsColumnList = new  String[]{"scnr_cntnt:FMT_BR","scnr_cntnt"};
        try{
        	jrs = getSQL(sqlBuf.toString());
        }catch(Exception e ){System.out.println(DhxGrid.getExceptionResult(ajaxGubun,e));}	
        
        //System.out.println(jrs.getDouble("WON_TRAN_AMT"));
        //System.out.println(GenUtil.dFmtToIntFmt(jrs.getDouble("WON_TRAN_AMT")));
        //DhxGrid.convertToJson(new String[]{"result"},new String[]{"false"});
        String result [] = {"result"};
		String value [] = {"false"};
    	DhxGrid.convertToJson(result,value); 
		System.out.println(DhxGrid.convertToJson(jrs,jrsColumnList,exportColumnList,values));
        //System.out.println(DhxGrid.convertToJson());
         * 
         */
	}
	
}
