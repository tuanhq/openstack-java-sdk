package org.openstack.client.imagestore;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.openstack.client.OpenstackException;
import org.openstack.model.image.Image;
import org.openstack.model.image.ImageList;
import org.openstack.model.image.ImageUploadResponse;
import org.openstack.utils.Io;

import com.sun.jersey.api.client.WebResource.Builder;

public class ImagesResource extends GlanceResourceBase {

    public ImagesRepresentation list() {
        return list(true);
    }

    public ImagesRepresentation list(boolean details) {
        Builder imagesResource = details ? resource("detail") : resource();

        ImageList imageList = imagesResource.get(ImageList.class);
        return new ImagesRepresentation(client, imageList);
    }

    public ImageResource image(String imageId) {
        return buildChildResource(imageId, ImageResource.class);
    }

    public Image addImage(File imageFile, Image properties) throws IOException, OpenstackException {
        FileInputStream fis = new FileInputStream(imageFile);
        try {
            properties.setSize(imageFile.length());

            return addImage(fis, properties);
        } finally {
            Io.safeClose(fis);
        }
    }

    public Image addImage(InputStream imageStream, Image properties) throws OpenstackException, IOException {
        Builder builder = resource();

        builder = GlanceHeaderUtils.setHeaders(builder, properties);

        ImageUploadResponse response = builder.post(ImageUploadResponse.class, imageStream);
        Image image = response.getImage();
        return image;
    }

}
