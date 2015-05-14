package com.woorea.openstack.quantum.model;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("router")
public class RouterForCreate implements Serializable {

public static final class ExternalGateway implements Serializable {
    
    @JsonProperty("network_id")
    private String networkId;
    
    public String getNetworkId() {
      return networkId;
    }

    public void setNetworkId(String networkId) {
      this.networkId = networkId;
    }
    public ExternalGateway(String networkId) {
      this.networkId = networkId;
    }
    @Override
    public String toString() {
      return "external_gateway_info [network_id=" + networkId + "]";
    }
  
    
  }
  private String name;
  
  @JsonProperty("admin_state_up")
  private boolean adminStateUp;
  
  @JsonProperty("external_gateway_info")
  private ExternalGateway externalGateway;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean getAdminStateUp() {
    return adminStateUp;
  }

  public void setAdminStateUp(boolean adminStateUp) {
    this.adminStateUp = adminStateUp;
  }

  public ExternalGateway getExternalGateway() {
    return externalGateway;
  }

  public void setExternalGateway(ExternalGateway externalGateway) {
    this.externalGateway = externalGateway;
  }
  

}
