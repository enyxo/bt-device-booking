package com.booking.mappers;


import com.booking.persistence.entities.booking.Booking;
import com.booking.persistence.entities.device.Device;
import com.booking.persistence.entities.user.User;
import com.booking.services.bookings.mapper.BookingMapper;
import com.booking.services.bookings.models.BookingResponse;
import com.booking.services.devices.mapper.DeviceMapper;
import com.booking.services.users.mapper.UserMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author William Arustamyan
 */


public class BookingMapperTest {


  private final BookingMapper mapper = new BookingMapper(new UserMapper(), new DeviceMapper());


  @Test
  public void test_booking_mapper() {
    final User user = Helper.makeUser();
    final Device device = Helper.makeDevice();

    final Booking source = Helper.makeBooking(user, device);

    final BookingResponse response = mapper.apply(source);

    assertNotNull(response);
    assertNotNull(response.user());
    assertNotNull(response.device());
    assertNotNull(response.user());
    assertNotNull(response.bookTime());
    assertNotNull(response.unbookTime());

    assertEquals(source.getUser().getId(), response.user().id());
    assertEquals(source.getUser().getName(), response.user().name());
    assertEquals(source.getDevice().getId(), response.device().id);
    assertEquals(source.getDevice().getBrandName(), response.device().brandName);
    assertEquals(source.getDevice().getModelName(), response.device().modelName);
    //other fields tested in device and user mappers
    assertEquals(source.getBookTime(), response.bookTime());
    assertEquals(source.getUnbookTime(), response.unbookTime());
  }
}
