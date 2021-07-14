/*
 * @(#)MgrException.java
 */
package lee.manager;

import lee.SsfException;

/**
 * 비즈니스 로직에서 발생한 Exception을 정의한다.
 *
 * @author sjju
 * @version 1.0
 */
public class MgrException extends SsfException {

  private static final long serialVersionUID = 1L;

  private String errorCode = "";

  /**
   * Constructor for MgrException.
   */
  public MgrException() {
    super();
  }

  /**
   * Constructor for MgrException.
   * @param msg
   */
  public MgrException( String msg ) {
    super( msg );
  }

  /**
   * @param msg
   * @param errorCode
   */
  public MgrException( String msg, String errorCode ) {
    super( msg );
    this.errorCode = errorCode;
  }

  /**
   * Constructor for MgrException.
   * @param t
   */
  public MgrException( Throwable t ) {
    super( t );
  }

  /**
   * Constructor for MgrException.
   * @param msg
   * @param t
   */
  public MgrException( String msg, Throwable t ) {
    super( msg, t );
  }

  /**
   * Constructor for MgrException.
   * @param msg
   * @param errorCode
   * @param t
   */
  public MgrException( String msg, String errorCode, Throwable t ) {
    super( msg, t );
    this.errorCode = errorCode;
  }

  /**
   * @param msg
   * @param t
   * @param errorCode
   */
  public MgrException( String msg, Throwable t, String errorCode ) {
    super( msg, t );
    this.errorCode = errorCode;
  }

  /**
   * @return
   */
  public String getErrorCode() {
    return this.errorCode;
  }

}
