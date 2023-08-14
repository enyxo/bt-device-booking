package com.booking.services.bookings.mapper;


import com.booking.persistence.entities.booking.Booking;
import com.booking.persistence.entities.device.Device;
import com.booking.services.bookings.models.Available;
import com.booking.services.bookings.models.BookHistory;
import com.booking.services.bookings.models.BookingHistoryResponse;
import com.booking.services.devices.mapper.DeviceMapper;
import com.booking.services.devices.models.DeviceResponse;
import com.booking.services.users.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author William Arustamyan
 */

@Component
public final class DeviceBookingMapper implements BiFunction<Device, List<Booking>, BookingHistoryResponse> {

  private final UserMapper userMapper;
  private final DeviceMapper deviceMapper;

  public DeviceBookingMapper(final UserMapper userMapper, final DeviceMapper deviceMapper) {
    this.deviceMapper = deviceMapper;
    this.userMapper = userMapper;
  }

  @Override
  public BookingHistoryResponse apply(final Device device, final List<Booking> bookings) {
    final DeviceResponse deviceResponse = this.deviceMapper.apply(device);
    if (bookings.isEmpty()) {
      return new BookingHistoryResponse(deviceResponse, Available.YES, Collections.emptyList());
    }
    final HistoryAvailability ha = this.toHistoryAvailability(bookings);
    return new BookingHistoryResponse(deviceResponse, Available.fromLogical(ha.available), ha.histories);
  }

  //not using filter for optimizations
  private HistoryAvailability toHistoryAvailability(final List<Booking> bookings) {
    boolean available = true;
    final List<BookHistory> histories = new ArrayList<>(bookings.size());
    for (final Booking booking : bookings) {
      histories.add(this.toHistory(booking));
      if (booking.getUnbookTime() == null) {
        available = false;
      }
    }
    return new HistoryAvailability(available, histories);
  }

  private BookHistory toHistory(final Booking source) {
    return new BookHistory(
      this.userMapper.apply(source.getUser()), source.getBookTime(), source.getUnbookTime()
    );
  }

  private record HistoryAvailability(Boolean available, List<BookHistory> histories) {
  }


}
