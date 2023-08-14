package com.booking.services.devices;


import com.booking.api.exceptions.ResourceNotFoundException;
import com.booking.services.devices.models.DeviceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author William Arustamyan
 */

@SpringBootTest
public class DeviceServiceIntegrationTest {


  private static final UUID existingDeviceId = UUID.fromString("53f681cd-be9e-4d11-896e-dd31297357f2");
  private static final String existingDeviceBrandName = "Samsung";
  private static final String existingDeviceModelName = "Galaxy S9";

  @Autowired
  private DeviceService deviceService;

  @Test
  public void test_get_device_with_id_ok() {
    final DeviceResponse deviceResponse = this.deviceService.get(existingDeviceId);
    assertEquals(existingDeviceId, deviceResponse.id);
    assertEquals(existingDeviceBrandName, deviceResponse.brandName);
    assertEquals(existingDeviceModelName, deviceResponse.modelName);
  }

  @Test
  public void test_get_device_with_id_throws_exception() {
    assertThrows(ResourceNotFoundException.class, () -> this.deviceService.get(UUID.randomUUID()));
  }

  @Test
  public void test_get_all_devices_ok() {
    final List<DeviceResponse> items = this.deviceService.items();
    assertFalse(items.isEmpty());
    assertEquals(items.size(), 9);
  }
}
