package com.kazak.carrent.service;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest
class UploadImageServiceTest {

  @MockBean
  private UploadImageService uploadImageService;

  @Test
  void upload() {
    MockMultipartFile imageFile = new MockMultipartFile("image", "image.jpg",
        MediaType.IMAGE_JPEG_VALUE, "car".getBytes());
    assertThat(uploadImageService.upload(imageFile)).isNullOrEmpty();
  }

}
