package com.booking.api.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CommonApplicationException extends RuntimeException {

  private final HttpStatus status;
  private final String message;
  private final Throwable cause;
  private final Set<ApiSubError> errors;

  public CommonApplicationException(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
    this.cause = null;
    this.errors = new HashSet<>();
  }

  public CommonApplicationException(HttpStatus status, String message, Throwable cause) {
    this.status = status;
    this.message = message;
    this.cause = cause;
    this.errors = new HashSet<>();
  }


  public CommonApplicationException addError(ApiSubError error) {
    this.errors.add(error);
    return this;
  }

  public void addErrors(Collection<ApiSubError> errors) {
    this.errors.addAll(errors);
  }

  public boolean hasErrors() {
    return !this.errors.isEmpty();
  }

  public HttpStatus getStatus() {
    return this.status;
  }

  public String getMessage() {
    return this.message;
  }

  public Throwable getCause() {
    return this.cause;
  }

  public Set<ApiSubError> getErrors() {
    return this.errors;
  }
}
