package com.booking.services.users;


import com.booking.services.CollectionService;
import com.booking.services.GetService;
import com.booking.services.users.models.UserResponse;

import java.util.List;
import java.util.UUID;

/**
 * @author William Arustamyan
 */


public interface UserService extends GetService<UserResponse, UUID>, CollectionService<UserResponse, List<UserResponse>> {

  UserResponse get(UUID id);

  List<UserResponse> items();
}
