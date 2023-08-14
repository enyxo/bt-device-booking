package com.booking.persistence.repositories.user;


import com.booking.persistence.entities.user.User;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author William Arustamyan
 */


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  default Optional<User> withId(UUID id) {
    return findByIdAndDeletedIsNull(id);
  }

  @QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
  Optional<User> findByIdAndDeletedIsNull(UUID id);
}
