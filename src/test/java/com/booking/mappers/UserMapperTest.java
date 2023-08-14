package com.booking.mappers;


import com.booking.persistence.entities.user.User;
import com.booking.services.users.mapper.UserMapper;
import com.booking.services.users.models.UserResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author William Arustamyan
 */


public class UserMapperTest {

  private final UserMapper mapper = new UserMapper();

  @Test
  public void test_user_mapper() {
    final User user = Helper.makeUser();

    final UserResponse response = mapper.apply(user);

    assertNotNull(response);
    assertEquals(user.getId(), response.id());
    assertEquals(user.getName(), response.name());
  }
}
