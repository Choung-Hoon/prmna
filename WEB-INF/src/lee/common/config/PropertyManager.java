/*
 * @(#)PropertyManager.java   1.0 2011/07/01
 */
package lee.common.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;

import lee.common.util.StringUtil;

/**
 * Property 파일의 Configurator <br>
 *
 * <pre>
 * 예) config.properties (name=value)
 * config.base.logConf=log.conf
 * config.base.database.connection.name=default,laputa,conan
 * config.base.database.connection.name.default.db=oracle
 * config.base.database.connection.name.laputa.db=unisql
 * config.base.database.connection.name.conan.db=mssql
 *
 * 위와 같은 설정에서
 *
 * getString("config.base.logConf") => log.conf
 * getString("config.base.database.connection.name") => default,laputa,conan
 * getStringArray("config.base.database.connection.name") => delimiter를 , 로
 * 하여 String [] = {"default", "laputa", "conan"} 로 반환
 * </pre>
 *
 * @author  sjju
 * @version 1.0, 2011/07/01
 */
public class PropertyManager extends Config {
  private static PropertyManager propMgr;
  private Properties properties;

  /**
   * PropertyConfigurator 생성자
   *
   * @exception      ConfigException
   */
  private PropertyManager() throws ConfigException {

    this.properties = new Properties();
    
    try {
      this.properties.load(PropertyManager.class.getResourceAsStream("/lee.properties"));
    } catch(FileNotFoundException fe) {
      throw new ConfigException(fe.getMessage(), fe);
    } catch(IOException ie) {
      throw new ConfigException(ie.getMessage(), ie);
    }
  }

  /**
   * single instance
   *
   * @return    PropertyManager        PropertyManager instance
   * @exception ConfigException  에러 발생시
   */
  public static PropertyManager getInstance()
         throws ConfigException {

    if(propMgr == null) {
      synchronized(PropertyManager.class) {
        // perform double-check to avoid race condition
        if(propMgr == null) {
          propMgr = new PropertyManager();
        }
      }
    }
    return propMgr;
  }

  /**
   * key 에 해당되는 값을 String 로 return
   *
   * @param   key    Property의 key
   * @return  String
   */
  public String getString(String key) {
    //String value = StringUtil.toKor( this.properties.getProperty(key) );
    String value = StringUtil.toUTF8( this.properties.getProperty(key) );
    return ( value != null ) ? value.trim() : value;
  }

  /**
   * key 에 해당되는 값을 String Array 로 return. delimiter 는 ,
   *
   * @param key
	 *            Property의 key
	 * @return String[]
	 */
	public String[] getStringArray(String key) {
		StringTokenizer st = new StringTokenizer(this.getString(key), ",");
		ArrayList string_list = new ArrayList();
		while (st.hasMoreTokens()) {
			string_list.add(st.nextToken().trim());
		}
		String[] string_array = new String[string_list.size()];
		string_list.toArray(string_array);

		return string_array;
	}

	/**
	 * Properties 설정파일의 {@link java.util.Properties}를 반환
	 * 
	 * @return Properties
	 */
	public Properties getProperties() {
		return this.properties;
	}
}
