package com.example.loggeospring.domain.servicios_api.weather.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weather {
  public int id;
  public String main;
  public String description;

  public String getMain() {
    return main;
  }

  public String getDescription() {
    return description;
  }
}
