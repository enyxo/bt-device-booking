package com.booking.services;


/**
 * @author William Arustamyan
 */


public interface GetService<T, ID> {

  T get(ID id);

}
