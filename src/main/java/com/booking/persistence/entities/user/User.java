package com.booking.persistence.entities.user;


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
@Table(name = "bt_t_users")
public class User extends BtEntity {


  @Column(name = "name", nullable = false)
  private String name;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;

    final User instance = (User) o;

    return new EqualsBuilder()
      .appendSuper(super.equals(instance))
      .append(this.name, instance.name)
      .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
      .appendSuper(super.hashCode())
      .append(this.name)
      .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
      .appendSuper(super.toString())
      .append("name", this.name)
      .toString();
  }

}
