package com.booking.services.bookings.models;


import lombok.ToString;

import java.util.UUID;

/**
 * @author William Arustamyan
 */

@ToString
public abstract class DeviceUserCommand {

  public final UUID deviceId;

  public final UUID userId;

  public DeviceUserCommand(final UUID deviceId, final UUID userId) {
    this.deviceId = deviceId;
    this.userId = userId;
  }
}
