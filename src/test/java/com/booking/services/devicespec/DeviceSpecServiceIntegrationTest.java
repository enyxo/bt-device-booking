package com.booking.services.devicespec;


import com.booking.api.exceptions.CommonApplicationException;
import com.booking.api.exceptions.remote.RemoteConnectionException;
import com.booking.persistence.entities.device.Device;
import com.booking.services.devices.DeviceQueryService;
import com.booking.services.devices.DeviceSpecService;
import com.booking.services.devices.models.DeviceSpecResponse;
import com.booking.services.remotes.RemoteDeviceSpecAdapterService;
import com.booking.services.remotes.models.DeviceApiQuery;
import com.booking.services.remotes.models.DeviceApiSpecResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * @author William Arustamyan
 */

@SpringBootTest
public class DeviceSpecServiceIntegrationTest {

  private final RemoteDeviceSpecAdapterService remoteAdapter = Mockito.mock(RemoteDeviceSpecAdapterService.class);
  @Autowired
  private DeviceSpecService deviceSpecService;
  @Autowired
  private DeviceQueryService deviceQueryService;

  @Test
  public void test_query_specifications_for_non_existing_device_by_id() {
    assertThrows(CommonApplicationException.class, () -> this.deviceSpecService.query(UUID.randomUUID()));
  }

  @Test
  public void test_query_specifications_for_non_existing_device_by_model_name_and_brand() {
    assertThrows(
      CommonApplicationException.class,
      () -> this.deviceSpecService.query(RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(5))
    );
  }

  @Test
  public void test_query_specifications_with_id() throws RemoteConnectionException, IOException {
    final UUID deviceId = UUID.fromString("b46b5920-7e28-4a8c-9cbd-5decaf8a8275");
    final Device device = this.deviceQueryService.findDevice(deviceId);
    final DeviceApiQuery query = new DeviceApiQuery(device.getBrandName(), device.getModelName());
    when(this.remoteAdapter.get(query)).thenReturn(Optional.of(makeSpecResponse()));
    final DeviceSpecResponse response = this.deviceSpecService.query(deviceId);
    assertNotNull(response);
    assertEquals(device.getId(), response.getId());
    assertEquals(device.getBrandName(), response.getBrandName());
    assertEquals(device.getModelName(), response.getModelName());
  }

  @Test
  public void test_query_specifications_with_model_name_and_brand_name() throws RemoteConnectionException, IOException {
    final String modelName = "Galaxy S9";
    final String brandName = "Samsung";
    final Device device = this.deviceQueryService.findDevice(brandName, modelName);
    final DeviceApiQuery query = new DeviceApiQuery(device.getBrandName(), device.getModelName());
    when(this.remoteAdapter.get(query)).thenReturn(Optional.of(makeSpecResponse()));
    final DeviceSpecResponse response = this.deviceSpecService.query(brandName, modelName);
    assertNotNull(response);
    assertEquals(device.getId(), response.getId());
    assertEquals(device.getBrandName(), response.getBrandName());
    assertEquals(device.getModelName(), response.getModelName());
  }

  private DeviceApiSpecResponse makeSpecResponse() {
    final DeviceApiSpecResponse response = new DeviceApiSpecResponse();
    response.setTechnology(RandomStringUtils.randomAlphabetic(10));
    response.setBands2g(RandomStringUtils.randomAlphabetic(10));
    response.setBands3g(RandomStringUtils.randomAlphabetic(10));
    response.setBands4g(RandomStringUtils.randomAlphabetic(10));
    return response;
  }
}
