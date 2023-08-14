package com.booking.services.remotes;


import com.booking.api.exceptions.remote.RemoteConnectionException;

import java.io.IOException;
import java.util.Optional;

/**
 * @author William Arustamyan
 */


public interface RemoteDeviceService<Query, Response> {

  Optional<Response> get(Query query) throws RemoteConnectionException, IOException;

}
