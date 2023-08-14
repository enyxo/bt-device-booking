package com.booking.api.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author William Arustamyan
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiSubError {

  private String object;

  private String field;

  private Object rejectValue;

  private String message;

  private String code;

}
