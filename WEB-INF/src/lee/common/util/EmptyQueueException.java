/*
 * @(#)EmptyQueueException.java   1.0 2010/01/20
 *
 * (c) Copyright JavaService.Net, 2001. All rights reserved.
 *
 * NOTICE !      You can copy or redistribute this code freely,
 * but you should not remove the information about the copyright notice
 * and the author.
 */
package lee.common.util;

/**
 * Thrown by methods in the Queue class to indicate that the queue is empty
 *
 * @author  Lee WonYoung  javaservice@hanmail.net, lwy@kr.ibm.com
 * @version 1.0 2001/02/11
 */
public class EmptyQueueException extends java.lang.RuntimeException {
  /**
   * Constructs a new EmptyQueueException with no detail message
   */
  public EmptyQueueException() {
    super();
  }
}
