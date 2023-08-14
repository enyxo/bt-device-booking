package com.booking.api.exceptions;


import org.springframework.http.HttpStatus;

/**
 * @author William Arustamyan
 */


public final class ResourceNotFoundException extends CommonApplicationException {


  public ResourceNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, message);
  }
}
