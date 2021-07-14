/*
 * @(#)CommonMgr.java
 */
package lee.manager;

import java.util.List;
import java.util.Map;

import lee.bean.ExcelData;
import lee.bean.ExcelTitle;
import lee.common.db.JResultSet;
import lee.common.util.ExceptionUtil;
import lee.dao.ExcelDataDao;

/**
 * 로드된 엑셀 데이터
 *
 * @author sjju
 * @version 1.0
 */
public class ExcelDataMgr extends MgrBase {


	private ExcelDataDao excelDataDao;
	
	public ExcelDataMgr() {
		super();
		excelDataDao = new ExcelDataDao(sqlMap);
	}
	
	public void insertExcelData(ExcelData excelData) throws Exception {
		try {
			sqlMap.startTransaction();
			excelDataDao.insertExcelData(excelData);
			sqlMap.commitTransaction();
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		} finally {
			try {
				sqlMap.endTransaction();
			} catch (Exception e) {
				throw new MgrException(e);
			}
		}
	}

	public void deleteExcelDataByloadId(Map map) throws Exception {
		try {
			sqlMap.startTransaction();
			excelDataDao.deleteExcelDataByloadId(map);
			sqlMap.commitTransaction();
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		} finally {
			try {
				sqlMap.endTransaction();
			} catch (Exception e) {
				throw new MgrException(e);
			}
		}
	}

	public ExcelData getExcelDataByExcelId(Map map) throws Exception {
		ExcelData result = null;
		try {
			result = excelDataDao.getExcelDataByExcelId(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}
		return result;
	}

	public Integer getExcelDataListCnt(Map map) throws Exception {
		int cnt = 0;
		try {
			cnt = excelDataDao.getExcelDataListCnt(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}

		return cnt;
	}	
	
	public ExcelData[] getExcelDataList(Map map) throws Exception {

		List<ExcelData> arrData = null;
		ExcelData[] result = null;

		try {
			arrData = (List<ExcelData>) excelDataDao.getExcelDataList(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}

		result = new ExcelData[arrData.size()];
		arrData.toArray(result);
		return result;
	}	
	
	public JResultSet getAllExcelDataByJResult(Map map) throws Exception {

		JResultSet resultJrs = null;

		try {
			resultJrs = (JResultSet) excelDataDao.getAllExcelDataByJResult(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}
		
		return resultJrs;
	}	
	
	public ExcelData[] getAllExcelData(Map map) throws Exception {

		List<ExcelData> arrData = null;
		ExcelData[] result = null;

		try {
			arrData = (List<ExcelData>) excelDataDao.getAllExcelData(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}

		result = new ExcelData[arrData.size()];
		arrData.toArray(result);
		return result;
	}	
	
	public ExcelData[] getExcelDataByField(Map map) throws Exception {

		List<ExcelData> arrData = null;
		ExcelData[] result = null;

		try {
			arrData = (List<ExcelData>) excelDataDao.getExcelDataByField(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}

		result = new ExcelData[arrData.size()];
		arrData.toArray(result);
		return result;
	}
	
	public ExcelData[] getExcelDataByF2(Map map) throws Exception {

		List<ExcelData> arrData = null;
		ExcelData[] result = null;

		try {
			arrData = (List<ExcelData>) excelDataDao.getExcelDataByF2(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}

		result = new ExcelData[arrData.size()];
		arrData.toArray(result);
		return result;
	}
	
	public ExcelData[] getExcelDataByF13(Map map) throws Exception {

		List<ExcelData> arrData = null;
		ExcelData[] result = null;

		try {
			arrData = (List<ExcelData>) excelDataDao.getExcelDataByF13(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}

		result = new ExcelData[arrData.size()];
		arrData.toArray(result);
		return result;
	}
	
	public ExcelData[] getExcelDataByF15(Map map) throws Exception {

		List<ExcelData> arrData = null;
		ExcelData[] result = null;

		try {
			arrData = (List<ExcelData>) excelDataDao.getExcelDataByF15(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}

		result = new ExcelData[arrData.size()];
		arrData.toArray(result);
		return result;
	}
	
	public ExcelTitle getExcelTitle(Map map) throws Exception {
		ExcelTitle result = null;
		try {
			result = excelDataDao.getExcelTitle(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}
		return result;
	}

}
