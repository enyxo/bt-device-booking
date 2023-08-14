package com.booking.mappers;


import com.booking.persistence.entities.device.Device;
import com.booking.services.devices.mapper.DeviceSpecMapper;
import com.booking.services.devices.models.DeviceSpecResponse;
import com.booking.services.remotes.models.DeviceApiSpecResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author William Arustamyan
 */


public class DeviceSpecMapperTest {

  private final DeviceSpecMapper mapper = new DeviceSpecMapper();

  @Test
  public void test_device_spec_mapper() {
    final Device device = Helper.makeDevice();
    final DeviceApiSpecResponse apiResponse = this.makeApiSpecResponse();
    final DeviceSpecResponse response = mapper.apply(device, apiResponse);

    assertNotNull(response);
    assertEquals(device.getId(), response.getId());
    assertEquals(device.getModelName(), response.getModelName());
    assertEquals(device.getBrandName(), response.getBrandName());
    assertEquals(apiResponse.getTechnology(), response.getTechnology());
    assertEquals(apiResponse.getBands2g(), response.getBands2g());
    assertEquals(apiResponse.getBands3g(), response.getBands3g());
    assertEquals(apiResponse.getBands4g(), response.getBands4g());
  }

  private DeviceApiSpecResponse makeApiSpecResponse() {
    final DeviceApiSpecResponse response = new DeviceApiSpecResponse();
    response.setTechnology(RandomStringUtils.randomAlphabetic(10));
    response.setBands2g(RandomStringUtils.randomAlphabetic(10));
    response.setBands3g(RandomStringUtils.randomAlphabetic(10));
    response.setBands4g(RandomStringUtils.randomAlphabetic(10));
    return response;
  }
}
