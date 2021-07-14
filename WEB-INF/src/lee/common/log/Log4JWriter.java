/*
 * @(#)Log4JWriter.java   1.0 2011/07/01
 */
package lee.common.log;

import java.io.*;

import org.apache.log4j.xml.DOMConfigurator;
import lee.common.config.ConfigManager;

/**
 * org.apache.log4j.Logger 의 wrapper class. 로그를 log4j 패키지를 사용하여 남긴다.<br>
 * Priority Level : DEBUG &lt; INFO &lt; WARN &lt; ERROR &lt; FATAL <br>
 * ex)conf 파일에서 Priority Mode 가 INFO 인 경우 DEBUG 는 기록하지 않는다.<br>
 * conf 설정 및 자세한 사항은 log4j 설명서를 참조.<br>
 * (see <a href="http://jakarta.apache.org/log4j/docs/index.html">Jakarta Log4j Project</a>)
 *
 * @author  sjju
 * @version 1.0, 2011/07/01
 */
public class Log4JWriter implements lee.common.log.Logger {
  private org.apache.log4j.Logger cat;
  private static boolean isConfigured = false;
  //private static final String LOG4J_CONF = "log4j.xml";
  private static final String LOG4J_CONF = "log4j.properties";
  
  /**
   * Shorthand for <code>LogWriter(clazz.getName())</code>.
   *
   * @param clazz The name of <code>clazz</code> will be used as the
   * name of the category to retrieve.
   */
  public Log4JWriter(Class clazz) {
    super();

    this.cat = org.apache.log4j.Logger.getLogger(clazz);

    if (!isConfigured) {
      synchronized(Log4JWriter.class) {
        // perform double-check to avoid race condition
        if (!isConfigured) {
          File configFile = null;
          try {
            configFile = new File(ConfigManager.getInstance().getConfigBase() + File.separator + LOG4J_CONF);
          } catch(Exception e) {}

          if( configFile.exists() ) {
            DOMConfigurator.configure(configFile.getAbsolutePath());
          } else {
            System.out.println( configFile.getAbsolutePath() +
                                " 파일이 존재하지 않아 기본설정을 사용합니다." );
            DOMConfigurator.configure(Log4JWriter.class.getResource(LOG4J_CONF));
          }
          isConfigured = true;
        }
      }
    } // end of if
  }

  /**
   * Retrieve a category with named as the <code>name</code>
   * parameter. If the named category already exists, then the
   * existing instance will be reutrned. Otherwise, a new instance is
   * created.
   *
   * By default, categories do not have a set priority but inherit
   * it from the hierarchy. This is one of the central features of log4j.
   *
   * @param name The name of the category to retrieve.
  */
  public Log4JWriter(String  name) {
    super();

    this.cat = org.apache.log4j.Logger.getLogger(name);

    if (!isConfigured) {
      synchronized(Log4JWriter.class) {
        // perform double-check to avoid race condition
        if (!isConfigured) {
          File configFile = null;
          try {
            configFile = new File(ConfigManager.getInstance().getConfigBase() + File.separator + LOG4J_CONF);
          } catch(Exception e) {}

          if( configFile.exists() ) {
            DOMConfigurator.configure(configFile.getAbsolutePath());
          } else {
            System.out.println( configFile.getAbsolutePath() +
                                " 파일이 존재하지 않아 기본설정을 사용합니다." );
            DOMConfigurator.configure(Log4JWriter.class.getResource(LOG4J_CONF));
          }
          isConfigured = true;
        }
      }
    } // end of if
  }

  /**
   * Log a message object with the <code>DEBUG</code> priority.
   *
   * <p>This method first checks if this category is <code>DEBUG</code>
   * enabled by comparing the priority of this category with the
   * <code>DEBUG</code> priority. If this category is
   * <code>DEBUG</code> enabled, then it converts the message object
   * (passed as parameter) to a string by invoking the appropriate
   * <code>ObjectRenderer</code>. It then proceeds to call all the
   * registered appenders in this category and also higher in the
   * hierarchy depending on the value of the additivity flag.
   *
   * <p><b>WARNING</b> Note that passing a {@link Throwable} to this
   * method will print the name of the <code>Throwable</code> but no
   * stack trace. To print a stack trace use the {@link #debug(Object,
   * Throwable)} form instead.
   *
   * @param message the message object to log.
   */
  public void debug(Object message) {
    this.cat.debug(message);
  }

  /**
   * Log a message object with the <code>DEBUG</code> priority including
   * the stack trace of the {@link Throwable} <code>t</code> passed as
   * parameter.
   *
   * <p>See {@link #debug(Object)} form for more detailed information.
   *
   * @param message the message object to log.
   * @param t       the exception to log, including its stack trace.
   */
  public void debug(Object message, Throwable t) {
    this.cat.debug(message, t);
  }

  /**
   * Log a message object with the <code>INFO</code> priority.
   *
   * <p>This method first checks if this category is <code>INFO</code>
   * enabled by comparing the priority of this category with
   * <code>INFO</code> priority. If the category is <code>INFO</code>
   * enabled, then it converts the message object passed as parameter
   * to a string by invoking the appropriate <code>ObjectRenderer</code>. It
   * proceeds to call all the registered appenders in this category and
   * also higher in the hierarchy depending on the value of the
   * additivity flag.
   *
   * <p><b>WARNING</b> Note that passing a {@link Throwable} to this
   * method will print the name of the Throwable but no stack trace. To
   * print a stack trace use the {@link #info(Object, Throwable)} form
   * instead.
   *
   * @param message the message object to log
   */
  public void info(Object message) {
    this.cat.info(message);
  }

