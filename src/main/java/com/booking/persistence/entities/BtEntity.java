package com.booking.persistence.entities;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author William Arustamyan
 */


@Getter
@Setter
@MappedSuperclass
public abstract class BtEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  protected UUID id;

  @Column(name = "created", nullable = false)
  protected LocalDateTime created;

  @LastModifiedDate
  @Column(name = "updated", nullable = false)
  protected LocalDateTime updated;

  @Column(name = "deleted")
  protected LocalDateTime deleted;

  @Version
  protected Integer version;

  @PrePersist
  protected void onCreate() {
    this.created = LocalDateTime.now();
    this.updated = this.created;
  }

  @PreRemove
  public void onRemove() {
    this.deleted = LocalDateTime.now();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final BtEntity instance = (BtEntity) o;
    return new EqualsBuilder()
      .append(id, instance.id)
      .append(created, instance.created)
      .append(updated, instance.updated)
      .append(deleted, instance.deleted)
      .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
      .append(id)
      .append(created)
      .append(updated)
      .append(deleted)
      .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
      .append("id", id)
      .append("created", created)
      .append("updated", updated)
      .append("deleted", deleted)
      .toString();
  }
}
