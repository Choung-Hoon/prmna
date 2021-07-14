/*
 * @(#)SsfException.java
 *
 */
package lee;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 기본 Exception
 *
 * @author
 * @version 1.0
 */
public class SsfException extends Exception {

  private static final long serialVersionUID = 1L;

  private Throwable t = null;

  public SsfException() {
    super();
  }

  public SsfException( String msg ) {
    super( msg );
  }

  public SsfException( Throwable t ) {
    super();
    this.t = t;
  }

  public SsfException( String msg, Throwable t ) {
    super( msg );
    this.t = t;
  }

  public Throwable getChainedException() {
    return this.t;
  }

  public void printStackTrace() {
    super.printStackTrace();
    if( this.t != null ) this.t.printStackTrace();
  }

  public void printStackTrace( PrintStream ps ) {
    super.printStackTrace( ps );
    if (this.t != null) this.t.printStackTrace( ps );
  }

  public void printStackTrace( PrintWriter pw ) {
    super.printStackTrace( pw );
    if (this.t != null) this.t.printStackTrace( pw );
  }
}
