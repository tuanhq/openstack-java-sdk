package com.woorea.openstack.examples;

import java.util.ArrayList;
import java.util.List;

import com.woorea.openstack.base.client.OpenStackSimpleTokenProvider;
import com.woorea.openstack.keystone.Keystone;
import com.woorea.openstack.keystone.model.Access;
import com.woorea.openstack.keystone.model.Tenants;
import com.woorea.openstack.keystone.model.authentication.TokenAuthentication;
import com.woorea.openstack.keystone.model.authentication.UsernamePassword;
import com.woorea.openstack.keystone.utils.KeystoneUtils;
import com.woorea.openstack.nova.Nova;
import com.woorea.openstack.nova.model.Flavor;
import com.woorea.openstack.nova.model.Flavors;
import com.woorea.openstack.nova.model.Image;
import com.woorea.openstack.nova.model.KeyPairs;
import com.woorea.openstack.nova.model.Server;
import com.woorea.openstack.nova.model.ServerForCreate;
import com.woorea.openstack.nova.model.Servers;
import com.woorea.openstack.quantum.Quantum;
import com.woorea.openstack.quantum.model.Network;


public class OpenStackClient { 
  public static void main(String[] args) {
    new OpenStackClient()
        .createVM("tuanhq", "cirros-0.3.3-x86_64", "m1.tiny","tuanhq");
    // new OpenStackClient().getIdImageByName("cirros-0.3.3-x86_64");
    // new OpenStackClient().deleteVirtualMachineByName("trinhtv1");
  }
  
  /**
   * 
   * Create virtual machine
   */
  
  public void createSystemVM(String name)  throws Exception {
     createVM(name, "cats-sys-image", "cloud-sys");
  }
  
  public void createNormalGuiVM(String name) throws Exception {
     createVM(name, "cats-ui-image", "m1.small");
  }
  
  public void createNormalNonGuiVM(String name) throws Exception {
     createVM(name, "cats-non-ui-image", "m1.small");
  }
  public void createVM(String name, String template, String offering){
    createVM(name, template, offering, null);
  }
  public void createVM(String name, String template, String offering,String networkName){
    Nova nova = getNovaClient();
    if (nova !=null ) {
          
      KeyPairs keysPairs = nova.keyPairs().list().execute();
      ServerForCreate serverForCreate = new ServerForCreate();
      serverForCreate.setName(name);
      
      String flavorId = getIdFlavorByName(offering);
      serverForCreate.setFlavorRef(flavorId);
      String imageId = getIdImageByName(template);       
      serverForCreate.setImageRef(imageId);    
      serverForCreate.setKeyName(keysPairs.getList().get(0).getName());
      if(networkName!=null) {
        serverForCreate.getNetworks().add(new ServerForCreate.NetworkForCreate(getNetworkIdByName(networkName)));
      }
      serverForCreate.getSecurityGroups()
          .add(new ServerForCreate.SecurityGroup("default"));      
      nova.servers().boot(serverForCreate).execute();   
      
    } else {
      System.out.println("No tenants found!");
    }
  }
  
  /**
   * 
   * Delete virtual machine
   */
  
    
  public void deleteVirtualMachineByName(String name){
    String id = getIdImageByName(name);
    deleteVirtualMachineById(id);
  }
  
  public void deleteVirtualMachineById(String id){
    Nova novaClient = getNovaClient();
    novaClient.servers().delete(id);    
  }
  
  /**
   * 
   * Start/stop  virtual machine
   */
  
  public void startVirtualMachineByName(String name) throws Exception {
    String id = getVmIdByName(name);
    startVirtualMachineByID(id);
  }
  public void startVirtualMachineByID(String id) throws Exception {
    Nova novaClient = getNovaClient();
    novaClient.servers().start(id).execute();
  }
  
  public void stopVirtualMachineByName(String name) throws Exception {
    String id = getVmIdByName(name);
    stopVirtualMachineById(id);
  }
  public void stopVirtualMachineById(String id) throws Exception {
    Nova novaClient = getNovaClient();
    novaClient.servers().stop(id).execute();
  }
  
  /**
   * Get list flavors
   *  
   * 
   */
  
  public List<Flavor> getlistFlavors(){
    Nova novaClient = getNovaClient();
    return novaClient.flavors().list(true).execute().getList();
    
  }
  
  
  public List<String> getAvailabilityOfferingNames() {
    List<Flavor> listFlavor = getlistFlavors();
    List<String> availabilityOfferingNames =new ArrayList<String>() ;
    for (Flavor flavor : listFlavor) {
      availabilityOfferingNames.add(flavor.getName());
    }
    return availabilityOfferingNames;
  }
  
