package lee.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XlsAdapter {
	private int columnCount;
	/**
	 * ���� ������ �о xml ������ ���ϰ� Ÿ��Ʋ ���� ��
	 * 
	 * @param path		excel file path
	 * @param storage	���� ���� ��ġ
	 * @return
	 * @throws Exception 
	 */
	public boolean readExcel(String path, String storage) 
			throws Exception {
	
		File file = new File(path);
		String sourceFile = file.getName();
		
		POIFSFileSystem fileSystem = new POIFSFileSystem(new FileInputStream(file));

		HSSFWorkbook work = new HSSFWorkbook(fileSystem);

		/**
		 * output : 
		 *  excelfileName_sheetNo.xml
		 *  excelfileName_sheetNo.title
		 */
		int sheetNum = work.getNumberOfSheets();
		System.out.println("\n# sheet num : " + sheetNum);
		
		for (int loop = 0; loop < 1; loop++) {
			HSSFSheet sheet = work.getSheetAt(loop);
			/**
			 * ������ xml ���� �� ����
			 */
			String fileName = sourceFile +"_"+loop+".title";
			System.out.println("xml file Name : " + fileName);
			
			/**
			 * ��� ���� ó��
			 */
			HSSFRow row = sheet.getRow(0);
			createFile(fileName, storage, doHeader(sheet, row).toString());
			/**
			 * ���� �����͸� XML�� ó��
			 */
			fileName = sourceFile +"_"+loop+".xml";
			doXMLData(sheet, fileName, storage);
		}
		return true;
	}
	/**
	 * ���� ������ ù ���� Ÿ��Ʋ ������ �����Ͽ� ó��
	 * 
	 * JSON format:
	 * {"title":
		[
		 {"title1","title2","title3",...}
		]
	   }
	   
	 * @param sheet
	 * @param headerRow
	 */
	private StringBuffer doHeader(HSSFSheet sheet, HSSFRow headerRow) {
//		StringBuffer rowValue = new StringBuffer("{\"titles\":[");
		StringBuffer rowValue = new StringBuffer("");
		
		if (headerRow != null) {
			
			columnCount = headerRow.getLastCellNum();
			//rowValue.append("{");
	
			StringBuffer temp = new StringBuffer("");
			StringBuffer width = new StringBuffer("");
			
			for (int cellnum = 0; cellnum < columnCount; cellnum++) {
				HSSFCell cell = headerRow.getCell(cellnum);

				width.append(String.valueOf(widthUnits2Pixel(sheet.getColumnWidth(cellnum))) + ",");
				
				if (cell != null) {
					//temp.append("\"");
					if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
						temp.append((int)cell.getNumericCellValue());
						
					} else if (HSSFCell.CELL_TYPE_STRING == cell.getCellType() ){
						temp.append(cell.getStringCellValue().replaceAll("\n", "<br>"));
					} else {
						temp.append("");
					}
					//temp.append("\",");
					temp.append(",");
				}
			}
			width.deleteCharAt(width.length()-1);
			temp.deleteCharAt(temp.length()-1);
			
			rowValue.append(temp);
			//rowValue.append("}]}");
			
			/*
			System.out.println("----------------------------------------------------------------");
			System.out.println("width");
			System.out.println("----------------------------------------------------------------");
			System.out.println(width);
			*/
		}
		
		/*
		System.out.println("----------------------------------------------------------------");
		System.out.println("Title");
		System.out.println("----------------------------------------------------------------");
		System.out.println(rowValue);
		*/
		
		return rowValue;
	}
	/**
	 * XML ������ �����
	 * @param sheet
	 * @return
	 */
	private StringBuffer doXMLData(HSSFSheet sheet, String fileName, String storage) throws Exception {
		/*
		 <?xml version="1.0" encoding="UTF-8"?>
		 <rows> 
		   <row id="1001"> 
		   <cell>100</cell> 
		   <cell>AN:apple</cell> 
		   <cell>John Grisham</cell> 
		   <cell>12.99</cell> 
		   <cell>1</cell> 
		   <cell>05/01/1998</cell> 
		   </row>
		  </rows>
		*/
		String cellData = "";
		String filePath = storage + File.separator + fileName;
		int rows = sheet.getPhysicalNumberOfRows();
		HSSFRow row = null;
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// ��Ʈ ������Ʈ
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("rows");
		doc.appendChild(rootElement);
		
		for (int rownum = 1; rownum < rows; rownum++) {
			row = sheet.getRow(rownum);
			
			if (row != null) {
				//int cells = row.getPhysicalNumberOfCells();
				//int columnCount = row.getLastCellNum();
				StringBuffer rowValue = new StringBuffer("");
				rowValue.append("{");
				
				StringBuffer temp = new StringBuffer("");
				
				//System.out.println("rownum : " + rownum);
				//System.out.println("column count : " + columnCount);
				
				// row ������Ʈ
				Element rowElement = doc.createElement("row");
				rootElement.appendChild(rowElement);
				rowElement.setAttribute("id", String.valueOf(rownum));
				
				Format formatter = new DecimalFormat("0.##"); 
				
				for (int cellnum = 0; cellnum < columnCount; cellnum++) {
					HSSFCell cell = row.getCell(cellnum);
					Element cellElement = doc.createElement("cell");
					
					if (cell != null) {
						int cellType = cell.getCellType();
						
						/**
						 * ��¥ Į��
						 */
						if (0 == cellnum) {
							/**
							 * nobel77 : need check
							 */
							cellData = numericToDateFormat(cell.getNumericCellValue()).toString();
							cellElement.appendChild(doc.createTextNode(cellData));
						} else {
							switch (cellType) {

							case HSSFCell.CELL_TYPE_NUMERIC:
								//cellData = ""+Double.parseDouble(String.format("%.2f", cell.getNumericCellValue()));
								cellData = "" + formatter.format(cell.getNumericCellValue());
								cellElement.appendChild(doc.createTextNode(cellData));
								break;
							case HSSFCell.CELL_TYPE_STRING:
								cellData = cell.getStringCellValue();
								cellData = cellData.replaceAll("\n", "<br>");
								cellElement.appendChild(doc.createCDATASection(cellData));
								break;
							default:
								cellData = "&nbsp";
								cellElement.appendChild(doc.createCDATASection(cellData));
								break;
							}
						}
					} else {
						cellData = "&nbsp";
						cellElement.appendChild(doc.createCDATASection(cellData));
					}
					//System.out.println("data: " + cellData);
					rowElement.appendChild(cellElement);

				}
				rootElement.appendChild(rowElement);
			}
		}
		// XML ���Ϸ� ����
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();

		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new FileOutputStream(
				new File(filePath)));

		transformer.transform(source, result);
		/*
		 <?xml version="1.0" encoding="UTF-8"?>
		 <rows> 
		   <row id="1001"> 
		   <cell>100</cell> 
		   <cell>AN:apple</cell> 
		   <cell>John Grisham</cell> 
		   <cell>12.99</cell> 
		   <cell>1</cell> 
		   <cell>05/01/1998</cell> 
		   </row>
		  </rows>
		*/
		return new StringBuffer("");
	}
	/**
	 * 
	 * @param numericCellValue	(1)231.0 3~4�ڸ� ��¥ ����
	 * @return
	 */
	private StringBuffer numericToDateFormat(double numericCellValue) {

		StringBuffer date = new StringBuffer(String.valueOf(numericCellValue));
		//System.out.println("before : " + date);
		int len = date.length();

		/**
		 * delete �Լ��� �ι�° ���ڴ� 1���� ����
		 */
		StringBuffer sb = date.delete(len-2, len);
		/**
		 * 10�� �̸��� ��� 0�� �տ� �߰�
		 */
		if (3 == date.length()) {
			date.insert(0,'0');
		}
		
		date.insert(2,'/');
		//System.out.println("after : " + date);
		return date;
	}
	/**
	 * 
	 * @param fileName	tile file name
	 * @param storage	tile file path(���� ���� �����)
	 * @param content	
	 */
	private void createFile(String fileName, String storage, String content) {
		String filePath = storage + File.separator + fileName;
		try {
			File d = new File(storage);

			if (!d.isDirectory()) {
				if (!d.mkdirs()) {
				}
			}

			BufferedWriter out = new BufferedWriter(new FileWriter(filePath));

			out.write(content);
			out.close();
		} catch (IOException e) {
			System.err.println(e);
		}
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
}
