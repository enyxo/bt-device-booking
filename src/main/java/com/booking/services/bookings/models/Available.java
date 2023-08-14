package com.booking.services.bookings.models;


/**
 * @author William Arustamyan
 */


public enum Available {
  YES, NO;


  public static Available fromLogical(boolean input) {
    return input ? YES : NO;
  }
}
