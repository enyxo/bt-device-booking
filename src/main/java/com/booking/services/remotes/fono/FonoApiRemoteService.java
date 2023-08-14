package com.booking.services.remotes.fono;


import com.booking.api.exceptions.remote.RemoteConnectionException;
import com.booking.services.remotes.RemoteDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author William Arustamyan
 */

@Service
public class FonoApiRemoteService implements RemoteDeviceService {

  private static final Logger logger = LoggerFactory.getLogger(FonoApiRemoteService.class);

  @Override
  public Optional<Object> get(Object o) throws RemoteConnectionException {
    throw new UnsupportedOperationException("Currently not supported");
  }
}
