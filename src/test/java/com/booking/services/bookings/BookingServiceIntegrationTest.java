package com.booking.services.bookings;


import com.booking.api.exceptions.CommonApplicationException;
import com.booking.services.bookings.models.BookCommand;
import com.booking.services.bookings.models.BookingResponse;
import com.booking.services.bookings.models.UnbookCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author William Arustamyan
 */

@SpringBootTest
public class BookingServiceIntegrationTest {

  @Autowired
  private BookingService bookingService;

  @Test
  public void test_successfully_book_device() {
    final UUID userId = UUID.fromString("d36cb4d1-7603-435b-bf54-313ae4397489");
    final UUID deviceId = UUID.fromString("2c0544ed-0c27-4687-9177-9ddb7c5aac99");
    final BookingResponse response = this.bookingService.book(new BookCommand(deviceId, userId));

    assertNotNull(response);
    assertNotNull(response.user());
    assertNotNull(response.device());
    assertNotNull(response.bookTime());
    assertNull(response.unbookTime());
  }

  @Test
  public void test_successfully_unbook_device() {
    final UUID userId = UUID.fromString("07bf8b1e-1c6d-4886-8be0-0bea5af0aa35");
    final UUID deviceId = UUID.fromString("aebfc226-ff0a-480f-af00-197e43955da6");

    final BookingResponse bookResponse = this.bookingService.book(new BookCommand(deviceId, userId));
    assertNotNull(bookResponse);
    assertNotNull(bookResponse.user());
    assertNotNull(bookResponse.device());
    assertNotNull(bookResponse.bookTime());
    assertNull(bookResponse.unbookTime());

    final BookingResponse unbookResponse = this.bookingService.unbook(new UnbookCommand(deviceId, userId));
    assertNotNull(unbookResponse);
    assertNotNull(unbookResponse.user());
    assertNotNull(unbookResponse.device());
    assertNotNull(unbookResponse.bookTime());
    assertNotNull(unbookResponse.unbookTime());
  }

  @Test
  public void test_book_already_booked_device() {
    final UUID userId = UUID.fromString("c0d84d66-d091-4d97-8a07-434dd8af33ec");
    final UUID deviceId = UUID.fromString("d3ec5067-9c4e-4611-bdfd-282139bd098b");
    final BookCommand command = new BookCommand(deviceId, userId);

    final BookingResponse book = this.bookingService.book(command);

    assertNotNull(book);
    assertThrows(CommonApplicationException.class, () -> this.bookingService.book(command));
  }

  @Test
  public void test_unbook_not_booked_device() {
    final UUID userId = UUID.fromString("0f8742ed-d456-46af-8022-e4b6547f6a8b");
    final UUID deviceId = UUID.fromString("472bbded-7382-4d12-af7f-537b495c38d9");
    assertThrows(CommonApplicationException.class, () -> this.bookingService.unbook(new UnbookCommand(deviceId, userId)));
  }
}
