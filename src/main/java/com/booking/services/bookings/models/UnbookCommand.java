package com.booking.services.bookings.models;


import java.util.UUID;

/**
 * @author William Arustamyan
 */


public final class UnbookCommand extends DeviceUserCommand {

  public UnbookCommand(final UUID deviceId, final UUID userId) {
    super(deviceId, userId);
  }
}
