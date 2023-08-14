package com.booking.mappers;


import com.booking.persistence.entities.device.Device;
import com.booking.services.devices.mapper.DeviceMapper;
import com.booking.services.devices.models.DeviceResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author William Arustamyan
 */


public class DeviceMapperTest {

  private final DeviceMapper mapper = new DeviceMapper();

  @Test
  public void test_device_mapper() {
    final Device device = Helper.makeDevice();

    final DeviceResponse response = this.mapper.apply(device);

    assertNotNull(response);
    assertEquals(device.getId(), response.id);
    assertEquals(device.getBrandName(), response.brandName);
    assertEquals(device.getModelName(), response.modelName);
  }
}
