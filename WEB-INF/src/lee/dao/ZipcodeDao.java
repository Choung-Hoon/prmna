package lee.dao;

import java.util.List;
import java.util.Map;

import lee.bean.Zipcode;
import lee.common.sqlMap.SqlMapBase;

import com.ibatis.sqlmap.client.SqlMapClient;


/**
 * Zipcode Data Access Object
 */
public class ZipcodeDao extends SqlMapBase {
	SqlMapClient sqlMapClient;

	public ZipcodeDao(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public List<Zipcode> getZipcodeList(Map map) throws Exception {
		return (List<Zipcode>) sqlMapClient.queryForList("lee.zipcode.getZipcodeList",map);
	}
}
