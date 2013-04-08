package org.openstack.swift.api;

import org.openstack.base.client.OpenStackClient;
import org.openstack.base.client.OpenStackRequest;
import org.openstack.swift.SwiftCommand;
import org.openstack.swift.model.ObjectDownload;

public class DownloadObject implements SwiftCommand<ObjectDownload> {

	private String containerName;
	
	private String objectName;
	
	public DownloadObject(String containerName, String objectName) {
		this.containerName = containerName;
		this.objectName = objectName;
	}
	
	@Override
	public OpenStackRequest execute(OpenStackClient client) {
//		Response response = target.path(containerName).path(objectName).request(MediaType.APPLICATION_JSON).get();
//		ObjectDownload objectDownload = new ObjectDownload();
//		objectDownload.setInputStream((InputStream) response.getEntity());
//		return objectDownload;
		return null;
		
	}

}
