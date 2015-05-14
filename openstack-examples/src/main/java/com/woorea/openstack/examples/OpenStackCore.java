package com.woorea.openstack.examples;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.woorea.openstack.base.client.OpenStackSimpleTokenProvider;
import com.woorea.openstack.examples.exception.KeystoneException;
import com.woorea.openstack.examples.exception.RolessException;
import com.woorea.openstack.examples.exception.TenantException;
import com.woorea.openstack.examples.exception.UsersException;
import com.woorea.openstack.keystone.Keystone;
import com.woorea.openstack.keystone.model.Access;
import com.woorea.openstack.keystone.model.Role;
import com.woorea.openstack.keystone.model.Roles;
import com.woorea.openstack.keystone.model.Tenant;
import com.woorea.openstack.keystone.model.Tenants;
import com.woorea.openstack.keystone.model.User;
import com.woorea.openstack.keystone.model.Users;
import com.woorea.openstack.keystone.model.authentication.UsernamePassword;
import com.woorea.openstack.keystone.utils.KeystoneUtils;
import com.woorea.openstack.nova.Nova;
import com.woorea.openstack.quantum.Quantum;

public class OpenStackCore {
  private static ConcurrentHashMap<String, Keystone> hashKeystone;
  private static ConcurrentHashMap<String, Access> hashTenantAccess;
  private static ConcurrentHashMap<String, Nova> hashNovaClient;
  private static ConcurrentHashMap<String, Quantum> hashQuantumClient;

  static {
    hashKeystone = new ConcurrentHashMap<String, Keystone>();
    hashKeystone.put(OpenstackConfiguration.ADMIN_KEYSTONE_USERNAME, new Keystone(OpenstackConfiguration.ADMIN_KEYSTONE_ENDPOINT));
    hashTenantAccess = new ConcurrentHashMap<String, Access>();
    hashNovaClient = new ConcurrentHashMap<String, Nova>();
    hashQuantumClient = new ConcurrentHashMap<String, Quantum>();

  }
  
  
  
  
  /**
   * TOKEN 
  */
  
    public static  Access getAccessByTenant(String tenantName,String username, String password) throws KeystoneException {
    Access access = hashTenantAccess.get(tenantName);
    
    if (access == null || Calendar.getInstance().after(access.getToken().getExpires())) {
      try {
        access = getKeyStoneByTenant(tenantName).tokens().authenticate(new UsernamePassword(username, password))
            .withTenantName(tenantName)
            .execute();
      } catch (Exception e) {
        throw new KeystoneException("Authentication fail");
      }
      
      hashTenantAccess.put(tenantName, access);
    }   
    return access;
  }
  
  
  
  public static Keystone getKeyStoneByTenant(String tenantName){
    Keystone keystone = hashKeystone.get(tenantName);
    if (keystone == null) {
      keystone = new Keystone(OpenstackConfiguration.KEYSTONE_AUTH_URL);
      hashKeystone.put(tenantName, keystone);
    }
    return keystone;
  }
  public static Keystone getKeystoneAdminClient() throws KeystoneException {
    Keystone keystone = getKeyStoneByTenant(OpenstackConfiguration.ADMIN_TENANT_NAME);    
    String tokenId = getAdminToken();
    keystone.setTokenProvider(new OpenStackSimpleTokenProvider(tokenId));
    return keystone;
  }
  
  public static Keystone getKeystoneClient(String tenantName, String username, String password) throws KeystoneException {
    Keystone keystone = getKeyStoneByTenant(tenantName);    
    String tokenId = getToken(tenantName, username, password);
    keystone.setTokenProvider(new OpenStackSimpleTokenProvider(tokenId));
    return keystone;
  }
  
  
  public static String getToken(String tenantName, String username, String password) throws KeystoneException {
    Access access = getAccessByTenant(tenantName, username, password);
    return access.getToken().getId();
  }

  public static String getAdminToken() throws KeystoneException {
    Access access = getAccessByTenant(OpenstackConfiguration.ADMIN_TENANT_NAME,
        OpenstackConfiguration.ADMIN_KEYSTONE_USERNAME,
        OpenstackConfiguration.ADMIN_KEYSTONE_PASSWORD);
    return access.getToken().getId();
  }

  
  
  /**
   * 
   * NETWORK
   * 
   */
 