  /**
   * Log a message object with the <code>INFO</code> priority including
   * the stack trace of the {@link Throwable} <code>t</code> passed as
   * parameter.

   * <p>See {@link #info(Object)} for more detailed information.

   * @param message the message object to log.
   * @param t       the exception to log, including its stack trace.
   */
  public void info(Object message, Throwable t) {
    this.cat.info(message, t);
  }

  /**
   * Log a message object with the <code>WARN</code> priority.
   *
   * <p>This method first checks if this category is <code>WARN</code>
   * enabled by comparing the priority of this category with
   * <code>WARN</code> priority. If the category is <code>WARN</code>
   * enabled, then it converts the message object passed as parameter
   * to a string by invoking the appropriate <code>ObjectRenderer</code>. It
   * proceeds to call all the registered appenders in this category and
   * also higher in the hieararchy depending on the value of the
   * additivity flag.
   *
   * <p><b>WARNING</b> Note that passing a {@link Throwable} to this
   * method will print the name of the Throwable but no stack trace. To
   * print a stack trace use the {@link #warn(Object, Throwable)} form
   * instead.
   *
   * @param message the message object to log.
   */
  public void warn(Object message) {
    this.cat.warn(message);
  }

  /**
   * Log a message with the <code>WARN</code> priority including the
   * stack trace of the {@link Throwable} <code>t</code> passed as
   * parameter.
   *
   * <p>See {@link #warn(Object)} for more detailed information.
   *
   * @param message the message object to log.
   * @param t       the exception to log, including its stack trace.
   */
  public void warn(Object message, Throwable t) {
    this.cat.warn(message, t);
  }

  /**
   * Log a message object with the <code>ERROR</code> priority.
   *
   * <p>This method first checks if this category is <code>ERROR</code>
   * enabled by comparing the priority of this category with
   * <code>ERROR</code> priority. If this category is
   * <code>ERROR</code> enabled, then it converts the message object
   * passed as parameter to a string by invoking the appropriate <code>
   * ObjectRenderer</code>. It proceeds to call all the registered appenders
   * in this category and also higher in the hierarchy depending on
   * the value of the additivity flag.
   *
   * <p><b>WARNING</b> Note that passing a {@link Throwable} to this
   * method will print the name of the <code>Throwable</code> but no
   * stack trace. To print a stack trace use the {@link #error(Object,
   * Throwable)} form instead.
   *
   * @param message the message object to log
   */
  public void error(Object message) {
    this.cat.error(message);
  }

  /**
   * Log a message object with the <code>ERROR</code> priority including
   * the stack trace of the {@link Throwable} <code>t</code> passed as
   * parameter.
   *
   * <p>See {@link #error(Object)} form for more detailed information.
   *
   * @param message the message object to log.
   * @param t the exception to log, including its stack trace.
   */
  public void error(Object message, Throwable t) {
    this.cat.error(message, t);
  }

  /**
   * Log a message object with the <code>FATAL</code> priority.
   *
   * <p>This method first checks if this category is <code>FATAL</code>
   * enabled by comparing the priority of this category with
   * <code>FATAL</code> priority. If the category is <code>FATAL</code>
   * enabled, then it converts the message object passed as parameter
   * to a string by invoking the appropriate <code>ObjectRenderer</code>. It
   * proceeds to call all the registered appenders in this category and
   * also higher in the hierarchy depending on the value of the
   * additivity flag.
   *
   * <p><b>WARNING</b> Note that passing a {@link Throwable} to this
   * method will print the name of the Throwable but no stack trace. To
   * print a stack trace use the {@link #fatal(Object, Throwable)} form
   * instead.
   *
   * @param message the message object to log
   */
  public void fatal(Object message) {
    this.cat.fatal(message);
  }

  /**
   * Log a message object with the <code>FATAL</code> priority including
   * the stack trace of the {@link Throwable} <code>t</code> passed as
   * parameter.
   *
   * <p>See {@link #fatal(Object)} for more detailed information.
   *
   * @param message the message object to log.
   * @param t       the exception to log, including its stack trace.
   */
  public void fatal(Object message, Throwable t) {
    this.cat.fatal(message, t);
  }

  /**
   *  Check whether this category is enabled for the <code>DEBUG</code>
   *  priority.
   *
   *  <p> This function is intended to lessen the computational cost of
   *  disabled log debug statements.
   *
   *  <p> For some <code>cat</code> Category object, when you write,
   *  <pre>
   *      cat.debug("This is entry number: " + i );
   *  </pre>
   *
   *  <p>You incur the cost constructing the message, concatenatiion in
   *  this case, regardless of whether the message is logged or not.
   *
   *  <p>If you are worried about speed, then you should write
   *  <pre>
   * 	 if(cat.isDebugEnabled()) {
   * 	   cat.debug("This is entry number: " + i );
   * 	 }
   *  </pre>
   *
   *  <p>This way you will not incur the cost of parameter
   *  construction if debugging is disabled for <code>cat</code>. On
   *  the other hand, if the <code>cat</code> is debug enabled, you
   *  will incur the cost of evaluating whether the category is debug
   *  enabled twice. Once in <code>isDebugEnabled</code> and once in
   *  the <code>debug</code>.  This is an insignificant overhead
   *  since evaluating a category takes about 1%% of the time it
   *  takes to actually log.
   *
   *  @return boolean - <code>true</code> if this category is debug
   *  enabled, <code>false</code> otherwise.
   */
  public boolean isDebugEnabled() {
    return this.cat.isDebugEnabled();
  }

  /**
   * Check whether this category is enabled for the info priority.
   * See also {@link #isDebugEnabled}.
   *
   * @return boolean - <code>true</code> if this category is enabled
   * for priority info, <code>false</code> otherwise.
   */
  public boolean isInfoEnabled() {
    return this.cat.isInfoEnabled();
  }
}
