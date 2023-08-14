package com.booking.services.remotes.rapid;


import com.booking.api.exceptions.remote.RemoteConnectionException;
import com.booking.services.remotes.RemoteDeviceService;
import com.booking.services.remotes.rapid.models.RapidApiQuery;
import com.booking.services.remotes.rapid.models.RapidApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author William Arustamyan
 */


@Service
public final class RapidApiRemoteService implements RemoteDeviceService<RapidApiQuery, RapidApiResponse> {

  private static final Logger logger = LoggerFactory.getLogger(RapidApiRemoteService.class);

  private final ObjectMapper mapper;

  @Value("${bt.remote.rapid.api.url}")
  private String url;

  @Value("${bt.remote.rapid.api.host}")
  private String host;

  @Value("${bt.remote.rapid.api.key}")
  private String key;

  public RapidApiRemoteService(final ObjectMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public Optional<RapidApiResponse> get(final RapidApiQuery query) throws RemoteConnectionException {
    logger.info("start doing async http call to rapid api with query parameters : {}", query);
    RapidApiResponse remoteResponse = null;
    try (AsyncHttpClient client = new DefaultAsyncHttpClient()) {
      remoteResponse = client.prepare("GET", this.buildQueryUrl(query))
        .setHeader("X-RapidAPI-Key", this.key)
        .setHeader("X-RapidAPI-Host", this.host)
        .execute()
        .toCompletableFuture()
        .thenApply(convertResponseToObject()).join();
    } catch (final Exception ex) {
      logger.error("Remote call ends with error ", ex);
    }
    logger.debug("Rapid Api remote call successfully ends, returning result");
    return Optional.ofNullable(remoteResponse);
  }

  private Function<Response, RapidApiResponse> convertResponseToObject() {
    return response -> {
      try {
        return mapper.readValue(response.getResponseBody(), RapidApiResponse.class);
      } catch (JsonProcessingException e) {
        throw new RuntimeException("Unable to unmarshalling response body from Rapid Api", e);
      }
    };
  }

  private String buildQueryUrl(final RapidApiQuery query) {
    return String.join("/", this.url, query.brandName(), query.modelName());
  }

}
