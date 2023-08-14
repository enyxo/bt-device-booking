package com.booking.services.bookings;


import com.booking.api.exceptions.CommonApplicationException;
import com.booking.api.exceptions.ResourceNotFoundException;
import com.booking.services.bookings.book.BookService;
import com.booking.services.bookings.models.BookCommand;
import com.booking.services.bookings.models.BookingResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author William Arustamyan
 */

@SpringBootTest
public class BookServiceIntegrationTest {

  private static final UUID deviceId = UUID.fromString("53f681cd-be9e-4d11-896e-dd31297357f2");
  private static final UUID userId = UUID.fromString("f4d1e11e-aec4-4e62-a938-35146c2993d5");

  @Autowired
  @Qualifier("btBookService")
  private BookService bookService;

  @Test
  public void test_successfully_book_device() {
    final BookingResponse response = this.bookService.book(new BookCommand(deviceId, userId));

    assertNotNull(response);
    assertNotNull(response.user());
    assertNotNull(response.device());
    assertNotNull(response.bookTime());
    assertNull((response.unbookTime()));

    assertEquals(deviceId, response.device().id);
    assertEquals(userId, response.user().id());
  }

  @Test
  public void test_book_device_with_null_attributes() {
    assertThrows(CommonApplicationException.class, () -> this.bookService.book(new BookCommand(null, null)));
  }

  @Test
  public void test_book_device_with_non_existing_user() {
    assertThrows(ResourceNotFoundException.class, () -> this.bookService.book(new BookCommand(deviceId, UUID.randomUUID())));
  }

  @Test
  public void test_book_device_with_non_existing_device() {
    assertThrows(ResourceNotFoundException.class, () -> this.bookService.book(new BookCommand(UUID.randomUUID(), userId)));
  }

}
