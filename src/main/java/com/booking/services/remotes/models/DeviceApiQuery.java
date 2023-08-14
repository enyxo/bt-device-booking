package com.booking.services.remotes.models;


/**
 * @author William Arustamyan
 */


public class DeviceApiQuery {

  public final String brandName;

  public final String modelName;

  public DeviceApiQuery(final String brandName, final String modelName) {
    this.brandName = brandName;
    this.modelName = modelName;
  }
}
