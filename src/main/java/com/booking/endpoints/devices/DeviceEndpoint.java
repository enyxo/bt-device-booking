package com.booking.endpoints.devices;


import com.booking.services.devices.DeviceService;
import com.booking.services.devices.models.DeviceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author William Arustamyan
 */

@RestController
@RequestMapping("/devices")
public class DeviceEndpoint {

  private static final Logger logger = LoggerFactory.getLogger(DeviceEndpoint.class);

  private final DeviceService deviceService;

  public DeviceEndpoint(final DeviceService deviceService) {
    this.deviceService = deviceService;
  }


  @GetMapping("/{id}")
  public DeviceResponse device(@PathVariable final UUID id) {
    logger.info("Starting find device with id : {}", id);
    return this.deviceService.get(id);
  }

  @GetMapping
  public List<DeviceResponse> devices() {
    logger.info("Starting query all devices");
    return this.deviceService.items();
  }
}
