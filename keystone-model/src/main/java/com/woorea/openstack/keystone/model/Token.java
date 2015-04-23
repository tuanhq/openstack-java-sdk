package com.woorea.openstack.keystone.model;

import java.util.Calendar;
import java.util.List;

public final class Token {

	private String id;

	private Calendar issued_at;

	private Calendar expires;

	private Tenant tenant;
	private List<String>audit_ids;
   
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the issued_at
	 */
	public Calendar getIssued_at() {
	  return issued_at;
	}

	/**
	 * @return the expires
	 */
	public Calendar getExpires() {
		return expires;
	}

	/**
	 * @return the tenant
	 */
	public Tenant getTenant() {
		return tenant;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Token [id=" + id + ", Issued_at=" + issued_at + ", expires=" + expires + ", tenant="
          + tenant + "]";
	}

  public List<String> getAudit_ids() {
    return audit_ids;
  }

  public void setAudit_ids(List<String> audit_ids) {
    this.audit_ids = audit_ids;
  }

}