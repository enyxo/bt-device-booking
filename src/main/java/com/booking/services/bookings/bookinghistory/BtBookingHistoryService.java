package com.booking.services.bookings.bookinghistory;


import com.booking.api.exceptions.ResourceNotFoundException;
import com.booking.persistence.repositories.booking.BookingRepository;
import com.booking.persistence.repositories.device.DeviceRepository;
import com.booking.services.bookings.mapper.DeviceBookingMapper;
import com.booking.services.bookings.models.BookingHistoryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author William Arustamyan
 */

@Service
@Transactional(readOnly = true)
public class BtBookingHistoryService implements BookingHistoryService {

  private static final Logger logger = LoggerFactory.getLogger(BtBookingHistoryService.class);

  private final BookingRepository repository;
  private final DeviceRepository deviceRepository;
  private final DeviceBookingMapper deviceBookingMapper;

  public BtBookingHistoryService(final BookingRepository repository,
                                 final DeviceRepository deviceRepository,
                                 final DeviceBookingMapper deviceBookingMapper) {
    this.deviceBookingMapper = deviceBookingMapper;
    this.deviceRepository = deviceRepository;
    this.repository = repository;
  }

  @Override
  public BookingHistoryResponse history(final UUID deviceId) {
    logger.debug("Start query device with id : {}", deviceId);
    return this.deviceRepository.findByIdAndDeletedIsNull(deviceId)
      .map(dvc -> this.deviceBookingMapper.apply(dvc, this.repository.deviceBookings(deviceId)))
      .orElseThrow(() -> new ResourceNotFoundException(String.format("Device with id {%s} not found ...", deviceId)));
  }
}
