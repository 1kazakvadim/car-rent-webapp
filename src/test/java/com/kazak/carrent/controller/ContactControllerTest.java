package com.kazak.carrent.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ContactController.class)
class ContactControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldReturnContactPage() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/contact"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("main/contact"));
  }

}
