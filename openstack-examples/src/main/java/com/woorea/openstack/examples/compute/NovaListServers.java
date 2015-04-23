package com.woorea.openstack.examples.compute;


import com.woorea.openstack.examples.ExamplesConfiguration;
import com.woorea.openstack.keystone.Keystone;
import com.woorea.openstack.keystone.model.Access;
import com.woorea.openstack.keystone.model.authentication.UsernamePassword;
import com.woorea.openstack.nova.Nova;
import com.woorea.openstack.nova.api.extensions.FloatingIpPoll;
import com.woorea.openstack.nova.model.Server;
import com.woorea.openstack.nova.model.Servers;

public class NovaListServers {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	  Keystone keystone = new Keystone(ExamplesConfiguration.KEYSTONE_AUTH_URL);
		Access access = keystone.tokens().authenticate(new UsernamePassword(ExamplesConfiguration.KEYSTONE_USERNAME, ExamplesConfiguration.KEYSTONE_PASSWORD)).withTenantName("demo").execute();
		
		//use the token in the following requests
		keystone.token(access.getToken().getId());
			
		//NovaClient novaClient = new NovaClient(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), "compute", null, "public"), access.getToken().getId());
		Nova novaClient = new Nova(ExamplesConfiguration.NOVA_ENDPOINT.concat("/").concat(access.getToken().getTenant().getId()));
		novaClient.token(access.getToken().getId());
		//novaClient.enableLogging(Logger.getLogger("nova"), 100 * 1024);
		//novaClient.floatingIps().allocate(new FloatingIpPoll("ext-net")).execute();
		
		novaClient.servers().associateFloatingIp("5c2abab5-f6e7-4904-8fc9-2513cb4de9f8", "192.168.100.16").execute();
		
		
		
		
		
  
		
		
//		Servers servers = novaClient.servers().list(true).execute();
//		for(Server server : servers) {
//			System.out.println(server);
//		}
		
	}

}
