package com.woorea.openstack.examples.exception;

public class KeystoneException extends Exception{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String message = null;
  
  public KeystoneException() {
      super();
  }

  public KeystoneException(String message) {
      super(message);
      this.message = message;
  }

  public KeystoneException(Throwable cause) {
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
