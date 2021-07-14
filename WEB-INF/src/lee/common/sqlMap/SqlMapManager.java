/**
 * SqlMapManager
 * @author sjju
 * @version 1.0
 */
package lee.common.sqlMap;

import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapManager extends SqlMapClientBuilder {

  private final static SqlMapClient SQL_MAP_CLIENT_MDWEB;

  static {
    try {
      String strResourceAres = "map/sqlMapConfig_lee.xml";

      Resources.setCharset(Charset.forName("UTF-8"));
      
      Reader readerAres = Resources.getResourceAsReader(strResourceAres);
      SQL_MAP_CLIENT_MDWEB = SqlMapClientBuilder.buildSqlMapClient(readerAres);
    } catch (Exception e) {
      System.out.println(e);
      throw new RuntimeException("Description. Cause: " + e, e);
    }
  }

  public static SqlMapClient getSqlMapClientMdweb() {
    return SQL_MAP_CLIENT_MDWEB;
  }
  
}
