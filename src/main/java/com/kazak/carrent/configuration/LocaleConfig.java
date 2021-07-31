package com.kazak.carrent.configuration;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.annotation.Resource;
import org.apache.logging.log4j.util.PropertiesPropertySource;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class LocaleConfig implements WebMvcConfigurer {

  @Bean
  public LocaleResolver localeResolver() {
    CookieLocaleResolver  localeResolver = new CookieLocaleResolver ();
    return localeResolver;
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("lang");
    return localeChangeInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }

//  @Bean
//  public MessageSource getMessageResource() {
//    ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
//    messageResource.setBasename("classpath:l10n");
//    messageResource.setCacheSeconds(3600);
//    messageResource.setDefaultEncoding("UTF-8");
//    return messageResource;
//  }

}