  public static Quantum getQuantumAdminClient() throws TenantException, KeystoneException{
    return getQuantumClient(OpenstackConfiguration.ADMIN_TENANT_NAME, OpenstackConfiguration.ADMIN_KEYSTONE_USERNAME, OpenstackConfiguration.ADMIN_KEYSTONE_PASSWORD);
  }
  
  
  public static  Quantum getQuantumClient(String tenantName,String username, String password) throws TenantException, KeystoneException{
    Quantum quantum  = hashQuantumClient.get(tenantName);
    if (quantum == null ) {
      Access access = getAccessByTenant(tenantName, username, password);
      quantum = new Quantum(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), "network",  null, "public").concat("/").concat("v2.0"));
      hashQuantumClient.put(tenantName, quantum);
    }
    String tokenKey = getToken(tenantName, username, password);   
    quantum.setTokenProvider(new OpenStackSimpleTokenProvider(tokenKey));
    return quantum;
  }
  
  
  
  
  /**
   * 
   * NOVA
   */
    
  public static  Nova getNovaAdminClient() throws TenantException, KeystoneException{
    return getNovaClient(OpenstackConfiguration.ADMIN_TENANT_NAME, OpenstackConfiguration.ADMIN_KEYSTONE_USERNAME, OpenstackConfiguration.ADMIN_KEYSTONE_PASSWORD);
  }
  
  public static  Nova getNovaClient(String tenantName,String username, String password) throws TenantException, KeystoneException{
    Nova novaClient =  hashNovaClient.get(tenantName);
    if(novaClient == null) {
      Tenant tenant = getFirstTenant(tenantName, username, password);
      novaClient = new Nova(OpenstackConfiguration.NOVA_ENDPOINT.concat("/").concat(tenant.getId()));
      hashNovaClient.put(tenantName, novaClient);
    }
    String tokenKey = getToken(tenantName, username, password);   
    novaClient.setTokenProvider(new OpenStackSimpleTokenProvider(tokenKey));
    return novaClient;
  }
  
  
  
  
  /**
   * 
   * KEYSTONE, TENANT, USER
   */
  
  public static Tenants getListTenants(String tenantName,String username, String password) throws TenantException, KeystoneException{
    Keystone keystone = getKeyStoneByTenant(tenantName);
    String tokenId = getToken(tenantName, username, password);
    keystone.setTokenProvider(new OpenStackSimpleTokenProvider(tokenId));
    Tenants tenants = keystone.tenants().list().execute();
    if (tenants == null)
      throw new TenantException("Not found any teant");
    return tenants;
  }
   
  public static List<Tenant> getFullListTenants() throws TenantException, KeystoneException{
    Keystone keystone = getKeystoneAdminClient();    
    Tenants tenants = keystone.tenants().list().execute();
    if (tenants == null)
      throw new TenantException("Not found any teant");
    return tenants.getList();
  } 
  public static List<User> getFullListUsers() throws TenantException, KeystoneException, UsersException{
    Keystone keystone = getKeystoneAdminClient();    
    Users users = keystone.users().list().execute();
    if (users == null)
      throw new UsersException("Not found any teant");
    return users.getList();
  } 
  
  
  public static List<Role> getFullListRole() throws KeystoneException, RolessException{
    Keystone keystone = getKeystoneAdminClient();   
    Roles roles = keystone.roles().list().execute();
    if(roles == null ){
      throw new RolessException("Not found any teant");
    }
    return roles.getList();
   }
  
  public static String getRoleIdByName(String roleName) throws KeystoneException, RolessException {
    List<Role> listRoles = getFullListRole();
    for(Role role:listRoles){
      if(roleName.equals(role.getName())){
        return role.getId();
      }
    }
    return null;
  }
  public static String getTenantIdByName(String tenantName) throws TenantException, KeystoneException{
    List<Tenant> listTenant = getFullListTenants();
    for(Tenant tenant:listTenant){
      if(tenantName.equals(tenant.getName())){
        return tenant.getId();
      }
    }
    return null;
  }
  
  public static String getUserIdByName(String userName) throws TenantException, KeystoneException, UsersException{
    List<User> listUser = getFullListUsers();
    for(User user:listUser){
      if(userName.equalsIgnoreCase(user.getName())){
        return user.getId();
      }
    }
    return null;
  }
   
  public static Tenant getFirstTenant(String tenantName,String username, String password) throws TenantException, KeystoneException{
    return getListTenants(tenantName, username, password).getList().get(0);
  }
  
  
  

}
