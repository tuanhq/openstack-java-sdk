package com.woorea.openstack.nova.api.extensions;

public class FloatingIpPoll {
  private String pool;

  public String getPool() {
    return pool;
  }

  public void setPool(String pool) {
    this.pool = pool;
  }
  public FloatingIpPoll() {
    // TODO Auto-generated constructor stub
  }
  public FloatingIpPoll(String poll) {
    this.pool =poll;
  }
}
