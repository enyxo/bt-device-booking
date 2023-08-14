package com.booking.persistence.repositories.booking;


import com.booking.persistence.entities.booking.Booking;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author William Arustamyan
 */

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

  default boolean bookingExists(UUID userId, UUID deviceId) {
    return existsByUser_IdAndDevice_IdAndUnbookTimeIsNull(userId, deviceId);
  }

  default Optional<Booking> findBooking(UUID userId, UUID deviceId) {
    return findByUser_IdAndDevice_IdAndUnbookTimeIsNull(userId, deviceId);
  }

  @QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
  boolean existsByUser_IdAndDevice_IdAndUnbookTimeIsNull(UUID userId, UUID deviceId);

  @QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
  Optional<Booking> findByUser_IdAndDevice_IdAndUnbookTimeIsNull(UUID userId, UUID deviceId);

  default List<Booking> deviceBookings(UUID deviceId) {
    return findBookingsByDevice_Id(deviceId);
  }

  @QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
  List<Booking> findBookingsByDevice_Id(UUID deviceId);
}
