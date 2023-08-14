package com.booking.services.bookings.models;


import java.util.UUID;

/**
 * @author William Arustamyan
 */


public final class BookCommand extends DeviceUserCommand {

  public BookCommand(final UUID deviceId, final UUID userId) {
    super(deviceId, userId);
  }

}

