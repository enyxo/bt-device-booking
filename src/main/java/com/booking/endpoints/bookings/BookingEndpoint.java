package com.booking.endpoints.bookings;


import com.booking.services.bookings.BookingService;
import com.booking.services.bookings.models.BookCommand;
import com.booking.services.bookings.models.BookingResponse;
import com.booking.services.bookings.models.UnbookCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author William Arustamyan
 */

@RestController
@RequestMapping("/bookings")
public class BookingEndpoint {

  private static final Logger logger = LoggerFactory.getLogger(BookingEndpoint.class);

  private final BookingService bookingService;

  public BookingEndpoint(final BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @PostMapping
  public BookingResponse book(@RequestBody final BookCommand command) {
    logger.info("Starting book operation with parameters : {}", command);
    return this.bookingService.book(command);
  }

  @PutMapping
  public BookingResponse unbook(@RequestBody final UnbookCommand command) {
    logger.info("Starting unbook operation with parameters : {}", command);
    return this.bookingService.unbook(command);
  }
}
