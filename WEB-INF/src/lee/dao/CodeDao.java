package lee.dao;

import java.util.List;
import java.util.Map;

import lee.bean.Code;
import lee.common.sqlMap.SqlMapBase;

import com.ibatis.sqlmap.client.SqlMapClient;



/**
 * Code Data Access Object
 */
public class CodeDao extends SqlMapBase 
{
	SqlMapClient sqlMapClient;

	public CodeDao(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public void insertCode(Code code) throws Exception {
		sqlMapClient.insert("lee.code.insertCode", code);
	}

	public void updateCode(Code code) throws Exception {
		sqlMapClient.update("lee.code.updateCode", code);
	}

	public void deleteCode(Map map) throws Exception {
		sqlMapClient.delete("lee.code.deleteCode", map);
	}

	public Code getCode(Map map) throws Exception {
		return (Code) sqlMapClient.queryForObject("lee.code.getCode", map);
	}
	
	public int getCodeListCnt(Map map) throws Exception {
		return ((Integer)sqlMapClient.queryForObject("lee.code.getCodeListCnt", map)).intValue();
	}		

	public List<Code> getCodeList(Map map) throws Exception {
		return (List<Code>) sqlMapClient.queryForList("lee.code.getCodeList", map);
	}
	
	public List<Code> getAllCodeList(Map map) throws Exception {
		return (List<Code>) sqlMapClient.queryForList("lee.code.getAllCodeList", map);
	}	
}
