package com.booking.persistence.entities.device;


import com.booking.persistence.entities.BtEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author William Arustamyan
 */


@Getter
@Setter
@Entity
@Table(name = "bt_t_devices")
public class Device extends BtEntity {

  @Column(name = "brand_name", nullable = false)
  private String brandName;

  @Column(name = "model_name", nullable = false)
  private String modelName;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;

    final Device instance = (Device) o;

    return new EqualsBuilder()
      .appendSuper(super.equals(instance))
      .append(this.brandName, instance.brandName)
      .append(this.modelName, instance.modelName)
      .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
      .appendSuper(super.hashCode())
      .append(this.brandName)
      .append(this.modelName)
      .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
      .appendSuper(super.toString())
      .append("brandName", this.brandName)
      .append("modelName", this.modelName)
      .toString();
  }
}
