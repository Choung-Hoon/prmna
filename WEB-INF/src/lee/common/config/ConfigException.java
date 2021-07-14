/*
 * @(#)ConfigException.java   1.0 2011/07/01
 */
package lee.common.config;

import lee.SsfException;

/**
 * 환경설정 파일 파싱, 설정시 발생하는 예외
 *
 * @author  sjju
 * @version 1.0, 2011/07/01lsrk
 */
public class ConfigException extends SsfException {
  public ConfigException() {}

  public ConfigException(String s) {
    super(s);
  }

  public ConfigException(Exception e) {
    super(e);
  }

  public ConfigException(String s, Exception e) {
    super(s, e);
  }
}