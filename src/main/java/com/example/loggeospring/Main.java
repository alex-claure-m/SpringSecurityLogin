package com.example.loggeospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Main {
//clave para el deploy!
  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  //esto es para deployar
  @Configuration
  public static class Myconfiguration{
    @Bean
    public WebMvcConfigurer corsConfigurer(){
      return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
          registry.addMapping("/**")
              .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
        }
      };
    }
  }
}
