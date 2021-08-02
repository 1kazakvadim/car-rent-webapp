package com.kazak.carrent.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TestController {

  private final String UPLOAD_DIR = "src/main/resources/static/images/";

  @GetMapping("catalog_new")
  public String getTest() {
    return "catalog_new";
  }

  @PostMapping("catalog_new/upload")
  public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

    // check if file is empty
    if (file.isEmpty()) {
      attributes.addFlashAttribute("message", "Please select a file to upload.");
      return "redirect:/catalog_new";
    }

    // normalize the file path
    String fileName = UUID.randomUUID().toString() + "." +StringUtils.cleanPath(file.getOriginalFilename());

    // save the file on the local file system
    try {
      Path path = Paths.get(UPLOAD_DIR + fileName);
      Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // return success response
    attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

    return "redirect:/catalog_new";
  }


}
