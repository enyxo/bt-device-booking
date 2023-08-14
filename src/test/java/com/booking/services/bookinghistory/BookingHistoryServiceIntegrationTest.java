package com.booking.services.bookinghistory;


import com.booking.api.exceptions.ResourceNotFoundException;
import com.booking.services.bookings.BookingService;
import com.booking.services.bookings.bookinghistory.BookingHistoryService;
import com.booking.services.bookings.models.Available;
import com.booking.services.bookings.models.BookCommand;
import com.booking.services.bookings.models.BookingHistoryResponse;
import com.booking.services.bookings.models.BookingResponse;
import com.booking.services.bookings.models.UnbookCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author William Arustamyan
 */


@SpringBootTest
public class BookingHistoryServiceIntegrationTest {

  @Autowired
  private BookingHistoryService bookingHistoryService;

  @Autowired
  private BookingService bookingService;

  @Test
  public void test_query_device_booking_history() {
    final UUID userId = UUID.fromString("a35fdec9-5d13-4bd0-be2a-e17b7c4e3417");
    final UUID deviceId = UUID.fromString("1233b0e3-0758-44a3-a343-6827c54352c6");

    final BookingResponse response = this.bookingService.book(new BookCommand(deviceId, userId));
    assertNotNull(response);
    assertNotNull(response.user());
    assertNotNull(response.device());
    assertNotNull(response.bookTime());
    assertNull((response.unbookTime()));

    final BookingHistoryResponse history_1 = this.bookingHistoryService.history(deviceId);
    assertNotNull(history_1);
    assertNotNull(history_1.device());
    assertEquals(deviceId, history_1.device().id);
    assertNotNull(history_1.histories());
    assertFalse(history_1.histories().isEmpty());
    assertNotNull(history_1.available());
    assertEquals(Available.NO, history_1.available());
    assertEquals(1, history_1.histories().size());

    final BookingResponse unbook = this.bookingService.unbook(new UnbookCommand(deviceId, userId));
    assertNotNull(unbook);
    assertNotNull(unbook.user());
    assertNotNull(unbook.device());
    assertNotNull(unbook.bookTime());
    assertNotNull(unbook.unbookTime());

    final BookingHistoryResponse history_2 = this.bookingHistoryService.history(deviceId);
    assertNotNull(history_2);
    assertNotNull(history_2.device());
    assertEquals(deviceId, history_2.device().id);
    assertNotNull(history_2.histories());
    assertFalse(history_2.histories().isEmpty());
    assertNotNull(history_2.available());
    assertEquals(Available.YES, history_2.available());
    assertEquals(1, history_2.histories().size());

    this.bookingService.book(new BookCommand(deviceId, userId));

    final BookingHistoryResponse history_3 = this.bookingHistoryService.history(deviceId);
    assertNotNull(history_3);
    assertNotNull(history_3.device());
    assertEquals(deviceId, history_3.device().id);
    assertNotNull(history_3.histories());
    assertFalse(history_3.histories().isEmpty());
    assertNotNull(history_3.available());
    assertEquals(Available.NO, history_3.available());
    assertEquals(2, history_3.histories().size());

  }

  @Test
  public void test_query_not_existing_device_booking_history() {
    assertThrows(ResourceNotFoundException.class, () -> this.bookingHistoryService.history(UUID.randomUUID()));
  }

  @Test
  public void test_query_not_booked_device_history() {
    final UUID deviceId = UUID.fromString("cf5bb79d-d9c3-4447-b969-56434f0f305e");

    final BookingHistoryResponse response = this.bookingHistoryService.history(deviceId);
    assertNotNull(response);
    assertNotNull(response.device());
    assertNotNull(response.histories());
    assertTrue(response.histories().isEmpty());
    assertNotNull(response.available());
    assertEquals(Available.YES, response.available());
  }
}
