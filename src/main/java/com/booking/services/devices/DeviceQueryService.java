package com.booking.services.devices;


import com.booking.api.exceptions.ResourceNotFoundException;
import com.booking.persistence.entities.device.Device;
import com.booking.persistence.repositories.device.DeviceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author William Arustamyan
 */

@Service
@Transactional(readOnly = true)
public class DeviceQueryService {

  private final DeviceRepository repository;

  public DeviceQueryService(final DeviceRepository repository) {
    this.repository = repository;
  }

  public Device findDevice(final String brandName, final String modelName) {
    return this.repository.withBrandAndModel(brandName, modelName)
      .orElseThrow(
        () -> new ResourceNotFoundException(String.format("Device with brand name : {%s} and model name {%s} not supported", brandName, modelName))
      );
  }

  public Device findDevice(final UUID id) {
    return this.repository.withId(id)
      .orElseThrow(
        () -> new ResourceNotFoundException(String.format("Device with id  : {%s} not found", id))
      );
  }

}
