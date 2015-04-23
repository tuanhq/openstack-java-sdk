package com.woorea.openstack.examples.network;

import com.woorea.openstack.keystone.utils.KeystoneUtils;
import com.woorea.openstack.base.client.OpenStackSimpleTokenProvider;
import com.woorea.openstack.examples.ExamplesConfiguration;
import com.woorea.openstack.keystone.Keystone;
import com.woorea.openstack.keystone.model.Access;
import com.woorea.openstack.keystone.model.Tenants;
import com.woorea.openstack.keystone.model.authentication.TokenAuthentication;
import com.woorea.openstack.keystone.model.authentication.UsernamePassword;
import com.woorea.openstack.quantum.Quantum;
import com.woorea.openstack.quantum.model.Network;
import com.woorea.openstack.quantum.model.NetworkForCreate;
import com.woorea.openstack.quantum.model.Networks;
import com.woorea.openstack.quantum.model.SubnetForCreate;

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
			
//			SubnetForCreate subnetForCreate = new SubnetForCreate();
//			subnetForCreate.setCidr("192.168.8.0/24");			
//			subnetForCreate.setIpVersion(4);
//      subnetForCreate.setNetworkId("30971863-97e7-4838-ae76-d7f0fa174926");
//			quantum.subnets().create(subnetForCreate).execute();
			
			 quantum.
		} else {
			System.out.println("No tenants found!");
		}
	}
}
