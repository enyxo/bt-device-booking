package com.booking.services.users;


import com.booking.api.exceptions.ResourceNotFoundException;
import com.booking.services.users.models.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author William Arustamyan
 */

@SpringBootTest
public class UserServiceIntegrationTest {

  private static final UUID existingUserId = UUID.fromString("f4d1e11e-aec4-4e62-a938-35146c2993d5");
  private static final String existingUserName = "user-1";

  @Autowired
  private UserService userService;


  @Test
  public void test_get_user_with_id_ok() {
    final UserResponse userResponse = this.userService.get(existingUserId);
    assertEquals(existingUserId, userResponse.id());
    assertEquals(existingUserName, userResponse.name());
  }

  @Test
  public void test_get_user_with_id_throws_exception() {
    assertThrows(ResourceNotFoundException.class, () -> this.userService.get(UUID.randomUUID()));
  }

  @Test
  public void test_get_all_users() {
    final List<UserResponse> items = this.userService.items();
    assertFalse(items.isEmpty());
    assertEquals(items.size(), 10);
  }

}
