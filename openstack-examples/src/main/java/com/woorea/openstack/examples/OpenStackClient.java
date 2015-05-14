package com.woorea.openstack.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.woorea.openstack.examples.exception.KeystoneException;
import com.woorea.openstack.examples.exception.RolessException;
import com.woorea.openstack.examples.exception.TenantException;
import com.woorea.openstack.examples.exception.UsersException;
import com.woorea.openstack.keystone.Keystone;
import com.woorea.openstack.keystone.model.Role;
import com.woorea.openstack.keystone.model.Tenant;
import com.woorea.openstack.keystone.model.User;
import com.woorea.openstack.nova.Nova;
import com.woorea.openstack.nova.model.Flavor;
import com.woorea.openstack.nova.model.Flavors;
import com.woorea.openstack.nova.model.Image;
import com.woorea.openstack.nova.model.KeyPairs;
import com.woorea.openstack.nova.model.Server;
import com.woorea.openstack.nova.model.ServerForCreate;
import com.woorea.openstack.nova.model.Servers;
import com.woorea.openstack.quantum.Quantum;
import com.woorea.openstack.quantum.model.FloatingIP;
import com.woorea.openstack.quantum.model.FloatingIpForCreate;
import com.woorea.openstack.quantum.model.Network;
import com.woorea.openstack.quantum.model.Port;
import com.woorea.openstack.quantum.model.PortForCreate;
import com.woorea.openstack.quantum.model.Router;
import com.woorea.openstack.quantum.model.RouterForAddInterface;
import com.woorea.openstack.quantum.model.RouterForCreate;
import com.woorea.openstack.quantum.model.Subnet;
import com.woorea.openstack.quantum.model.SubnetForCreate;



public class OpenstackClient {
   
  private  String tenantName ;
  private  String username ;
  private  String password ;
  private final ExecutorService pool = Executors.newFixedThreadPool(10);
  
  public String getTenantName() {
    return tenantName;
  }
  public void setTenantName(String tenantName) {
    this.tenantName = tenantName;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }  public void setPassword(String password) {
    this.password = password;
  }
  
  public OpenstackClient() {
   
  }
 
  public OpenstackClient(String tenantName, String username, String password) {
    this.tenantName = tenantName;
    this.username = username;
    this.password = password;
  }
  
  
  
  public static void main(String[] args) throws InterruptedException {
    try {
      
      //buoc 1 create tenant and username
      OpenstackClient openstack = new OpenstackClient("demo", "demo", "DEMO_PASS");
//      openstack.createFirst("fsoft", "fsoft", "123456", "admin@fsoft.com.vn");
//      openstack.setUsername("fsoft");
//      openstack.setTenantName("fsoft");
//      openstack.setPassword("123456");
 //     OpenstackClient openstack = new OpenstackClient("fsoft", "fsoft", "123456");
      
      ////buoc 2 create network
      //openstack.createNetwork("fsoft");
      ////create subnet
      
      //openstack.createSubnet("subnetFsoft", "10.0.0.1/24", "fsoft");
      
      ////create vm
      
   // openstack.createVM("sys-cloud-ats", "ubuntu-server-14.04", "cloud-sys","demo-net");
      
      //create floatingIP
      //   openstack.createFloatingIP("ext-net");
      
      //add floatingIp to server
    openstack.addFloatingIPToServer("sys-cloud-ats", "192.168.100.37");
    //  openstack.deleteVirtualMachineByName("sys-cloud-ats");
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      //TEST STEP BY STEP
      ////test create vm
  //   openstack.createVM("tuanhq12", "cirros-0.3.3-x86_64", "m1.tiny","demo-net");
      ////test delete vm
      // openstack.deleteVirtualMachineByName("woorea");
      
      //add floatingIp to vm
      
     /// openstack.addFloatingIPToServer("e1d7ff6c-6fce-4f6f-9ac7-36c201a5efb8", "192.168.100.33");
      //deattach floatIp to vm 
      
    //  openstack.remoteFloatingIPToServer("e1d7ff6c-6fce-4f6f-9ac7-36c201a5efb8", "192.168.100.33");
      
      ////create network
       //openstack.createNetwork("test2-net");
      ////delete network
      //openstack.deleteNetworkByName("test1-net");
      
      //create subnet 
      
      //openstack.createSubnet("subnet1","192.168.1/24", "test-net");
      //delete subnet
      //openstack.deleteSubnet("subnet1");
      
      //create route
     // openstack.createRouter("tuanhq", null);
    //delete router
      //openstack.deleteRouter("tuanhq6");
      
      //add interface to route 
      
    //  openstack.addNicToRouter("demo-router", "test-net-subnet");
      //detach interface in route
     // openstack.detachNicToRouter("demo-router", "test-net-subnet");
      
      
      //openstack.createPort("test-net");
     
    //  openstack.createFloatingIPByNetworkId("03849923-e8e4-4d7f-801d-07656ae79f40","45013449dbce4d669444569d5fa50ea9");
      
      //delete floatingip
     // openstack.deleteFloatingIp("192.168.100.11");
      
   //  openstack.createTenant("cloud-ats");
    //openstack.createUser("user3", "USER_PASS", "user1@openstack.com","cloud-ats");
    // openstack.createRoles("test-role");
    ///    openstack.addRoleToUserAndTenant("cloud-ats", "user2", "_member_");
      
     
        
      
    } catch (Exception e1) {
      // TODO Auto-generated catch block
     
    }
  
    
  }
  
