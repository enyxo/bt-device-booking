package com.booking.services.bookings.book;


import com.booking.api.exceptions.ResourceNotFoundException;
import com.booking.persistence.entities.booking.Booking;
import com.booking.persistence.entities.device.Device;
import com.booking.persistence.entities.user.User;
import com.booking.persistence.repositories.booking.BookingRepository;
import com.booking.persistence.repositories.device.DeviceRepository;
import com.booking.persistence.repositories.user.UserRepository;
import com.booking.services.bookings.mapper.BookingMapper;
import com.booking.services.bookings.models.BookCommand;
import com.booking.services.bookings.models.BookingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * @author William Arustamyan
 */

@Service
@Transactional
public class BtBookService implements BookService {

  private static final Logger logger = LoggerFactory.getLogger(BtBookService.class);

  private final BookingMapper mapper;
  private final UserRepository userRepository;
  private final DeviceRepository deviceRepository;
  private final BookingRepository bookingRepository;

  public BtBookService(final BookingMapper mapper,
                       final UserRepository userRepository,
                       final DeviceRepository deviceRepository,
                       final BookingRepository bookingRepository) {
    this.userRepository = userRepository;
    this.deviceRepository = deviceRepository;
    this.bookingRepository = bookingRepository;
    this.mapper = mapper;
  }

  @Override
  @Transactional
  public BookingResponse book(final BookCommand command) {
    logger.debug("Accepted book command : {}", command);
    return Optional.of(this.booking(command.deviceId, command.userId))
      .map(this.bookingRepository::save)
      .map(this.mapper)
      .orElseThrow();
  }

  private Booking booking(final UUID deviceId, final UUID userId) {
    return new Booking(this.device(deviceId), this.user(userId));
  }

  private User user(final UUID id) {
    logger.debug("Try to find user with id : {}", id);
    return this.userRepository.withId(id)
      .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id {%s} not found ...", id)));
  }

  private Device device(final UUID id) {
    logger.debug("Try to find device with id : {}", id);
    return this.deviceRepository.withId(id)
      .orElseThrow(() -> new ResourceNotFoundException(String.format("Device with id {%s} not found ...", id)));
  }
}
