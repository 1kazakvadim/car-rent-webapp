package com.kazak.carrent.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldReturnLoginPage() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/login"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("main/login"));
  }

}