  public void createFirst(String tenantName, String userName, String password, String email) throws KeystoneException, TenantException{
    createTenant(tenantName);
    createUser(userName, password, email, tenantName);
  }
  
  
  /**
   * 
   * virtual machine
   */
  
  //create 
  
  public void createSystemVM(String name) throws Exception {
    createVM(name, "cats-sys-image", "cloud-sys");
  }

  public void createNormalGuiVM(String name) throws Exception {
    createVM(name, "cats-ui-image", "m1.small");
  }

  public void createNormalNonGuiVM(String name) throws Exception {
    createVM(name, "cats-non-ui-image", "m1.small");
  }

  public void createVM(String name, String template, String offering)
      throws TenantException, KeystoneException {
    createVM(name, template, offering, null);
  }

  public  void createVM(String name, String template, String offering, String networkName) throws TenantException, KeystoneException {
    Nova nova = getNovaClient();
    
    KeyPairs keysPairs = nova.keyPairs().list().execute();
    ServerForCreate serverForCreate = new ServerForCreate();
    serverForCreate.setName(name);

    String flavorId = getIdFlavorByName(offering);
    serverForCreate.setFlavorRef(flavorId);
    String imageId = getIdImageByName(template);
    serverForCreate.setImageRef(imageId);
    if(keysPairs.getList().size()>0){
    serverForCreate.setKeyName(keysPairs.getList().get(0).getName());
    }
    if (networkName != null) {
      serverForCreate.getNetworks().add(new ServerForCreate.NetworkForCreate(getNetworkIdByName(networkName)));
    }
    serverForCreate.getSecurityGroups().add(new ServerForCreate.SecurityGroup("default"));
    nova.servers().boot(serverForCreate).execute();

  }
  
  public Future<Server> createVMAsync(final String name, final String template, final String offering) throws TenantException, KeystoneException {
    return createVMAsync(name, template, offering, null);
    
  }
  
  public Future <Server> createVMAsync(final String name, final String template, final String offering, final String networkName) throws TenantException, KeystoneException {

    return pool.submit(new Callable<Server>() {

    @Override

    public Server call() throws Exception {
      Nova nova = getNovaClient();
      
      KeyPairs keysPairs = nova.keyPairs().list().execute();
      ServerForCreate serverForCreate = new ServerForCreate();
      serverForCreate.setName(name);

      String flavorId = getIdFlavorByName(offering);
      serverForCreate.setFlavorRef(flavorId);
      String imageId = getIdImageByName(template);
      serverForCreate.setImageRef(imageId);
      serverForCreate.setKeyName(keysPairs.getList().get(0).getName());
      if (networkName != null) {
        serverForCreate.getNetworks().add(new ServerForCreate.NetworkForCreate(getNetworkIdByName(networkName)));
      }
      serverForCreate.getSecurityGroups().add(new ServerForCreate.SecurityGroup("default"));
      return nova.servers().boot(serverForCreate).execute();
      
    }

    });

    }
  
  
  //delete
    