  public String getIdFlavorByName(String name){
    Nova novaClient = getNovaClient();
    Flavors flavors = novaClient.flavors().list(true).execute();
    for(Flavor flavor : flavors) {
      if(name.equalsIgnoreCase(flavor.getName())){
        return flavor.getId();
      }
    }
    return null;
    
  }
  
  /**
   * Get list servers
   */
  
  public List<Server> listVirtualMachines() throws Exception {
    Nova novaClient = getNovaClient();
    return novaClient.servers().list(true).execute().getList();
  }
  
  public String getVmIdByName(String name){

    Nova novaClient = getNovaClient();
    Servers servers = novaClient.servers().list(true).execute();
    for(Server server : servers) {
      if (name.equalsIgnoreCase(server.getName())){    
        return server.getId();                
      }
    }
    return null; 
  }
  public Server getServerByName(String name){
    Nova novaClient = getNovaClient();
    Servers servers = novaClient.servers().list(true).execute();
    for(Server server : servers) {
      if (name.equalsIgnoreCase(server.getName())){    
        return server;               
      }
    }
    return null;
  }
  
  public Server getServerById(String id){
    Nova novaClient = getNovaClient();
    Servers servers = novaClient.servers().list(true).execute();
    for(Server server : servers) {
      if (id.equalsIgnoreCase(server.getId())){    
        return server;               
      }
    }
    return null;
  }
  
  /**
   * Get images
   */
  
  public List<Image> getListImage(){
    Nova novaClient = getNovaClient();
    return novaClient.images().list(true).execute().getList();
  }
  
  public String getIdImageByName(String name) {
    List<Image> images = getListImage();
    for (Image image : images) {
      if (name.equalsIgnoreCase(image.getName())) {
        return image.getId();
      }

    }
    return null;
  }
  
  /***
   * get list network
   */
  
  public List<Network> getListNetwork(){
    Quantum quantum = getQuantumClient();
    List<Network> list = quantum.networks().list().execute().getList();
    return list;
  }
  public String getNetworkIdByName(String name){
    List<Network>list = getListNetwork();
    for (Network network : list) {
      if( name.equalsIgnoreCase(network.getName())){
        return network.getId();
      }
      
    }
    return null;
  }
  
  /**
   * 
   * Utity 
   * @return
   */
    
  
  private  Nova getNovaClient(){
    Keystone keystone = new Keystone(ExamplesConfiguration.KEYSTONE_AUTH_URL);
    Access access = keystone.tokens().authenticate(new UsernamePassword(ExamplesConfiguration.KEYSTONE_USERNAME, ExamplesConfiguration.KEYSTONE_PASSWORD)).execute();
    
    //use the token in the following requests
    keystone.token(access.getToken().getId());
    
    Tenants tenants = keystone.tenants().list().execute();
    
    //try to exchange token using the first tenant
    if(tenants.getList().size() > 0) {
      
      access = keystone.tokens().authenticate(new TokenAuthentication(access.getToken().getId()))
          .withTenantId(tenants.getList().get(0).getId())
          .execute();
      
      //NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), "compute", null, "public"), access.getToken().getId());
      Nova novaClient = new Nova(ExamplesConfiguration.NOVA_ENDPOINT.concat("/").concat(tenants.getList().get(0).getId()));
      novaClient.token(access.getToken().getId());
      return novaClient;
     
  }
    return null;
  }
  private Quantum getQuantumClient(){
    Keystone keystone = new Keystone(ExamplesConfiguration.KEYSTONE_AUTH_URL);
    // access with unscoped token
    Access access = keystone.tokens().authenticate(
        new UsernamePassword(ExamplesConfiguration.KEYSTONE_USERNAME, ExamplesConfiguration.KEYSTONE_PASSWORD))
        .execute();
    // use the token in the following requests
    keystone.setTokenProvider(new OpenStackSimpleTokenProvider(access.getToken().getId()));

    Tenants tenants = keystone.tenants().list().execute();
    // try to exchange token using the first tenant
    if (tenants.getList().size() > 0) {
      // access with tenant
      access = keystone.tokens().authenticate(new TokenAuthentication(access.getToken().getId())).withTenantId(tenants.getList().get(0).getId()).execute();     
      Quantum quantum = new Quantum(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), "network",  null, "public").concat("/").concat("v2.0"));      
      quantum.setTokenProvider(new OpenStackSimpleTokenProvider(access.getToken().getId()));
      return quantum;
    }
    return null;
  }
  

}
