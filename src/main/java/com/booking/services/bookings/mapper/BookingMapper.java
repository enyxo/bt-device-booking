package com.booking.services.bookings.mapper;


import com.booking.persistence.entities.booking.Booking;
import com.booking.services.bookings.models.BookingResponse;
import com.booking.services.devices.mapper.DeviceMapper;
import com.booking.services.users.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @author William Arustamyan
 */

@Component
public final class BookingMapper implements Function<Booking, BookingResponse> {

  private static final Logger logger = LoggerFactory.getLogger(BookingMapper.class);

  private final UserMapper userMapper;
  private final DeviceMapper deviceMapper;

  public BookingMapper(final UserMapper userMapper, final DeviceMapper deviceMapper) {
    this.userMapper = userMapper;
    this.deviceMapper = deviceMapper;
  }

  @Override
  public BookingResponse apply(final Booking source) {
    logger.debug("Starts booking mapping for instance : {}", source);
    return new BookingResponse(
      this.userMapper.apply(source.getUser()),
      this.deviceMapper.apply(source.getDevice()),
      source.getBookTime(),
      source.getUnbookTime()
    );
  }
}