  public void deleteVirtualMachineByName(String name) throws TenantException, KeystoneException{
    String id = getVmIdByName(name);
    deleteVirtualMachineById(id);
  }
  
  public  void deleteVirtualMachineById(String id) throws TenantException, KeystoneException{
    Nova novaClient = getNovaClient();
    novaClient.servers().delete(id).execute();    
  }
  
  /**
   * 
   * Start/stop  virtual machine
   */
  
  public  void startVirtualMachineByName(String name) throws Exception {
    String id = getVmIdByName(name);
    startVirtualMachineByID(id);
  }
  public  void startVirtualMachineByID(String id) throws Exception {
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
   * Add floatingIP to server
   
   */
  
  public void addFloatingIPToServerById(String serverId, String floatingIpAdress) throws TenantException, KeystoneException{
    Nova novaClient = getNovaClient();
    novaClient.servers().associateFloatingIp(serverId, floatingIpAdress).execute();
  }
  public void addFloatingIPToServer(String serverName, String floatingIpAdress) throws TenantException, KeystoneException{
    Nova novaClient = getNovaClient();
    String serverId = getServerIdByName(serverName);
    novaClient.servers().associateFloatingIp(serverId, floatingIpAdress).execute();
  }
  
  public String getServerIdByName (String serverName) throws TenantException, KeystoneException{    
    List<Server> list = getListServerByTenant();
    for(Server server: list){
      if(serverName.equals(server.getName())){
        return server.getId();
      }
    }
    return null;
  }
  public List<Server> getListServerByTenant() throws TenantException, KeystoneException{
    Nova novaClient = getNovaClient();
    return novaClient.servers().list(true).execute().getList();
    
  }
  
  public void remoteFloatingIPToServer(String serverId, String floatingIpId) throws TenantException, KeystoneException{
    Nova novaClient = getNovaClient();
    novaClient.servers().disassociateFloatingIp(serverId, floatingIpId).execute();
  }
  
  /**
   * Get list flavors
   *  
   * 
   */
  
  public List<Flavor> getlistFlavors() throws TenantException, KeystoneException{
    Nova novaClient = getNovaClient();
    return novaClient.flavors().list(true).execute().getList();
    
  }
  
  
  public List<String> getAvailabilityOfferingNames() throws TenantException, KeystoneException {
    List<Flavor> listFlavor = getlistFlavors();
    List<String> availabilityOfferingNames =new ArrayList<String>() ;
    for (Flavor flavor : listFlavor) {
      availabilityOfferingNames.add(flavor.getName());
    }
    return availabilityOfferingNames;
  }
  
  public String getIdFlavorByName(String name) throws TenantException, KeystoneException{
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
  
  public  String getVmIdByName(String name) throws TenantException, KeystoneException{

    Nova novaClient = getNovaClient();
    Servers servers = novaClient.servers().list(true).execute();
    for(Server server : servers) {
      if (name.equalsIgnoreCase(server.getName())){    
        return server.getId();                
      }
    }
    return null; 
  }
  public  Server getServerByName(String name) throws TenantException, KeystoneException{
    Nova novaClient = getNovaClient();
    Servers servers = novaClient.servers().list(true).execute();
    for(Server server : servers) {
      if (name.equalsIgnoreCase(server.getName())){    
        return server;               
      }
    }
    return null;
  }
  
  public  Server getServerById(String id) throws TenantException, KeystoneException{
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
  
  public List<Image> getListImage() throws TenantException, KeystoneException{
    Nova novaClient = getNovaClient();
    return novaClient.images().list(true).execute().getList();
  }
  
  public String getIdImageByName(String name) throws TenantException, KeystoneException {
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
  
  public List<Network> getListNetwork() throws TenantException, KeystoneException{
    Quantum quantum = getQuantumClient();
    List<Network> list = quantum.networks().list().execute().getList();
    return list;
  }
  public String getNetworkIdByName(String networkName) throws TenantException, KeystoneException{
    List<Network>list = getListNetwork();
    for (Network network : list) {
      if( networkName.equalsIgnoreCase(network.getName()) ){
        return network.getId();
      }
      
    }
    return null;
  }
  
  //create network
  public void createNetwork(String networkName) throws TenantException, KeystoneException{  
    Quantum quantum = getQuantumClient();
    com.woorea.openstack.quantum.model.NetworkForCreate networkForCreate = new com.woorea.openstack.quantum.model.NetworkForCreate();  
    networkForCreate.setName(networkName);
    networkForCreate.setAdminStateUp(true);     
    //networkForCreate.setProviderPhysicalNetwork("external");
    //networkForCreate.setProviderNetworkType("flat");
    //networkForCreate.setTenantId(tenantId);
    quantum.networks().create(networkForCreate).execute();
  }
  
  //delete network
  public void deleteNetworkByName(String networkName) throws TenantException, KeystoneException{
    String netId = getNetworkIdByName(networkName);
    deleteNetworkById(netId);
  }
  public void deleteNetworkById(String networkId) throws TenantException, KeystoneException{
    Quantum quantum = getQuantumClient();   
    quantum.networks().delete(networkId).execute();
  }
  
  //create subnet
  public void createSubnet(String subnetName, String cidr, String networkId, int ipVersion) throws TenantException, KeystoneException{
    Quantum quantum = getQuantumClient();
    SubnetForCreate subnetForCreate = new SubnetForCreate();
    subnetForCreate.setName(subnetName);
    subnetForCreate.setCidr(cidr);      
    subnetForCreate.setIpVersion(ipVersion);
    subnetForCreate.setNetworkId(networkId);
    quantum.subnets().create(subnetForCreate).execute();
    
  }
  public void createSubnet(String subnetName ,String cidr, String networkName) throws TenantException, KeystoneException{
    String netId = getNetworkIdByName(networkName);
    createSubnet(subnetName, cidr, netId, 4);
  }
  //delete subnet
  public void deleteSubnet(String subnetName) throws TenantException, KeystoneException{    
    String subnetId = getSubnetIdByName(subnetName);
    deleteSubnetById(subnetId);
    
  }
  public void deleteSubnetById(String subnetId) throws TenantException, KeystoneException{
    Quantum quantum = getQuantumClient();   
    quantum.subnets().delete(subnetId).execute();    
    
  }
  
  //get subnetId by name
  public String getSubnetIdByName(String subnetName) throws TenantException, KeystoneException{
    Quantum quantum = getQuantumClient();
    List<Subnet> listSubnet = quantum.subnets().list().execute().getList();
    for (Subnet subnet : listSubnet) {
      if(subnetName.equals(subnet.getName())){
        return subnet.getId();
      }
    }
    return null;
  }
  
    
  //create router
  
  public void createRouter(String routerName, String networkName) throws TenantException, KeystoneException{
    Quantum quantum = getQuantumClient();
    RouterForCreate routerForCreate = new RouterForCreate();
    routerForCreate.setAdminStateUp(true);
    routerForCreate.setName(routerName);
    if(networkName != null){
      String networkId = getNetworkIdByName(networkName);
      routerForCreate.setExternalGateway(new RouterForCreate.ExternalGateway(networkId));
    }
    quantum.routers().create(routerForCreate).execute();
    
  }
  //delete router
  public void deleteRouter(String routerName) throws TenantException, KeystoneException{
    Quantum quantum = getQuantumClient();
    String routeId = getRouterIdByName(routerName);
    quantum.routers().delete(routeId).execute();
    
    
  }
  //list router
  
  public List<Router> getListRouter() throws TenantException, KeystoneException{
    Quantum quantum = getQuantumClient();    
    return quantum.routers().list().execute().getList();
    
  }
  public String getRouterIdByName(String routerName) throws TenantException, KeystoneException{
    List<Router>list = getListRouter();
    for (Router router : list) {
        if(routerName.equals(router.getName())) return router.getId(); 
    }
    return null;
  }
  
  //add interface to router 
  
  public void addNicToRouterById(String routerId, String subnetId) throws TenantException, KeystoneException{
    Quantum quantum = getQuantumClient();
    RouterForAddInterface routerForAddInteface  = new RouterForAddInterface();
    routerForAddInteface.setRouterId(routerId);
    routerForAddInteface.setSubnetId(subnetId);
    quantum.routers().addInterface(routerForAddInteface).execute();
     
  }
  
  public void addNicToRouter(String routerName, String subnetName) throws TenantException, KeystoneException{
    String routerId = getRouterIdByName(routerName);
    String subnetId = getSubnetIdByName(subnetName);
    addNicToRouterById(routerId, subnetId);
     
  }
  
  //detach interface to router
  
  public void detachNicToRouterById(String routerId, String subnetId) throws TenantException, KeystoneException{
    Quantum quantum = getQuantumClient();
    RouterForAddInterface routerForAddInteface  = new RouterForAddInterface();
    routerForAddInteface.setRouterId(routerId);
    routerForAddInteface.setSubnetId(subnetId);
    quantum.routers().deleteInterface(routerForAddInteface).execute();    
     
  }
  
  public void detachNicToRouter(String routerName, String subnetName) throws TenantException, KeystoneException{
    String routerId = getRouterIdByName(routerName);
    String subnetId = getSubnetIdByName(subnetName);
    detachNicToRouterById(routerId, subnetId);
     
  }
  
  //create port 
  
  public void createPort(String networkName) throws TenantException, KeystoneException{
    String networkId = getNetworkIdByName(networkName);
    createPortById(networkId);
     
  }
  
  //delete port 
  
  public void deletePort(String portId) throws TenantException, KeystoneException{
    Quantum quantum = getQuantumClient();
    quantum.ports().delete(portId).execute();
  }
  
  public List<Port> getListPort(String networkId) throws TenantException, KeystoneException{
    Quantum quantum = getQuantumClient();
    return quantum.ports().list().execute().getList();
  }
  public String getPortByIp(String ip, String subnetId, String networkId) throws TenantException, KeystoneException{    
    List<Port> listPort = getListPort(networkId);
    for (Port port : listPort) {
      
    }
    return null;
  }
   
  public void createPortById(String networkId) throws TenantException, KeystoneException{
    createPortById(networkId, null);
     
  }
  
  public void createPortById(String networkId, String subnetId) throws TenantException, KeystoneException{
    Quantum quantum = getQuantumClient();
    PortForCreate portForCreate = new PortForCreate();
    portForCreate.setNetworkId(networkId);
    if(subnetId != null){
      portForCreate.setSubnetId(subnetId);
    }
    quantum.ports().create(portForCreate).execute();
     
  }
  //create float ip
  
  public void createFloatingIPByNetworkId(String networkId, String tenantId) throws TenantException, KeystoneException{
    Quantum quantum = OpenStackCore.getQuantumAdminClient();        
    FloatingIpForCreate floatIpForCreate = new FloatingIpForCreate();
    floatIpForCreate.setFloatingNetworkId(networkId);
    if( tenantId !=null ){
      floatIpForCreate.setTenantId(tenantId);
    }
    quantum.floatingIps().create(floatIpForCreate).execute();
     
  }
  
  public void createFloatingIP(String networkName) throws TenantException, KeystoneException{
    String networkId = getNetworkIdByName(networkName);
    String tenantId = getTenantId();        
    createFloatingIPByNetworkId(networkId, tenantId);
  }
  
  //delete floatIp
  
  public void deletFloatingIP(String floatIpId) throws TenantException, KeystoneException{
    Quantum quantum = OpenStackCore.getQuantumAdminClient();       
    
    quantum.floatingIps().delete(floatIpId).execute();
     
  }
  public void deleteFloatingIp(String floatingIpAddr) throws TenantException, KeystoneException{
    String floatingIpId = getFloatingIpId(floatingIpAddr);
    deletFloatingIP(floatingIpId);
  }
  
  public List<FloatingIP> getListFloatingIPs() throws TenantException, KeystoneException{
    Quantum quantum = OpenStackCore.getQuantumAdminClient();
    return quantum.floatingIps().list().execute().getList();
    
  }
  public String getFloatingIpId(String floatingIpAddr) throws TenantException, KeystoneException{
    List<FloatingIP> list = getListFloatingIPs();
    
    for (FloatingIP floatingIP : list) {
      if( floatingIpAddr.equals(floatingIP.getFloatingIpAddress())){
        return floatingIP.getId();
      }
    }
    return null;
  }
  
  /**
   * 
   * USER AND TENANT   
   * @throws TenantException 
  
   */
  
 
   public void createUser(String username, String password, String email, String tenantName) throws KeystoneException, TenantException{
     String tenantId = OpenStackCore.getTenantIdByName(tenantName);
     Keystone keystone = getKeystoneAdmin();
     User user = new User();
     user.setEmail(email);
     user.setUsername(username);
     user.setPassword(password);
     user.setName(username);
     user.setEnabled(Boolean.TRUE);     
     user.setTenantId(tenantId);
     user = keystone.users().create(user).execute();    
   
   }
   
   public void deleteUserById(String userId) throws KeystoneException, TenantException{
     Keystone keystone = getKeystoneAdmin();
     keystone.users().delete(userId).execute();
   }
  
   public void deleteUser(String userName) throws KeystoneException, TenantException, UsersException{
     String userId = OpenStackCore.getUserIdByName(userName);
     deleteUserById(userId);
   }
  
   public void createTenant(String tenantName) throws KeystoneException, TenantException{
     
     Keystone keystone = getKeystoneAdmin();
     Tenant tenant = new Tenant();
     tenant.setName(tenantName);
     tenant.setDescription(tenantName);
     tenant.setEnabled(true);     
     tenant = keystone.tenants().create(tenant).execute();     
    
   }
   
   public  void deleteTenantById(String tenantId) throws KeystoneException, TenantException {
     Keystone keystone = getKeystoneAdmin();
     keystone.tenants().delete(tenantId).execute();
   }
   
   public  void deleteTenant(String tenantName) throws KeystoneException, TenantException {
     String tenantId = OpenStackCore.getTenantIdByName(tenantName);
     deleteTenantById(tenantId);
   }
   
   public void createRoles(String roleName) throws TenantException, KeystoneException{
     Keystone keystone = getKeystoneAdmin();
     Role role = new Role();
     role.setName(roleName);
     keystone.roles().create(role).execute();
     
     
   }
   public void deleteRoleById(String roleId) throws TenantException, KeystoneException{
     Keystone keystone = getKeystoneAdmin();
     keystone.roles().delete(roleId).execute();
   }
   
   public void deleteRole(String roleName) throws TenantException, KeystoneException, RolessException{
     String roleId = OpenStackCore.getRoleIdByName(roleName);
     deleteRoleById(roleId);
     
   }
   
   public void addRoleToUserAndTenantById(String tenantId, String userId, String roleId) throws TenantException, KeystoneException{
     Keystone keystone = getKeystoneAdmin();
     keystone.tenants().addUser(tenantId, userId, roleId).execute();
   }
   
   public void addRoleToUserAndTenant(String tenantName, String userName, String roleName) throws TenantException, KeystoneException, UsersException, RolessException{
     String tenantId = OpenStackCore.getTenantIdByName(tenantName);
     String userId = OpenStackCore.getUserIdByName(userName);
     String roleId = OpenStackCore.getRoleIdByName(roleName);
     addRoleToUserAndTenantById(tenantId, userId, roleId);
     
     
   }
   
   private String getTenantId() throws TenantException, KeystoneException{
     return OpenStackCore.getFirstTenant(tenantName, username, password).getId();
   }
  
   private Nova getNovaClient() throws TenantException, KeystoneException{
     Nova nova = OpenStackCore.getNovaClient(tenantName, username, password);
     return nova;
  }
  private Quantum getQuantumClient () throws TenantException, KeystoneException {
    Quantum quantum = OpenStackCore.getQuantumClient(tenantName, username, password);
    return quantum;
  }
  private Keystone getKeystoneAdmin() throws TenantException, KeystoneException {
    Keystone keystone = OpenStackCore.getKeystoneAdminClient();
    return keystone;
  }
  private Keystone getKeystone() throws TenantException, KeystoneException {
    Keystone keystone = OpenStackCore.getKeystoneClient(tenantName, username, password);
    
    return keystone;
  }
  
 
}
