/*
 * @(#)LoggerFactory.java   1.0 2011/07/01
 */
package lee.common.log;

/**
 * 생성할 Logger instance를 결정하여 생성, 반환하는 Logger Factory
 *
 * @author  sjju
 * @version 1.0, 2011/07/01
 */
public class LoggerFactory {
  /**
   * clazz 카테고리에 해당하는 Logger instance를 반환한다.
   *
   * @param   clazz   클래스명
   * @return  Logger  Logger instance
   */
  public static Logger getLogger(Class clazz) {
    return new Log4JWriter(clazz);
  }

  /**
   * name 카테고리에 해당하는 Logger instance를 반환한다.
   *
   * @param   name    카테고리이름
   * @return  Logger  Logger instance
   */
  public static Logger getLogger(String name ) {
    return new Log4JWriter(name);
  }
}