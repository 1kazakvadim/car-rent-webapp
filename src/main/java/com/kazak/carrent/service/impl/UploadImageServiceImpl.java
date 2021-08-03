package com.kazak.carrent.service.impl;

import com.kazak.carrent.service.UploadImageService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadImageServiceImpl implements UploadImageService {

  private final String UPLOAD_DIR = "D:/car-rent/upload_images/";


  @Override
  public String upload(MultipartFile imageFile) {
    String imageName =
        UUID.randomUUID().toString() + "." + StringUtils.cleanPath(imageFile.getOriginalFilename());
    try {
      Path path = Paths.get(UPLOAD_DIR + imageName);
      Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return imageName;
  }

}
