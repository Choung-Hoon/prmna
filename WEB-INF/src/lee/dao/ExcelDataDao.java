package lee.dao;

import java.util.List;
import java.util.Map;

import lee.bean.ExcelData;
import lee.bean.ExcelTitle;
import lee.common.db.JResultSet;
import lee.common.sqlMap.SqlMapBase;

import com.ibatis.sqlmap.client.SqlMapClient;



/**
 * Code Data Access Object
 */
public class ExcelDataDao extends SqlMapBase 
{
	SqlMapClient sqlMapClient;

	public ExcelDataDao(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public void insertExcelData(ExcelData excelData) throws Exception {
		sqlMapClient.insert("lee.exceldata.insertExcelData", excelData);
	}

	public void deleteExcelDataByloadId(Map map) throws Exception {
		sqlMapClient.delete("lee.exceldata.deleteExcelDataByloadId", map);
	}
	
	/* 업종/3년 합계/양도가  추출 쿼리 */
	public List<ExcelData> getExcelDataByField(Map map) throws Exception {
		return (List<ExcelData>) sqlMapClient.queryForList("lee.exceldata.getExcelDataByField", map);
	}
	
	/* 업종 추출 쿼리 */
	public List<ExcelData> getExcelDataByF2(Map map) throws Exception {
		return (List<ExcelData>) sqlMapClient.queryForList("lee.exceldata.getExcelDataByF2", map);
	}
	
	/* 3년 합계 추출 쿼리 */
	public List<ExcelData> getExcelDataByF13(Map map) throws Exception {
		return (List<ExcelData>) sqlMapClient.queryForList("lee.exceldata.getExcelDataByF13", map);
	}
	
	/* 양도가 추출 쿼리 */
	public List<ExcelData> getExcelDataByF15(Map map) throws Exception {
		return (List<ExcelData>) sqlMapClient.queryForList("lee.exceldata.getExcelDataByF15", map);
	}
	
	public ExcelData getExcelDataByExcelId(Map map) throws Exception {
		return (ExcelData) sqlMapClient.queryForObject("lee.exceldata.getExcelDataByExcelId", map);
	}
	
	public JResultSet getAllExcelDataByJResult(Map map) throws Exception {
		return (JResultSet) sqlMapClient.queryForList("lee.exceldata.getAllExcelDataByJResult", map);
	}
	
	public List<ExcelData> getAllExcelData(Map map) throws Exception {
		return (List<ExcelData>) sqlMapClient.queryForList("lee.exceldata.getAllExcelData", map);
	}
	
	public int getExcelDataListCnt(Map map) throws Exception {
		return ((Integer)sqlMapClient.queryForObject("lee.exceldata.getExcelDataListCnt", map)).intValue();
	}		

	public List<ExcelData> getExcelDataList(Map map) throws Exception {
		return (List<ExcelData>) sqlMapClient.queryForList("lee.exceldata.getExcelDataList", map);
	}
	
	public ExcelTitle getExcelTitle(Map map) throws Exception {
		return (ExcelTitle) sqlMapClient.queryForObject("lee.exceldata.getExcelTitle", map);
	}
}
