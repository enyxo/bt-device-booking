package com.booking.services.devices;


import com.booking.services.CollectionService;
import com.booking.services.GetService;
import com.booking.services.devices.models.DeviceResponse;

import java.util.List;
import java.util.UUID;

/**
 * @author William Arustamyan
 */


public interface DeviceService extends GetService<DeviceResponse, UUID>, CollectionService<DeviceResponse, List<DeviceResponse>> {

  DeviceResponse get(UUID id);

  List<DeviceResponse> items();
}
