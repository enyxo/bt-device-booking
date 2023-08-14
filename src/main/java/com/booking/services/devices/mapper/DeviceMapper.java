package com.booking.services.devices.mapper;


import com.booking.persistence.entities.device.Device;
import com.booking.services.devices.models.DeviceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @author William Arustamyan
 */

@Component
public final class DeviceMapper implements Function<Device, DeviceResponse> {

  private static final Logger logger = LoggerFactory.getLogger(DeviceMapper.class);

  @Override
  public DeviceResponse apply(final Device source) {
    logger.debug("Starting device mapping for instance : {}", source);
    return new DeviceResponse(source.getId(), source.getBrandName(), source.getModelName());
  }
}
