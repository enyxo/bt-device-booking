package com.booking.endpoints.bookinghistory;


import com.booking.services.bookings.bookinghistory.BookingHistoryService;
import com.booking.services.bookings.models.BookingHistoryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author William Arustamyan
 */

@RestController
@RequestMapping("/devices")
public class BookingHistoryEndpoint {

  private static final Logger logger = LoggerFactory.getLogger(BookingHistoryEndpoint.class);
  private final BookingHistoryService bookingHistoryService;

  public BookingHistoryEndpoint(final BookingHistoryService bookingHistoryService) {
    this.bookingHistoryService = bookingHistoryService;
  }

  @GetMapping("/{id}/bookings")
  public BookingHistoryResponse deviceBookings(@PathVariable final UUID id) {
    logger.info("Start retrieving booking history for device : {}", id);
    return this.bookingHistoryService.history(id);
  }
}
