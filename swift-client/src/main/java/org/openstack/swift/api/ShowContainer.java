package org.openstack.swift.api;

import javax.xml.ws.Response;

import org.openstack.base.client.OpenStackClient;
import org.openstack.base.client.OpenStackRequest;
import org.openstack.swift.SwiftCommand;

public class ShowContainer implements SwiftCommand<Response>{

	private String containerName;
	
	public ShowContainer(String containerName) {
		this.containerName = containerName;
	}
	
	@Override
	public OpenStackRequest execute(OpenStackClient client) {
//		return target.path(containerName).request(MediaType.APPLICATION_JSON).head();
		return null;
	}

}
