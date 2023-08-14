package com.booking.services.devices.models;


import lombok.Data;

import java.util.UUID;

/**
 * @author William Arustamyan
 */

@Data
public final class DeviceSpecResponse {

  private UUID id;

  private String brandName;

  private String modelName;

  private String technology;

  private String bands2g;

  private String bands3g;

  private String bands4g;

}
