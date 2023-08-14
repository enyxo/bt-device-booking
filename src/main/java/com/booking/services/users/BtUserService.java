package com.booking.services.users;


import com.booking.api.exceptions.ResourceNotFoundException;
import com.booking.persistence.repositories.user.UserRepository;
import com.booking.services.users.mapper.UserMapper;
import com.booking.services.users.models.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author William Arustamyan
 */

@Service
@Transactional(readOnly = true)
public class BtUserService implements UserService {

  private static final Logger logger = LoggerFactory.getLogger(BtUserService.class);

  private final UserMapper mapper;
  private final UserRepository repository;

  public BtUserService(final UserMapper mapper,
                       final UserRepository repository) {
    this.mapper = mapper;
    this.repository = repository;
  }


  @Override
  public UserResponse get(final UUID id) {
    logger.debug("Start query user with id : {}", id);
    return this.repository.withId(id)
      .map(this.mapper)
      .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id %s not found ...", id)));
  }

  @Override
  public List<UserResponse> items() {
    logger.debug("Starting users query");
    return this.repository.findAll().stream()
      .map(this.mapper)
      .collect(Collectors.toList());
  }
}
