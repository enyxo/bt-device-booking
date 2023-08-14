package com.booking.services.remotes.rapid.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author William Arustamyan
 */


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RapidApiResponse {

  private RapidApiNetworkDetails gsmNetworkDetails;
}
