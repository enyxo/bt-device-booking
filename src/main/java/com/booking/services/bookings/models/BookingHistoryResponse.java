package com.booking.services.bookings.models;


import com.booking.services.devices.models.DeviceResponse;

import java.util.List;

/**
 * @author William Arustamyan
 */


public record BookingHistoryResponse(DeviceResponse device, Available available, List<BookHistory> histories) {

}
