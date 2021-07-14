/*
 * @(#)Config.java   1.0 2011/07/01
 */
package lee.common.config;

/**
 * configuration file로 부터 Property 를 추출.
 *
 * @author  주성진
 * @version 1.0, 2011/04/01
 */
public abstract class Config {

  /**
   * key 에 해당되는 값을 String 로 return
   *
   * @param   key    Property의 key
   * @return  String
   */
  abstract public String getString(String key);

  /**
   * key 에 해당되는 값을 String Array 로 return
   *
   * @param   key      Property의 key
   * @return  String[]
   */
  abstract public String [] getStringArray(String key);

  /**
   * key 에 해당되는 값을 int 로 return
   *
   * @param   key      Property의 key
   * @return  int
   */
  public int getInt(String key) {
    return Integer.parseInt(getString(key));
  }

  /**
   * key 에 해당되는 값을 long 로 return
   *
   * @param   key      Property의 key
   * @return  long
   */
  public long getLong(String key) {
    return Long.parseLong(getString(key));
  }

  /**
   * key 에 해당되는 값을 float 로 return
   *
   * @param   key      Property의 key
   * @return  float
   */
  public float getFloat(String key) {
    return Float.parseFloat(getString(key));
  }

  /**
   * key 에 해당되는 값을 boolean 로 return
   *
   * @param   key      Property의 key
   * @return  boolean
   */
  public boolean getBoolean(String key) {
    return Boolean.valueOf( getString(key) ).booleanValue();
  }
}
