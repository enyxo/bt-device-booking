package com.booking.services.bookings.models;


import com.booking.services.devices.models.DeviceResponse;
import com.booking.services.users.models.UserResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author William Arustamyan
 */


public record BookingResponse(UserResponse user, DeviceResponse device,
                              @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime bookTime,
                              @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime unbookTime) {

  public BookingResponse(final UserResponse user, final DeviceResponse device,
                         final LocalDateTime bookTime, final LocalDateTime unbookTime) {
    this.user = user;
    this.device = device;
    this.bookTime = bookTime;
    this.unbookTime = unbookTime;
  }
}
