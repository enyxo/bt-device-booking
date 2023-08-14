package com.booking.services.remotes.models;


import lombok.Data;

/**
 * @author William Arustamyan
 */


@Data
public final class DeviceApiSpecResponse {

  private String technology;

  private String bands2g;

  private String bands3g;

  private String bands4g;
}
