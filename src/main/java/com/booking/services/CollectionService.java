package com.booking.services;


import java.util.Collection;

/**
 * @author William Arustamyan
 */


public interface CollectionService<E, T extends Collection<E>> {

  T items();

}
