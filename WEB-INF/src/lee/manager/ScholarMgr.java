/*
 * @(#)ScholarMgr.java
 */
package lee.manager;

import java.util.List;
import java.util.Map;

import lee.bean.Scholarship;
import lee.common.util.ExceptionUtil;
import lee.dao.ScholarshipDao;

/**
 * 장학사업, 장학사업 배정인원, 장학사업 대상자, 장학금신청, 장학금신청 가족사항, 장학금신청 대학원
 *
 * @author sjju
 * @version 1.0
 */
public class ScholarMgr extends MgrBase {
 

	private ScholarshipDao scholarshipDao;
	

	public ScholarMgr() {
		super();
		scholarshipDao = new ScholarshipDao(sqlMap);
	}
	
	//============= 장학사업 ================//
	
	public void insertScholarship(Scholarship scholarship) throws Exception {
		try {
			sqlMap.startTransaction();
			scholarshipDao.insertScholarship(scholarship);
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
	
	public void updateScholarship(Scholarship scholarship) throws Exception {
		try {
			sqlMap.startTransaction();
			scholarshipDao.updateScholarship(scholarship);
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

	public void updateScholarshipWithAccDate(Scholarship scholarship) throws Exception {
		try {
			sqlMap.startTransaction();
			scholarshipDao.updateScholarshipWithAccDate(scholarship);
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

	public void updateScholarshipReqCnt(Map map) throws Exception {
		try {
			sqlMap.startTransaction();
			scholarshipDao.updateScholarshipReqCnt(map);
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
	
	public void updateScholarshipReqCntPlus(Map map) throws Exception {
		try {
			sqlMap.startTransaction();
			scholarshipDao.updateScholarshipReqCntPlus(map);
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
	
	public void updateScholarshipReqCntMinus(Map map) throws Exception {
		try {
			sqlMap.startTransaction();
			scholarshipDao.updateScholarshipReqCntMinus(map);
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


	public void updateScholarshipAllocCnt(Map map) throws Exception {
		try {
			sqlMap.startTransaction();
			scholarshipDao.updateScholarshipAllocCnt(map);
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
	
	public Scholarship getScholarship(Map map) throws Exception {
		Scholarship result = null;
		try {
			result = scholarshipDao.getScholarship(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}
		return result;
	}

	public Scholarship getScholarshipForChk(Map map) throws Exception {
		Scholarship result = null;
		try {
			result = scholarshipDao.getScholarshipForChk(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}
		return result;
	}	

	public Integer getMaxScId() throws Exception {
		int cnt = 0;
		try {
			cnt = scholarshipDao.getMaxScId();
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}

		return cnt;
	}
	
	public Integer getScholarshipListCnt(Map map) throws Exception {
		int cnt = 0;
		try {
			cnt = scholarshipDao.getScholarshipListCnt(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}

		return cnt;
	}	
	
	public Scholarship[] getScholarshipList(Map map) throws Exception {

		List<Scholarship> arrData = null;
		Scholarship[] result = null;

		try {
			arrData = (List<Scholarship>) scholarshipDao.getScholarshipList(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}

		result = new Scholarship[arrData.size()];
		arrData.toArray(result);
		return result;
	}
	
	

}
