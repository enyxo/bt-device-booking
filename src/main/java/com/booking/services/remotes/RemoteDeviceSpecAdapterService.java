package com.booking.services.remotes;


import com.booking.api.exceptions.remote.RemoteConnectionException;
import com.booking.services.remotes.models.DeviceApiProvider;
import com.booking.services.remotes.models.DeviceApiQuery;
import com.booking.services.remotes.models.DeviceApiSpecResponse;
import com.booking.services.remotes.rapid.models.RapidApiQuery;
import com.booking.services.remotes.rapid.models.RapidApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 * @author William Arustamyan
 */

@Service
public class RemoteDeviceSpecAdapterService implements RemoteDeviceService<DeviceApiQuery, DeviceApiSpecResponse> {

  private final DeviceApiProvider provider;
  private final RemoteDeviceService<RapidApiQuery, RapidApiResponse> remoteService;

  public RemoteDeviceSpecAdapterService(@Value("${bt.device.api.provider}") final String strProvider,
                                        final RemoteDeviceService<RapidApiQuery, RapidApiResponse> remoteService) {
    this.provider = DeviceApiProvider.fromStr(strProvider);
    this.remoteService = remoteService;
  }

  @Override
  public Optional<DeviceApiSpecResponse> get(final DeviceApiQuery apiQuery) throws RemoteConnectionException, IOException {
    if (this.provider == DeviceApiProvider.RAPID) {
      return this.toResponse(this.remoteService.get(this.toRapidQuery(apiQuery)));
    }
    throw new RuntimeException("FonoApi currently not supported");
  }

  public RapidApiQuery toRapidQuery(final DeviceApiQuery query) {
    return new RapidApiQuery(query.brandName, query.modelName);
  }

  public Optional<DeviceApiSpecResponse> toResponse(final Optional<RapidApiResponse> opResponse) {
    if (opResponse.isPresent()) {
      final RapidApiResponse rsp = opResponse.get();
      final DeviceApiSpecResponse target = new DeviceApiSpecResponse();
      target.setTechnology(rsp.getGsmNetworkDetails().getNetworkTechnology());
      target.setBands2g(rsp.getGsmNetworkDetails().getNetwork2GBands());
      target.setBands3g(rsp.getGsmNetworkDetails().getNetwork3GBands());
      target.setBands4g(rsp.getGsmNetworkDetails().getNetwork4GBands());
      return Optional.of(target);
    }
    return Optional.empty();
  }
}
