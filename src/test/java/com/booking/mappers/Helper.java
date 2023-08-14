package com.booking.mappers;


import com.booking.persistence.entities.booking.Booking;
import com.booking.persistence.entities.device.Device;
import com.booking.persistence.entities.user.User;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author William Arustamyan
 */


public final class Helper {

  private Helper() {
    //static only
    throw new AssertionError("static only");
  }

  public static User makeUser() {
    final User user = new User();
    user.setId(UUID.randomUUID());
    user.setName(RandomStringUtils.randomAlphabetic(5));
    return user;
  }

  public static Device makeDevice() {
    final Device device = new Device();
    device.setId(UUID.randomUUID());
    device.setBrandName(RandomStringUtils.randomAlphabetic(5));
    device.setModelName(RandomStringUtils.randomAlphabetic(5));
    return device;
  }

  public static Booking makeBooking(final User user, final Device device) {
    final Booking source = new Booking(device, user);
    final LocalDateTime bookTime = LocalDateTime.now();
    final LocalDateTime unbookTime = LocalDateTime.now().plusSeconds(10);
    source.setBookTime(bookTime);
    source.setUnbookTime(unbookTime);
    return source;
  }
}
