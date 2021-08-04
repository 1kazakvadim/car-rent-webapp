package com.kazak.carrent.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ExternalFolderConfig implements WebMvcConfigurer {

  public static String externalFilePath = "file:D:/car-rent/upload_images/";

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/upload_images/**").addResourceLocations(externalFilePath);
  }

}
