package com.kazak.carrent.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(LogoutController.class)
class LogoutControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldLogoutAndReturnIndexPage() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/logout"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/index"));
  }

}
