package com.booking.endpoints.users;


import com.booking.services.users.UserService;
import com.booking.services.users.models.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author William Arustamyan
 */

@RestController
@RequestMapping("/users")
public class UserEndpoint {

  private static final Logger logger = LoggerFactory.getLogger(UserEndpoint.class);

  private final UserService userService;

  public UserEndpoint(final UserService userService) {
    this.userService = userService;
  }


  @GetMapping("/{id}")
  public UserResponse user(@PathVariable final UUID id) {
    logger.info("Starting query user with id : {}", id);
    return this.userService.get(id);
  }

  @GetMapping
  public List<UserResponse> items() {
    logger.info("Start doing query for all users");
    return this.userService.items();
  }
}
