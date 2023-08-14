package com.booking.services.devices.mapper;


import com.booking.persistence.entities.device.Device;
import com.booking.services.devices.models.DeviceSpecResponse;
import com.booking.services.remotes.models.DeviceApiSpecResponse;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

/**
 * @author William Arustamyan
 */

@Component
public final class DeviceSpecMapper implements BiFunction<Device, DeviceApiSpecResponse, DeviceSpecResponse> {


  @Override
  public DeviceSpecResponse apply(final Device device, final DeviceApiSpecResponse apiResponse) {
    final DeviceSpecResponse response = new DeviceSpecResponse();
    response.setId(device.getId());
    response.setBrandName(device.getBrandName());
    response.setModelName(device.getModelName());
    response.setTechnology(apiResponse.getTechnology());
    response.setBands2g(apiResponse.getBands2g());
    response.setBands3g(apiResponse.getBands3g());
    response.setBands4g(apiResponse.getBands4g());
    return response;
  }
}
