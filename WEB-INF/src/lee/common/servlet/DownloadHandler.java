/*
 * @(#)DownloadHandler.java
 */
package lee.common.servlet;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sjju
 * @version 1.0, 2011/07/01
 */
public abstract class DownloadHandler {

  public abstract String getHandlerId();

  public abstract void init() throws Exception;

  public abstract void handle( HttpServletRequest req, HttpServletResponse res )
  throws Exception;

  public abstract void destroy() throws Exception;

  protected void logRequestParameters( HttpServletRequest req ) {

    System.out.println( "Parameters in Handler : " );

    Enumeration en = req.getParameterNames();
    String key = null;
    while( en.hasMoreElements() ) {
      key = en.nextElement().toString();
      System.out.println( key + " : " + req.getParameter( key ) );
    }
  }

}
