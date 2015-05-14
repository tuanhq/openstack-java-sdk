
package com.woorea.openstack.examples.exception;

public class RolessException extends Exception{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String message = null;
  
  public RolessException() {
      super();
  }

  public RolessException(String message) {
      super(message);
      this.message = message;
  }

  public RolessException(Throwable cause) {
      super(cause);
  }

  @Override
  public String toString() {
      return message;
  }

  @Override
  public String getMessage() {
      return message;
  }

}
