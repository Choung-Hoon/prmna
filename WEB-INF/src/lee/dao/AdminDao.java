package lee.dao;

import java.util.List;
import java.util.Map;

import lee.bean.Admin;
import lee.common.sqlMap.SqlMapBase;

import com.ibatis.sqlmap.client.SqlMapClient;


/**
 * Admin Data Access Object
 */
public class AdminDao extends SqlMapBase 
{
	SqlMapClient sqlMapClient;

	public AdminDao(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public void insertAdmin(Admin admin) throws Exception {
		sqlMapClient.insert("lee.admin.insertAdmin", admin);
	}

	public void updateAdmin(Admin admin) throws Exception {
		sqlMapClient.update("lee.admin.updateAdmin", admin);
	}

	public void updatePhoneAdmin(Map map) throws Exception {
		sqlMapClient.update("lee.admin.updatePhoneAdmin", map);
	}

	public void updateEmailAdmin(Map map) throws Exception {
		sqlMapClient.update("lee.admin.updateEmailAdmin", map);
	}	
	
	public void updatePwdAdmin(Map map) throws Exception {
		sqlMapClient.update("lee.admin.updatePwdAdmin", map);
	}	
		
	public void updateSchoolPwdAdmin(Map map) throws Exception {
		sqlMapClient.update("lee.admin.updateSchoolPwdAdmin", map);
	}	
	
	public void updateIpListAdmin(Map map) throws Exception {
		sqlMapClient.update("lee.admin.updateIpListAdmin", map);
	}		
	
	public void updateAccessDateAdmin(Map map) throws Exception {
		sqlMapClient.update("lee.admin.updateAccessDateAdmin", map);
	}
	
	public void updateValidDate(Map map) throws Exception {
		sqlMapClient.update("lee.admin.updateValidDate", map);
	}	
	
	public void deleteAdmin(Map map) throws Exception {
		sqlMapClient.delete("lee.admin.deleteAdmin", map);
	}

	public Admin getAdmin(Map map) throws Exception {
		return (Admin) sqlMapClient.queryForObject("lee.admin.getAdmin", map);
	}
	
	public int getAdminListCnt(Map map) throws Exception {
		return ((Integer)sqlMapClient.queryForObject("lee.admin.getAdminListCnt", map)).intValue();
	}		

	public List<Admin> getAdminList(Map map) throws Exception {
		return (List<Admin>) sqlMapClient.queryForList("lee.admin.getAdminList", map);
	}
	
	public Integer getValidMemberMainCnt(Map map) throws Exception{
        return (Integer)sqlMapClient.queryForObject("lee.admin.getValidMemberMainCnt", map);
    }
    
	public Integer getInvalidMemberMainCnt(Map map) throws Exception{
        return (Integer)sqlMapClient.queryForObject("lee.admin.getInvalidMemberMainCnt", map);
    }
}
