/*
 * @(#)Queue.java   1.1 2010/01/20
 *
 * (c) Copyright JavaService.Net, 2001. All rights reserved.
 *
 * NOTICE !      You can copy or redistribute this code freely,
 * but you should not remove the information about the copyright notice
 * and the author.
 */
package lee.common.util;

/**
 * The <code>Queue</code> class represents a first-in-first-out
 * (FIFO) queue of objects.
 *
 * @author  Lee WonYoung  javaservice@hanmail.net, lwy@kr.ibm.com
 * @version 1.0 2001/02/11
 */
public class Queue extends java.util.LinkedList {
  public Queue() {
    super();
  }

  /**
   * Removes the object at the front of this queue and returns
   * that object as the value of this function.
   *
   * @return     The object at the front of this queue.
   * @exception  EmptyQueueException  if this queue is empty.
   */
   public synchronized Object dequeue() {
    Object obj = peek();
    removeFirst();
    return obj;
  }

  /**
   * Tests if this queue is empty.
   *
   * @return  <code>true</code> if this queue is empty;
   *          <code>false</code> otherwise.
   */
  public boolean empty() {
    return size() == 0;
  }

  /**
   * Add an item to the end of the queue.
   *
   * @param   item   the item to be pushed to the end of this queue.
   * @return  the <code>item</code> argument.
   */
  public synchronized Object enqueue(Object item) {
    addLast(item);
    return item;
  }

  /**
   * Looks at the object at the front of this queue without removing it
   * from the queue.
   *
   * @return     the object at the top of this queue.
   * @exception  EmptyQueueException  if this queue is empty.
   */
  public synchronized Object peek() {
    if ( size() == 0 )
      throw new EmptyQueueException();
    return getFirst();
  }

  /**
   * Returns where an object is on this queue.
   *
   * @param   o   the desired object.
   * @return  the distance from the front of the queue where the object is
   *          located; the return value <code>-1</code> indicates that the
   *          object is not on the queue.
   */
  public synchronized int search(Object o) {
    return indexOf(o);
  }
}
