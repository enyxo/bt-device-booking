package com.booking.api.exceptions.remote;


/**
 * @author William Arustamyan
 */


public class RemoteConnectionException extends Exception {

  private final String message = "Unable to connect remote service";

  public String getMessage() {
    return this.message;
  }
}
