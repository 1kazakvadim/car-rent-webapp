package com.kazak.carrent.service;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    uploadImageService.upload(imageFile);
    verify(uploadImageService, times(1)).upload(imageFile);
  }

}
