package lee.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelLoadXlsAdapter {
	private String[] titles = new String[19];
	//
	// start update.2013.08.26
	//
	private static final String SQL_DELETE_TITLE ="delete from tb_excel_title";
	private static final String SQL_UPDATE_TITLE =
			"insert into tb_excel_title values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	//
	// end update.2013.08.26
	//
	/**
	 * update source_data table with recent excel file
	 * 1. delete source_data table
	 * 2. insert new data into source_data table
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public boolean updateData(String path) 
			throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		
		Connection conn = getConnection();
				
		/**
		 * prepare for batch exception
		*/
		conn.setAutoCommit(false);
        
		/**
         * remove existing data
        */
        
        Statement stmt;
        
        /*
		 * 2013.04.20, excel?�일 ?�싱 ?�러??기존 ?�이?��? 모두 ??�� ?�기 ?�문?? ?�시 ?�이블에   ??�� ??
		 *             모드 ?�업 ?�나�??�서 �??�이블에  ??�� ?�다.
		 * */
        System.out.println("********** 1. temp table deleting start *************");
        try {
			stmt = conn.createStatement();
	        stmt.execute("delete from tb_excel_data_temp");
		} catch (SQLException e1) {
			e1.printStackTrace();
			conn.rollback();
			return false;
		}
		System.out.println("********** 2. temp table deleting end *************");
		/*
		 * 2013.03.25, 2008?�드(f9)�?2013?�드�?�?��?�고 ?�치 �?��??
		 **/
        /**
         * insert new data
         */
        PreparedStatement pStmt = null;

        File file = new File(path);
		
		POIFSFileSystem fileSystem = new POIFSFileSystem(new FileInputStream(file));

		HSSFWorkbook work = new HSSFWorkbook(fileSystem);
		/**
		 * process just 1st sheet
		 */
		
		for (int loop = 0; loop < 1; loop++) {
			HSSFSheet sheet = work.getSheetAt(loop);
			int rows = sheet.getPhysicalNumberOfRows();
			HSSFRow row = null;
			String cellData = "";
			//
			// start update.2013.08.26
			//
			/**
			 * 헤더 먼저 처리
			 * 두번째 행이 타이틀 명
			 */
			extractTitles(sheet);
			/**
	         * remove title table
	         */
	        stmt = null;
			stmt = conn.createStatement();
	        stmt.execute(SQL_DELETE_TITLE);
	        /**
	         * insert new title data
	         */
	        pStmt = null;
			pStmt = conn.prepareStatement(SQL_UPDATE_TITLE);
			for (int i=0; i<this.titles.length; i++) {
				pStmt.setString(i+1, titles[i]);
			}
			pStmt.addBatch();
			pStmt.executeBatch();
			
			StringBuffer insertSqlBuf = new StringBuffer()
				.append("insert into tb_excel_data_temp ")
				.append("(f1,f2,f3,f4,f5,f6,f7,f8,f10,f11,f12,f13,f14,f9,f15,f16,f17,f18,f19)")
				.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			/**
         	* insert new data
         	*/
			pStmt = 
					conn.prepareStatement(insertSqlBuf.toString());
			//
			// end update.2013.08.26
			//
			/**
			 * get data from 3th row
			 */
			for (int rownum = 2; rownum < rows; rownum++) {
				row = sheet.getRow(rownum);
				
				if (row != null) {
					Format formatter = new DecimalFormat("0.##");
					int colCount = row.getLastCellNum();
					
					for (int cellnum = 0; cellnum < colCount; cellnum++) {
						HSSFCell cell = row.getCell(cellnum);
						
						if (cell != null) {
							int cellType = cell.getCellType();
							/**
							 * data column
							 */
							if (0 == cellnum) {
								/**
								 * nobel77 : need check
								 */
								cellData = numericToDateFormat(cell.getNumericCellValue()).toString();
								cellData = cellData.trim();
								pStmt.setString(cellnum+1, cellData);
							} else {
								switch (cellType) {

								case HSSFCell.CELL_TYPE_NUMERIC:
									cellData = ""+formatter.format(cell.getNumericCellValue());
									break;
								case HSSFCell.CELL_TYPE_STRING:
									cellData = cell.getStringCellValue();
									cellData = cellData.replaceAll("\n", "<br>");
									break;
								 case HSSFCell.CELL_TYPE_FORMULA :
							          cellData = ""+formatter.format(cell.getNumericCellValue());
							          break;	
								default:
									cellData = "";
									break;
								}

								pStmt.setString(cellnum+1, cellData);
							}
						} else {
							cellData = "";
							pStmt.setString(cellnum+1, cellData);
						}
					}
					
					pStmt.addBatch();
				}
			}
		}
        int[] totalInserted = new int[5000];
        
        System.out.println("********** 3. excel file loading IN temp table start *************");
        
        try {
			totalInserted = pStmt.executeBatch();
        } catch(BatchUpdateException e) {
			e.printStackTrace();
            totalInserted = e.getUpdateCounts();
            conn.rollback();
        } catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} 
        
        System.out.println("********** 4. excel file loading IN temp table end. TOTAL COUNT : " +  totalInserted.length + " *************");
        
        System.out.println("********** 5. DATA loading IN REAL TABLE START *************");
        
        try {
			stmt = conn.createStatement();
	        stmt.execute("delete from tb_excel_data");
	        stmt.execute("INSERT INTO tb_excel_data SELECT * FROM tb_excel_data_temp");
	        
		} catch (SQLException e1) {
			e1.printStackTrace();
			conn.rollback();
			return false;
		}
		
		   System.out.println("********** 6. DATA loading IN REAL TABLE END *************");
		
        conn.commit();
        
        stmt.close();
        pStmt.close();
        conn.close();
        
        return true;
	}
	/**
	 * extract Titles
	 * @param sheet
	 */
	private void extractTitles(HSSFSheet sheet) {
		HSSFRow row = sheet.getRow(1);
		int colCount = row.getLastCellNum();
		
		for (int i=0; i<colCount; i++) {
			HSSFCell cell = row.getCell(i);
			if (cell != null) {
				if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
					this.titles[i] = "";
					this.titles[i] += ((int)cell.getNumericCellValue());
				} else if (HSSFCell.CELL_TYPE_STRING == cell.getCellType() ){
					this.titles[i] = cell.getStringCellValue().replaceAll("\n", "<br>");
				} else {
					System.out.println("엑셀 파일의 타이틀 정보에 오류 발생");
				}
			}
		}
	}
	/**
	 * 08/29 수정 :
	 * 날짜 포맷이 00-00-00으로 변경되어 DB에 저장 시, 00/00/00으로 날짜 데이터 변환
	 * 
	 * @param numericCellValue	(1)20309.0 7~8자리 날짜 포맷
	 * @return
	 */
	private StringBuffer numericToDateFormat(double numericCellValue) {
		
		StringBuffer date = new StringBuffer(String.valueOf(((int)numericCellValue)));
		Calendar oCalendar = Calendar.getInstance( );
		/**
		 * 31021-> 031021(년도가 1자리 인 경우 0을 앞에 추가)
		 * 1219 -> 000719(년이 없는 경우, 현재 년도 추가)
		 * 719  -> 000719(년이 없고 월이 1자리인 경우, 0 추가, 현재 년도 추가)
		 */
		int y = oCalendar.get(Calendar.YEAR);
		String year = String.valueOf(y);
		year = year.substring(2, 4);
		if (5 == date.length()) {
			date.insert(0,'0');
		} else if (3 == date.length()) {
			//719 ->000719
			date = (new StringBuffer("0").append(date));
			date = (new StringBuffer(year).append(date));
		} else if (4 == date.length()) {
			//1119 ->001119
			date = (new StringBuffer(year).append(date));
		}
		// 000719 ->00/0719
		date.insert(2,'/');
		// 000719 ->00/07/19
		date.insert(5,'/');
		
		return date;
	}

	public static final short EXCEL_COLUMN_WIDTH_FACTOR = 256;
	public static final int UNIT_OFFSET_LENGTH = 7;

	public static int widthUnits2Pixel(int widthUnits) {
		int pixels = (widthUnits / EXCEL_COLUMN_WIDTH_FACTOR)
				* UNIT_OFFSET_LENGTH;
		int offsetWidthUnits = widthUnits % EXCEL_COLUMN_WIDTH_FACTOR;
		pixels += Math.floor((float) offsetWidthUnits
				/ ((float) EXCEL_COLUMN_WIDTH_FACTOR / UNIT_OFFSET_LENGTH));
		return pixels;
	}
	
	public Connection getConnection(){
	    
	   Connection conn = null;
    
	    try{
			
		   	Context initCnt = new InitialContext();
		   	Context env = (Context) initCnt.lookup("java:comp/env/");
		   	DataSource ds = (DataSource) env.lookup("jdbc/jndi_lee");
		   	
	        /*
	        String DB_DRIVER = "org.gjt.mm.mysql.Driver" ;
	        String DB_URL = "jdbc:mysql://localhost:3306/hissfdb";              
	        String DB_USER = "hissf";                                      
	        String DB_PASSWORD= "wkdgkrwoeks20!!";                                   
	    	*/
	        //Class.forName(DB_DRIVER);  
	        //conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	        conn = ds.getConnection();
        
	   } catch(Exception e) {
                
         System.out.println(e.toString());

	   }
      
        return conn ;      
   	}
}
