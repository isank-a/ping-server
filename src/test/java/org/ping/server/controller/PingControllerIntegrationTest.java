package org.ping.server.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PingControllerIntegrationTest {

  private static final String PING_URI = "/ping";


  @Autowired
  TestRestTemplate restTemplate;

  @Test
  void ping_returns_pong() {

    ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(PING_URI, String.class);

    assertAll(
        () -> assertNotNull(responseEntity),
        () -> assertTrue(responseEntity.getStatusCode().is2xxSuccessful()),
        () -> assertEquals(200, responseEntity.getStatusCode().value()),
        () -> assertEquals(new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8), responseEntity.getHeaders().getContentType()),
        () -> assertTrue(responseEntity.hasBody()),
        () -> assertEquals("PONG!", responseEntity.getBody())
    );
  }
}
