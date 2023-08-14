package com.booking.persistence.entities.booking;


import com.booking.persistence.entities.BtEntity;
import com.booking.persistence.entities.device.Device;
import com.booking.persistence.entities.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;

/**
 * @author William Arustamyan
 */


@Getter
@Setter
@Entity
@Table(name = "bt_t_bookings")
public class Booking extends BtEntity {

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  private User user;


  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name = "device_id", referencedColumnName = "id", nullable = false)
  private Device device;

  @Column(name = "book_time", nullable = false)
  private LocalDateTime bookTime;

  @Column(name = "unbook_time")
  private LocalDateTime unbookTime;

  public Booking() {

  }

  public Booking(final Device device, final User user) {
    this.device = device;
    this.user = user;
  }

  protected void onCreate() {
    this.created = LocalDateTime.now();
    this.updated = this.created;
    this.bookTime = this.created;
  }

  public Booking unbook() {
    this.unbookTime = LocalDateTime.now();
    return this;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass())
      return false;

    final Booking instance = (Booking) o;

    return new EqualsBuilder()
      .appendSuper(super.equals(instance))
      .append(this.user.getId(), instance.user.getId())
      .append(this.device.getId(), instance.device.getId())
      .append(this.bookTime, instance.bookTime)
      .append(this.unbookTime, instance.unbookTime)
      .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
      .appendSuper(super.hashCode())
      .append(this.user.getId())
      .append(this.device.getId())
      .append(this.bookTime)
      .append(this.unbookTime)
      .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
      .append("user", this.user.getName())
      .append("device", this.device.getBrandName() + " " + device.getModelName())
      .append("bookTime", this.bookTime)
      .append("unbookTime", this.unbookTime)
      .appendSuper(super.toString())
      .toString();
  }
}
