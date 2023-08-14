package com.booking.services.users.models;


import java.util.UUID;

/**
 * @author William Arustamyan
 */

public record UserResponse(UUID id, String name) {

  public static UserResponse from(final UUID id, final String name) {
    return new UserResponse(id, name);
  }

}
