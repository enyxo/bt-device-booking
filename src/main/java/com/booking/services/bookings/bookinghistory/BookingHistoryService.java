package com.booking.services.bookings.bookinghistory;


import com.booking.services.bookings.models.BookingHistoryResponse;

import java.util.UUID;

/**
 * @author William Arustamyan
 */


public interface BookingHistoryService {

  BookingHistoryResponse history(UUID deviceId);

}
