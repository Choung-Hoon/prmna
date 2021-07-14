/*
 * @(#)UserSession.java
 *
 */
package lee.session;

import lee.bean.Admin;

/**
 * User Sessoin Bean
 * @author sjju
 * @version 1.0
 */
public class UserSession extends Admin {

  private static final long serialVersionUID = 1L;

  private String sessionId = null;
  private String loginIp = null;
  private String loginDate = null;
  private long timestamp;

  /**
   * @return String
   */
  public String getLoginDate() {
    return loginDate;
  }

  /**
   * @return long
   */
  public long getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the loginDate.
   * @param loginDate The loginDate to set
   */
  public void setLoginDate(String loginDate) {
    this.loginDate = loginDate;
  }

  /**
   * Sets the timestamp.
   * @param timestamp The timestamp to set
   */
  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * @return String
   */
  public String getSessionId() {
    return sessionId;
  }

  /**
   * Sets the sessionId.
   * @param sessionId The sessionId to set
   */
  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  /**
   * @return String
   */
  public String getLoginIp() {
    return loginIp;
  }

  /**
   * Sets the loginIp.
   * @param loginIp The loginIp to set
   */
  public void setLoginIp(String loginIp) {
    this.loginIp = loginIp;
  }

}
