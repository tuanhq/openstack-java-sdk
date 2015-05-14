package com.woorea.openstack.quantum.model;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;



@JsonRootName("router")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Router  implements Serializable {
  
  @JsonRootName("external_gateway_info")
  @JsonIgnoreProperties(ignoreUnknown = true) 
public static final class ExternalGateway implements Serializable {
    
    @JsonProperty("network_id")
    private String networkId;
    
    @JsonProperty("enable_snat")    
    private boolean enableSnat;
    public boolean isEnableSnat() {
      return enableSnat;
    }

    public void setEnableSnat(boolean enableSnat) {
      this.enableSnat = enableSnat;
    }

    public String getNetworkId() {
      return networkId;
    }

    public void setNetworkId(String networkId) {
      this.networkId = networkId;
    }
    public ExternalGateway(){
      
    }
    public ExternalGateway(String networkId, boolean enableSnat) {
      this.networkId = networkId;
      this.enableSnat = enableSnat;
    }
    @Override
    public String toString() {
      return "external_gateway_info [network_id=" + networkId + ",enable_snat=" +enableSnat + "]";
    }
  
    
  }
  
 public static final class Ip implements Serializable {
    
    @JsonProperty("ip_address")
    private String address;
    @JsonProperty("subnet_id")
    private String subnetId;

    /**
     * @return the address
     */
    public String getAddress() {
      return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
      this.address = address;
    }

    /**
     * @return the subnetId
     */
    public String getSubnetId() {
      return subnetId;
    }

    /**
     * @param subnetId the subnetId to set
     */
    public void setSubnetId(String subnetId) {
      this.subnetId = subnetId;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
      return "ip_addresses [ip_address=" + address + ", subnet_id=" + subnetId + "]";
    }
    
  }
  
  private String status;
  
  @JsonProperty("external_gateway_info")
  private ExternalGateway externalGateway;
  
  @JsonProperty("external_fixed_ips")
  private List<Ip> list;
  
  public List<Ip> getList() {
    return list;
  }

  public void setList(List<Ip> list) {
    this.list = list;
  }
  private String name;
  
  @JsonProperty("admin_state_up")
  private boolean adminStateUp;
  
  @JsonProperty("tenant_id")
  private String tenantId;
  
  private String id;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public ExternalGateway getExternalGateway() {
    return externalGateway;
  }

  public void setExternalGateway(ExternalGateway externalGateway) {
    this.externalGateway = externalGateway;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isAdminStateUp() {
    return adminStateUp;
  }

  public void setAdminStateUp(boolean adminStateUp) {
    this.adminStateUp = adminStateUp;
  }

  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
  @Override
  public String toString() {
    return "Router [name=" + name + ", admin_state_up=" + adminStateUp +
        ",external_gateway_info=" + externalGateway + ",tenant_id=" + tenantId + ",id=" + id + " ]";
  }
  

}
