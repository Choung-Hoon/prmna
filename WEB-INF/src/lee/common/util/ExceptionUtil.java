/*
 * @(#)ExceptionUtil.java   1.0 2011/07/01
 */
package lee.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
  public static String getStackTrace(Throwable throwable) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw, true);
    throwable.printStackTrace(pw);

    return sw.getBuffer().toString();
  }
}
