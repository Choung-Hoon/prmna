/*
 * @(#)Logger.java   1.0 2011/07/01
 */
package lee.common.log;

/**
 * Logger Interface <br>
 * Priority Level : DEBUG &lt; INFO &lt; WARN &lt; ERROR &lt; FATAL <br>
 * ex)conf 파일에서 Priority Mode 가 INFO 인 경우 DEBUG 는 기록하지 않는다.<br>
 *
 * @author  주성진
 * @version 1.0, 2011/04/01
 */
public interface Logger {
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
  public void debug(Object message);

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
  public void debug(Object message, Throwable t);

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
  public void info(Object message);

  /**
   * Log a message object with the <code>INFO</code> priority including
   * the stack trace of the {@link Throwable} <code>t</code> passed as
   * parameter.

   * <p>See {@link #info(Object)} for more detailed information.

   * @param message the message object to log.
   * @param t       the exception to log, including its stack trace.
   */
  public void info(Object message, Throwable t);

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
  public void warn(Object message);

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
  public void warn(Object message, Throwable t);

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
  public void error(Object message);

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
  public void error(Object message, Throwable t);

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
  public void fatal(Object message);

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
  public void fatal(Object message, Throwable t);

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
  public boolean isDebugEnabled();

  /**
   * Check whether this category is enabled for the info priority.
   * See also {@link #isDebugEnabled}.
   *
   * @return boolean - <code>true</code> if this category is enabled
   * for priority info, <code>false</code> otherwise.
   */
  public boolean isInfoEnabled();
}