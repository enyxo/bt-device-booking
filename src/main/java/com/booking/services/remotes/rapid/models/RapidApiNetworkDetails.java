package com.booking.services.remotes.rapid.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author William Arustamyan
 */


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RapidApiNetworkDetails {

  private String networkTechnology;

  private String network2GBands;

  private String network3GBands;

  private String network4GBands;
}
