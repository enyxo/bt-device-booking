package com.booking.mappers;


import com.booking.persistence.entities.booking.Booking;
import com.booking.persistence.entities.device.Device;
import com.booking.persistence.entities.user.User;
import com.booking.services.bookings.mapper.DeviceBookingMapper;
import com.booking.services.bookings.models.BookingHistoryResponse;
import com.booking.services.devices.mapper.DeviceMapper;
import com.booking.services.users.mapper.UserMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author William Arustamyan
 */


public class DeviceBookingMapperTest {


  private final DeviceBookingMapper mapper = new DeviceBookingMapper(new UserMapper(), new DeviceMapper());

  @Test
  public void test_device_bookings_mapper() {

    final User user_1 = Helper.makeUser();
    final Device device = Helper.makeDevice();
    final User user_2 = Helper.makeUser();

    final Booking booking_1 = Helper.makeBooking(user_1, device);
    final Booking booking_2 = Helper.makeBooking(user_2, device);

    final BookingHistoryResponse response = mapper.apply(device, List.of(booking_1, booking_2));

    assertNotNull(response);
    assertNotNull(response.device());
    assertNotNull(response.available());
    assertNotNull(response.histories());

    assertEquals(device.getId(), response.device().id);
    assertEquals(device.getBrandName(), response.device().brandName);
    assertEquals(device.getModelName(), response.device().modelName);

    assertEquals(2, response.histories().size());
    assertTrue(response.histories().stream().anyMatch(bh -> bh.user().id().equals(user_1.getId())));
    assertTrue(response.histories().stream().anyMatch(bh -> bh.user().id().equals(user_2.getId())));

  }
}
