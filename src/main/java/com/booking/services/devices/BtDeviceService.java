package com.booking.services.devices;


import com.booking.api.exceptions.ResourceNotFoundException;
import com.booking.persistence.repositories.device.DeviceRepository;
import com.booking.services.devices.mapper.DeviceMapper;
import com.booking.services.devices.models.DeviceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * @author William Arustamyan
 */


@Service
@Transactional(readOnly = true)
public class BtDeviceService implements DeviceService {

  private static final Logger logger = LoggerFactory.getLogger(BtDeviceService.class);

  private final DeviceMapper mapper;
  private final DeviceRepository repository;

  public BtDeviceService(final DeviceMapper mapper,
                         final DeviceRepository repository) {
    this.mapper = mapper;
    this.repository = repository;
  }

  @Override
  public DeviceResponse get(final UUID id) {
    logger.debug("Doing device query with id : {}", id);
    return this.repository.withId(id)
      .map(this.mapper)
      .orElseThrow(() -> new ResourceNotFoundException(String.format("Device with id %s not found ...", id)));
  }

  @Override
  public List<DeviceResponse> items() {
    logger.debug("Starting devices query");
    return this.repository.findAll().stream()
      .map(this.mapper)
      .collect(Collectors.toList());
  }
}
