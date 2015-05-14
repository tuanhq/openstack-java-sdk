package com.woorea.openstack.examples.exception;

public class TenantException extends Exception {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String message = null;
  
  public TenantException() {
      super();
  }

  public TenantException(String message) {
      super(message);
      this.message = message;
  }

  public TenantException(Throwable cause) {
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
