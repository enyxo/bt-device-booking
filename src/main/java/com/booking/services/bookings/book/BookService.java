package com.booking.services.bookings.book;


import com.booking.services.bookings.models.BookCommand;
import com.booking.services.bookings.models.BookingResponse;

/**
 * @author William Arustamyan
 */


public interface BookService {

  BookingResponse book(BookCommand command);

}
