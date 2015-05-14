package com.woorea.openstack.quantum.model;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("floatingip")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FloatingIP implements Serializable {
  @JsonProperty("fixed_ip_address")
  private String fixedIpAddress;
  
  @JsonProperty("floating_ip_address")
  private String floatingIpAddress;
  
  @JsonProperty("floating_network_id")
  private String floatingNetworkId;
  
  private String id;
  @JsonProperty("port_id")
  private String portId;
  
  @JsonProperty("router_id")
  private String routerId;
  private String status;
  
  @JsonProperty("tenant_id")
  private String tenantId;

  public String getFixedIpAddress() {
    return fixedIpAddress;
  }

  public void setFixedIpAddress(String fixedIpAddress) {
    this.fixedIpAddress = fixedIpAddress;
  }

  public String getFloatingIpAddress() {
    return floatingIpAddress;
  }

  public void setFloatingIpAddress(String floatingIpAddress) {
    this.floatingIpAddress = floatingIpAddress;
  }

  public String getFloatingNetworkId() {
    return floatingNetworkId;
  }

  public void setFloatingNetworkId(String floatingNetworkId) {
    this.floatingNetworkId = floatingNetworkId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPortId() {
    return portId;
  }

  public void setPortId(String portId) {
    this.portId = portId;
  }

  public String getRouterId() {
    return routerId;
  }

  public void setRouterId(String routerId) {
    this.routerId = routerId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }  
  
}