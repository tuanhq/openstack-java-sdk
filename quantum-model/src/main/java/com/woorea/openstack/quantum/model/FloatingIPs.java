package com.woorea.openstack.quantum.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

public class FloatingIPs implements Iterable<FloatingIP>, Serializable{
  
  @JsonProperty("floatingips")
  private List<FloatingIP> list;

  /**
   * @return the list
   */
  public List<FloatingIP> getList() {
    return list;
  }


  /**
   * @param list the list to set
   */
  public void setList(List<FloatingIP> list) {
    this.list = list;
  }


  public String toString() {
    return "Networks [list=" + list + "]";
  }


  public Iterator<FloatingIP> iterator() {
    return list.iterator();
  }
  
}