/**
 * SqlMapBase
 * @author sjju
 * @version 1.0
 */
package lee.common.sqlMap;

import com.ibatis.sqlmap.client.SqlMapClient;

public class SqlMapBase {
  public SqlMapClient sqlMap;

  public SqlMapBase() {
    this.sqlMap = SqlMapManager.getSqlMapClientMdweb();
  }
}