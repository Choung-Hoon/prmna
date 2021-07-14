package lee.dao;

import java.util.List;
import java.util.Map;

import lee.bean.Scholarship;
import lee.common.sqlMap.SqlMapBase;

import com.ibatis.sqlmap.client.SqlMapClient;


/**
 * Scholarship Data Access Object
 */
public class ScholarshipDao extends SqlMapBase 
{
	SqlMapClient sqlMapClient;

	public ScholarshipDao(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public void insertScholarship(Scholarship scholarship) throws Exception {
		sqlMapClient.insert("lee.scholarship.insertScholarship", scholarship);
	}

	public void updateScholarship(Scholarship scholarship) throws Exception {
		sqlMapClient.update("lee.scholarship.updateScholarship", scholarship);
	}

	public void updateScholarshipWithAccDate(Scholarship scholarship) throws Exception {
		sqlMapClient.update("lee.scholarship.updateScholarshipWithAccDate", scholarship);
	}
	
	public void updateScholarshipReqCnt(Map map) throws Exception {
		sqlMapClient.update("lee.scholarship.updateScholarshipReqCnt", map);
	}
	
	public void updateScholarshipReqCntPlus(Map map) throws Exception {
		sqlMapClient.update("lee.scholarship.updateScholarshipReqCntPlus", map);
	}	

	public void updateScholarshipReqCntMinus(Map map) throws Exception {
		sqlMapClient.update("lee.scholarship.updateScholarshipReqCntMinus", map);
	}	
	
	public void updateScholarshipAllocCnt(Map map) throws Exception {
		sqlMapClient.update("lee.scholarship.updateScholarshipAllocCnt", map);
	}
	
	public void deleteScholarship(Map map) throws Exception {
		sqlMapClient.delete("lee.scholarship.deleteScholarship", map);
	}

	public Scholarship getScholarship(Map map) throws Exception {
		return (Scholarship) sqlMapClient.queryForObject("lee.scholarship.getScholarship", map);
	}
	
	public Scholarship getScholarshipForChk(Map map) throws Exception {
		return (Scholarship) sqlMapClient.queryForObject("lee.scholarship.getScholarshipForChk", map);
	}
		
	public int getMaxScId() throws Exception {
		return ((Integer)sqlMapClient.queryForObject("lee.scholarship.getMaxScId", null)).intValue();
	}
	
	public int getScholarshipListCnt(Map map) throws Exception {
		return ((Integer)sqlMapClient.queryForObject("lee.scholarship.getScholarshipListCnt", map)).intValue();
	}		
	
	public List<Scholarship> getScholarshipList(Map map) throws Exception {
		return (List<Scholarship>) sqlMapClient.queryForList("lee.scholarship.getScholarshipList", map);
	}
}
