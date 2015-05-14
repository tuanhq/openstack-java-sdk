package com.woorea.openstack.quantum.model;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("floatingip")
public class FloatingIpForCreate implements Serializable{
  
  @JsonProperty("floating_network_id")
  private String floatingNetworkId;
  @JsonProperty("tenant_id")  
  private String tenantId;
  
  
  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public String getFloatingNetworkId() {
    return floatingNetworkId;
  }

  public void setFloatingNetworkId(String floatingNetworkId) {
    this.floatingNetworkId = floatingNetworkId;
  }
  public FloatingIpForCreate() {
    // TODO Auto-generated constructor stub
  }
}
