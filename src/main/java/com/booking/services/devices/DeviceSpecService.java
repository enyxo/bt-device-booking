package com.booking.services.devices;


import com.booking.services.devices.models.DeviceSpecResponse;

import java.util.UUID;

/**
 * @author William Arustamyan
 */


public interface DeviceSpecService {

  DeviceSpecResponse query(String brandName, String modelName);

  DeviceSpecResponse query(UUID id);
}
