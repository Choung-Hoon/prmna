/*
 * @(#)CommonMgr.java
 */
package lee.manager;

import java.util.List;
import java.util.Map;

import lee.bean.*;
import lee.common.util.ExceptionUtil;
import lee.dao.*;

/**
 * 우편번호, 공통코드
 *
 * @author sjju
 * @version 1.0
 */
public class CommonMgr extends MgrBase {


	private ZipcodeDao zipcodeDao;
	private CodeDao codeDao;
	
	public CommonMgr() {
		super();
		zipcodeDao = new ZipcodeDao(sqlMap);
		codeDao = new CodeDao(sqlMap);
	}
	
	//============= 공통 코드 ================//
	
	public void insertCode(Code code) throws Exception {
		try {
			sqlMap.startTransaction();
			codeDao.insertCode(code);
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

	public void updateCode(Code code) throws Exception {
		try {
			sqlMap.startTransaction();
			codeDao.updateCode(code);
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

	public void deleteCode(Map map) throws Exception {
		try {
			sqlMap.startTransaction();
			codeDao.deleteCode(map);
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

	public Code getCode(Map map) throws Exception {
		Code result = null;
		try {
			result = codeDao.getCode(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}
		return result;
	}

	public Integer getCodeListCnt(Map map) throws Exception {
		int cnt = 0;
		try {
			cnt = codeDao.getCodeListCnt(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}

		return cnt;
	}	
	
	public Code[] getCodeList(Map map) throws Exception {

		List<Code> arrData = null;
		Code[] result = null;

		try {
			arrData = (List<Code>) codeDao.getCodeList(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}

		result = new Code[arrData.size()];
		arrData.toArray(result);
		return result;
	}		
	
	public Code[] getAllCodeList(Map map) throws Exception {

		List<Code> arrData = null;
		Code[] result = null;

		try {
			arrData = (List<Code>) codeDao.getAllCodeList(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}

		result = new Code[arrData.size()];
		arrData.toArray(result);
		return result;
	}	

	public Zipcode[] getZipCodeList(Map map) throws Exception {

        List<Zipcode> arrData = null;
        Zipcode[] result = null;

        try {
            arrData = (List<Zipcode>) zipcodeDao.getZipcodeList(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }

        result = new Zipcode[arrData.size()];
        arrData.toArray(result);
        return result;
    }       
	
}
