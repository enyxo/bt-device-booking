package com.booking.services.bookings.unbook;


import com.booking.api.exceptions.ResourceNotFoundException;
import com.booking.persistence.entities.booking.Booking;
import com.booking.persistence.repositories.booking.BookingRepository;
import com.booking.services.bookings.mapper.BookingMapper;
import com.booking.services.bookings.models.BookingResponse;
import com.booking.services.bookings.models.UnbookCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author William Arustamyan
 */

@Service
@Transactional
public class BtUnbookService implements UnbookService {

  private static final Logger logger = LoggerFactory.getLogger(BtUnbookService.class);

  private final BookingMapper mapper;
  private final BookingRepository repository;

  public BtUnbookService(final BookingMapper mapper, final BookingRepository repository) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public BookingResponse unbook(final UnbookCommand command) {
    logger.debug("Doing query for bookings with parameters : {}", command);
    return this.repository.findBooking(command.userId, command.deviceId)
      .map(Booking::unbook)
      .map(this.repository::save)
      .map(this.mapper)
      .orElseThrow(
        () -> new ResourceNotFoundException(
          String.format("No booking found with user id: {%s} and device id: {%s}", command.userId, command.deviceId)
        )
      );
  }
}
