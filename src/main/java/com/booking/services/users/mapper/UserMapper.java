package com.booking.services.users.mapper;


import com.booking.persistence.entities.user.User;
import com.booking.services.users.models.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @author William Arustamyan
 */


@Component
public final class UserMapper implements Function<User, UserResponse> {

  private static final Logger logger = LoggerFactory.getLogger(UserMapper.class);

  @Override
  public UserResponse apply(final User source) {
    logger.debug("Starting user mapping for instance : {}", source);
    return UserResponse.from(
      source.getId(),
      source.getName()
    );
  }
}
