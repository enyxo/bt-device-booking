package com.booking.services.bookings;


import com.booking.api.exceptions.ResourceNotFoundException;
import com.booking.services.bookings.book.BookService;
import com.booking.services.bookings.models.BookCommand;
import com.booking.services.bookings.models.BookingResponse;
import com.booking.services.bookings.models.UnbookCommand;
import com.booking.services.bookings.unbook.UnbookService;
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
public class UnbookServiceIntegrationTest {

  private static final UUID deviceId = UUID.fromString("6cdaa8d2-3e2c-473d-b2cf-e9ef6a16729f");
  private static final UUID userId = UUID.fromString("acf219ae-c1d4-490c-b27a-663d8cf37abc");

  @Autowired
  private UnbookService btUnbookService;

  @Autowired
  private BookService btBookService;

  @Test
  public void test_successfully_unbook_device() {
    final BookingResponse response = this.btBookService.book(new BookCommand(deviceId, userId));
    assertNotNull(response);
    assertNotNull(response.user());
    assertNotNull(response.device());
    assertNotNull(response.bookTime());
    assertNull((response.unbookTime()));

    final BookingResponse unbook = this.btUnbookService.unbook(new UnbookCommand(deviceId, userId));
    assertNotNull(unbook);
    assertNotNull(unbook.user());
    assertNotNull(unbook.device());
    assertNotNull(unbook.bookTime());
    assertNotNull((unbook.unbookTime()));
  }


  @Test
  public void test_unbook_device_with_null_attributes() {
    assertThrows(ResourceNotFoundException.class, () -> this.btUnbookService.unbook(new UnbookCommand(null, null)));
  }

  @Test
  public void test_unbook_device_with_non_existing_user() {
    assertThrows(ResourceNotFoundException.class, () -> this.btUnbookService.unbook(new UnbookCommand(deviceId, UUID.randomUUID())));
  }

  @Test
  public void test_unbook_device_with_non_existing_device() {
    assertThrows(ResourceNotFoundException.class, () -> this.btUnbookService.unbook(new UnbookCommand(UUID.randomUUID(), userId)));
  }

}
