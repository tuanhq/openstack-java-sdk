package com.woorea.openstack.examples.exception;

public class UsersException extends Exception {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String message = null;
  
  public UsersException() {
      super();
  }

  public UsersException(String message) {
      super(message);
      this.message = message;
  }

  public UsersException(Throwable cause) {
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
