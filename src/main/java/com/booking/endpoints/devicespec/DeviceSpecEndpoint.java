package com.booking.endpoints.devicespec;


import com.booking.services.devices.DeviceSpecService;
import com.booking.services.devices.models.DeviceSpecResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author William Arustamyan
 */


@RestController
@RequestMapping("/devices/spec")
public class DeviceSpecEndpoint {

  private static final Logger logger = LoggerFactory.getLogger(DeviceSpecEndpoint.class);

  private final DeviceSpecService specService;

  public DeviceSpecEndpoint(final DeviceSpecService specService) {
    this.specService = specService;
  }

  @GetMapping("/{id}")
  public DeviceSpecResponse deviceSpecification(@PathVariable final UUID id) {
    logger.info("Start query device specifications with device id : {}", id);
    return this.specService.query(id);
  }

  @GetMapping
  public DeviceSpecResponse deviceSpecification(@RequestParam(name = "brandName") final String brandName,
                                                @RequestParam(name = "modelName") final String modelName) {
    logger.info("Start query device specifications with brand name : {} and model name : {}", brandName, modelName);
    return this.specService.query(brandName, modelName);
  }


}
