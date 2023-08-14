package com.booking;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BtDeviceBookingApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(BtDeviceBookingApplication.class)
      .bannerMode(Banner.Mode.OFF)
      .run(args);
  }

}
//  cd zeptolab-app
//  docker build -t zeptolab-app .
//  docker run --name zeptolab-app -dp 8080:8080 zeptolab-app
//  docker start zeptolab-app
//  telnet localhost 8080