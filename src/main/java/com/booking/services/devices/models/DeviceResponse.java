package com.booking.services.devices.models;


import java.util.UUID;

/**
 * @author William Arustamyan
 */


public final class DeviceResponse {

  public final UUID id;

  public final String brandName;

  public final String modelName;


  public DeviceResponse(final UUID id, final String brandName, final String modelName) {
    this.brandName = brandName;
    this.modelName = modelName;
    this.id = id;
  }
}
