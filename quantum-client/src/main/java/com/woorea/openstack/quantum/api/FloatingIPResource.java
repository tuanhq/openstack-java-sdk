package com.woorea.openstack.quantum.api;


import com.woorea.openstack.base.client.Entity;
import com.woorea.openstack.base.client.HttpMethod;
import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.base.client.OpenStackRequest;
import com.woorea.openstack.quantum.model.FloatingIP;
import com.woorea.openstack.quantum.model.FloatingIPs;
import com.woorea.openstack.quantum.model.FloatingIpForCreate;


public class FloatingIPResource {

  private final OpenStackClient CLIENT;

  public FloatingIPResource(OpenStackClient client) {
    CLIENT = client;
  }

  public List list() {
    return new List();
  }

  public Create create(FloatingIpForCreate floatingForCreate){
    return new Create(floatingForCreate);
  }

  public Delete delete(String netId){
    return new Delete(netId);
  }

  public Show show(String netId){
    return new Show(netId);
  }

  public class List extends OpenStackRequest<FloatingIPs> {

    public List() {
        super(CLIENT, HttpMethod.GET, "floatingips", null, FloatingIPs.class);
    }
  }

  public class Query extends OpenStackRequest<FloatingIPs> {

    public Query(FloatingIPs network) {
      //super(network);
//      target = target.path("v2.0").path("networks");
//      target = queryParam(target);
//      return target.request(MediaType.APPLICATION_JSON).get(Networks.class);
    }
  }


  public class Create extends OpenStackRequest<FloatingIP> {

    public Create(FloatingIpForCreate net){
        super(CLIENT, HttpMethod.POST, "floatingips", Entity.json(net), FloatingIP.class);
    }
  }

  public class Show extends OpenStackRequest<FloatingIP> {

    public Show(String id) {
        super(CLIENT, HttpMethod.GET, buildPath("floatingips/", id), null, FloatingIP.class);
    }
  }

  public class Delete extends OpenStackRequest<Void> {

    public Delete(String id){
        super(CLIENT, HttpMethod.DELETE, buildPath("floatingips/", id), null, Void.class);
    }
  }
}
