package com.kazak.carrent.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImageService {

  String upload(MultipartFile imageFile);

}
