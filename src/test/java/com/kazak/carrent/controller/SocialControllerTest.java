package com.kazak.carrent.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(SocialController.class)
class SocialControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldRedirectOnInstagramAccount() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/instagram"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("https://www.instagram.com/1diaboli/"));
  }

  @Test
  void shouldRedirectOnYoutubeChannel() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/youtube"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("https://www.youtube.com/channel/UCzZZnOjQVPWuxZC6dqZzEKw"));
  }

  @Test
  void shouldRedirectOnViberChat() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/viber"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("viber://chat?number=+375293590590"));
  }

  @Test
  void shouldRedirectOnLinkedinAccount() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/linkedin"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("www.linkedin.com/in/1kazak-vadim"));
  }

}
