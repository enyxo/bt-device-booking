package com.booking.services.bookings.unbook;


import com.booking.services.bookings.models.BookingResponse;
import com.booking.services.bookings.models.UnbookCommand;

/**
 * @author William Arustamyan
 */


public interface UnbookService {

  BookingResponse unbook(UnbookCommand command);
}
