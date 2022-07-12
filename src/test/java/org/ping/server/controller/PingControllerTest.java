package org.ping.server.controller;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
public class PingControllerTest {

  private static final String PING_URI = "/ping";

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("200 - PING returns PONG")
  void ping_returns_pong() throws Exception {

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(PING_URI))
        .andExpectAll(
            MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().contentType(new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8)),
            MockMvcResultMatchers.content().string("PONG!"));
  }
}
