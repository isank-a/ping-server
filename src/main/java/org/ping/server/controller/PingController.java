package org.ping.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class PingController {

  public static final String PONG = "PONG!";

  @GetMapping(path = "ping", produces = MediaType.TEXT_PLAIN_VALUE)
  public String ping() {

    log.info("Returning :: {}", PONG);

    return PONG;
  }
}
