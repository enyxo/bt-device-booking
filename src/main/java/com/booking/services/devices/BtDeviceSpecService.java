package com.booking.services.devices;


import com.booking.api.exceptions.CommonApplicationException;
import com.booking.api.exceptions.remote.RemoteConnectionException;
import com.booking.persistence.entities.device.Device;
import com.booking.services.devices.mapper.DeviceSpecMapper;
import com.booking.services.devices.models.DeviceSpecResponse;
import com.booking.services.remotes.RemoteDeviceSpecAdapterService;
import com.booking.services.remotes.models.DeviceApiQuery;
import com.booking.services.remotes.models.DeviceApiSpecResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

/**
 * @author William Arustamyan
 */

@Service
public class BtDeviceSpecService implements DeviceSpecService {

  private static final Logger logger = LoggerFactory.getLogger(BtDeviceSpecService.class);

  private final DeviceSpecMapper deviceSpecMapper;
  private final DeviceQueryService deviceQueryService;
  private final RemoteDeviceSpecAdapterService remoteDeviceService;


  public BtDeviceSpecService(final DeviceSpecMapper deviceSpecMapper,
                             final DeviceQueryService deviceQueryService,
                             final RemoteDeviceSpecAdapterService remoteDeviceService) {
    this.deviceSpecMapper = deviceSpecMapper;
    this.deviceQueryService = deviceQueryService;
    this.remoteDeviceService = remoteDeviceService;
  }

  @Override
  public DeviceSpecResponse query(final String brandName, final String modelName) {
    logger.debug("Start doing query for device with brand name : {} and model name : {}", brandName, modelName);
    final Device device = this.deviceQueryService.findDevice(brandName, modelName);
    final DeviceApiSpecResponse response = this.safeQuery(new DeviceApiQuery(brandName, modelName))
      .orElseThrow(() -> new CommonApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to get specifications from remote service"));
    return this.deviceSpecMapper.apply(device, response);
  }

  @Override
  public DeviceSpecResponse query(final UUID id) {
    logger.debug("Start doing query for device with id : {}", id);
    final Device device = this.deviceQueryService.findDevice(id);
    final DeviceApiSpecResponse response = this.safeQuery(new DeviceApiQuery(device.getBrandName(), device.getModelName()))
      .orElseThrow(() -> new CommonApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to get specifications from remote service"));
    return this.deviceSpecMapper.apply(device, response);
  }

  private Optional<DeviceApiSpecResponse> safeQuery(final DeviceApiQuery query) {
    try {
      return this.remoteDeviceService.get(query);
    } catch (RemoteConnectionException | IOException e) {
      logger.error("Unable to get device spec from remote service...", e);
      throw new RuntimeException("Unable to get device specification from remote service", e);
    }
  }

}
