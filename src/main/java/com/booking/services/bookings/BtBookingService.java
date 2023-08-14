package com.booking.services.bookings;


import com.booking.api.exceptions.CommonApplicationException;
import com.booking.persistence.repositories.booking.BookingRepository;
import com.booking.services.bookings.book.BookService;
import com.booking.services.bookings.models.BookCommand;
import com.booking.services.bookings.models.BookingResponse;
import com.booking.services.bookings.models.DeviceUserCommand;
import com.booking.services.bookings.models.UnbookCommand;
import com.booking.services.bookings.unbook.UnbookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author William Arustamyan
 */

@Service("bookingService")
public class BtBookingService implements BookingService {

  private static final Logger logger = LoggerFactory.getLogger(BtBookingService.class);

  private final BookService bookService;
  private final UnbookService unbookService;
  private final BookingRepository repository;

  public BtBookingService(@Qualifier("btBookService") final BookService bookService,
                          @Qualifier("btUnbookService") final UnbookService unbookService,
                          final BookingRepository repository) {
    this.repository = repository;
    this.bookService = bookService;
    this.unbookService = unbookService;
  }


  @Override
  public BookingResponse book(BookCommand command) {
    logger.debug("Starting book command validation : {}", command);
    this.doCommandValidation(command);

    logger.debug("Starting can book validation : {}", command);
    this.validateCanBook(command);

    return this.bookService.book(command);
  }

  @Override
  public BookingResponse unbook(final UnbookCommand command) {
    logger.debug("Starting unbook command validation : {}", command);
    this.doCommandValidation(command);

    logger.debug("Starting can unbook validation : {}", command);
    this.validateCanUnbook(command);

    return this.unbookService.unbook(command);
  }

  private void doCommandValidation(final DeviceUserCommand source) {
    if (source.userId == null || source.deviceId == null) {
      logger.warn("Invalid user id, device id request parameters : {}, {}", source.userId, source.deviceId);
      throw new CommonApplicationException(
        HttpStatus.BAD_REQUEST, "User id and device id should be valid UUID's ..."
      );
    }
  }

  private void validateCanBook(final BookCommand command) {
    if (this.repository.bookingExists(command.userId, command.deviceId)) {
      logger.warn("Device already booked : {}", command.deviceId);
      throw new CommonApplicationException(
        HttpStatus.CONFLICT,
        String.format("Device with id {%s} already booked by User {%s} ...", command.deviceId, command.userId)
      );
    }
  }

  private void validateCanUnbook(final UnbookCommand command) {
    if (!this.repository.bookingExists(command.userId, command.deviceId)) {
      logger.warn("Device is not booked : {}", command.deviceId);
      throw new CommonApplicationException(
        HttpStatus.CONFLICT,
        String.format("Device with id {%s} already returned or not exists ...", command.deviceId)
      );
    }
  }

}
