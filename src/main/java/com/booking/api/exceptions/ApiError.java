package com.booking.api.exceptions;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author William Arustamyan
 */


@Builder
@Getter
@Setter
public class ApiError {

  private String message;

  private Integer code;

  private String error;

  private LocalDateTime timestamp;

  private String exception;

  private String trace;

  private Set<ApiSubError> errors;
}
