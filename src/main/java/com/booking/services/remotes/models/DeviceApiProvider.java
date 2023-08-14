package com.booking.services.remotes.models;


import java.util.Arrays;

/**
 * @author William Arustamyan
 */


public enum DeviceApiProvider {
  RAPID("rapid"), FONO("fono");

  private final String providerName;

  DeviceApiProvider(final String providerName) {
    this.providerName = providerName;
  }

  public static DeviceApiProvider fromStr(final String input) {
    return Arrays.stream(values())
      .filter(it -> it.providerName.equalsIgnoreCase(input))
      .findFirst()
      .orElseThrow(
        () -> new RuntimeException(String.format("Unknown api provider name : {%s}", input))
      );
  }
}
