package com.woorea.openstack.examples.hpcloud;


import com.woorea.openstack.examples.ExamplesConfiguration;
import com.woorea.openstack.keystone.Keystone;
import com.woorea.openstack.keystone.model.Access;

public class KeystoneAuthentication {

  private static final String KEYSTONE_AUTH_URL = "http://172.27.4.101:5000/v2.0";

  /**
   * @param args
   */
  public static void main(String[] args) {
    Keystone keystone = new Keystone(KEYSTONE_AUTH_URL);

    // access with unscoped token
    Access access = keystone
        .tokens()
        .authenticate()
        .withUsernamePassword(ExamplesConfiguration.KEYSTONE_USERNAME, ExamplesConfiguration.KEYSTONE_PASSWORD)
        .execute();

    System.out.println(access);

  }

}
