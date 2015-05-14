package com.woorea.openstack.examples.network;

import com.woorea.openstack.base.client.OpenStackSimpleTokenProvider;
import com.woorea.openstack.examples.ExamplesConfiguration;
import com.woorea.openstack.keystone.Keystone;
import com.woorea.openstack.keystone.model.Access;
import com.woorea.openstack.keystone.model.Tenants;
import com.woorea.openstack.keystone.model.authentication.TokenAuthentication;
import com.woorea.openstack.keystone.model.authentication.UsernamePassword;
import com.woorea.openstack.keystone.utils.KeystoneUtils;
import com.woorea.openstack.quantum.Quantum;
import com.woorea.openstack.quantum.model.Router;
import com.woorea.openstack.quantum.model.RouterForCreate;

public class QuantumListNetworks {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
			Quantum quantum = new Quantum(KeystoneUtils.findEndpointURL(access.getServiceCatalog(), "network",	null, "public").concat("/").concat("v2.0"));
			
			quantum.setTokenProvider(new OpenStackSimpleTokenProvider(access.getToken().getId()));
			
			//example create network
//			NetworkForCreate networkForCreate = new NetworkForCreate();
//			networkForCreate.setName("tuanhq");
//		//	networkForCreate.setAdminStateUp(true);			
//		//	networkForCreate.setProviderPhysicalNetwork("external");
//		//	networkForCreate.setProviderNetworkType("flat");
//			networkForCreate.setTenantId(tenants.getList().get(0).getId());
//			quantum.networks().create(networkForCreate).execute();
			
			

//			Networks networks = quantum.networks().list().execute();
//			for (Network network : networks) {
//				System.out.println(network);
//			}
			
			//example create subnet
//			SubnetForCreate subnetForCreate = new SubnetForCreate();
//			subnetForCreate.setCidr("192.168.8.0/24");			
//			subnetForCreate.setIpVersion(4);
//      subnetForCreate.setNetworkId("30971863-97e7-4838-ae76-d7f0fa174926");
//			quantum.subnets().create(subnetForCreate).execute();
			
			//example create router
			
			RouterForCreate routerForCreate = new RouterForCreate();
			routerForCreate.setAdminStateUp(true);
			routerForCreate.setName("tuanhq11");
			routerForCreate.setExternalGateway(new RouterForCreate.ExternalGateway("03849923-e8e4-4d7f-801d-07656ae79f40"));
			Router route = quantum.routers().create(routerForCreate).execute();
			System.out.println();
			
			//example delete router
//			quantum.routers().delete("680d8995-ceba-45c4-9fc6-2ceb4eb947a4").execute();
			
			 //1210a775-a37f-43a0-a742-7678e3687a0c
			
			 
		} else {
			System.out.println("No tenants found!");
		}
	}
}
