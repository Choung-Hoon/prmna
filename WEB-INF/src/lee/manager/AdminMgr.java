package lee.manager;

import java.util.List;
import java.util.Map;

import lee.bean.Admin;
import lee.common.util.ExceptionUtil;
import lee.dao.AdminDao;

/**
 * Admin Manager : TB_ADMIN
 * 
 * @author mhlee
 * @version 1.0
 */
public class AdminMgr extends MgrBase {

	private AdminDao adminDao;

	public AdminMgr() {
		super();
		adminDao = new AdminDao(sqlMap);
	}
	
	public void insertAdmin(Admin admin) throws Exception {
		try {
			sqlMap.startTransaction();
			adminDao.insertAdmin(admin);
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

	public void updateAdmin(Admin admin) throws Exception {
		try {
			sqlMap.startTransaction();
			adminDao.updateAdmin(admin);
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
	
	public void updateAccessDateAdmin(Map map) throws Exception {
		try {
			sqlMap.startTransaction();
			adminDao.updateAccessDateAdmin(map);
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
	
	public void updatePhoneAdmin(Map map) throws Exception {
		try {
			sqlMap.startTransaction();
			adminDao.updatePhoneAdmin(map);
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
	
	public void updateEmailAdmin(Map map) throws Exception {
		try {
			sqlMap.startTransaction();
			adminDao.updateEmailAdmin(map);
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
	
	public void updatePwdAdmin(Map map) throws Exception {
		try {
			sqlMap.startTransaction();
			adminDao.updatePwdAdmin(map);
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
	
	public void updateSchoolPwdAdmin(Map map) throws Exception {
		try {
			sqlMap.startTransaction();
			adminDao.updateSchoolPwdAdmin(map);
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
	
	
	public void updateIpListAdmin(Map map) throws Exception {
		try {
			sqlMap.startTransaction();
			adminDao.updateIpListAdmin(map);
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
	
	public void updateValidDate(Map map) throws Exception {
		try {
			sqlMap.startTransaction();
			adminDao.updateValidDate(map);
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

	public void deleteAdmin(Map map) throws Exception {
		try {
			sqlMap.startTransaction();
			adminDao.deleteAdmin(map);
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

	public Admin getAdmin(Map map) throws Exception {
		Admin result = null;
		try {
			result = adminDao.getAdmin(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}
		return result;
	}

	public Integer getAdminListCnt(Map map) throws Exception {
		int cnt = 0;
		try {
			cnt = adminDao.getAdminListCnt(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}

		return cnt;
	}	
	
	public Admin[] getAdminList(Map map) throws Exception {

		List<Admin> arrData = null;
		Admin[] result = null;

		try {
			arrData = (List<Admin>) adminDao.getAdminList(map);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			throw new MgrException(e);
		}

		result = new Admin[arrData.size()];
		arrData.toArray(result);
		return result;
	}	
	
	public int getValidMemberMainCnt(Map map) throws Exception {
        Integer cnt = 0;
        try {
            cnt = adminDao.getValidMemberMainCnt(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        return cnt.intValue();
    }
    
    public int getInvalidMemberMainCnt(Map map) throws Exception {
        Integer cnt = 0;
        try {
            cnt = adminDao.getInvalidMemberMainCnt(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        return cnt.intValue();
    }
	
		
}
