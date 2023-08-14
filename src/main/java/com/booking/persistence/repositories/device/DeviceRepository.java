package com.booking.persistence.repositories.device;


import com.booking.persistence.entities.device.Device;
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
public interface DeviceRepository extends JpaRepository<Device, UUID> {

  default Optional<Device> withId(UUID id) {
    return findByIdAndDeletedIsNull(id);
  }

  default Optional<Device> withBrandAndModel(String brandName, String modelName) {
    return findByBrandNameAndModelNameAndDeletedIsNull(brandName, modelName);
  }

  Optional<Device> findByBrandNameAndModelNameAndDeletedIsNull(String brandName, String modelName);

  @QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
  Optional<Device> findByIdAndDeletedIsNull(UUID id);


}
