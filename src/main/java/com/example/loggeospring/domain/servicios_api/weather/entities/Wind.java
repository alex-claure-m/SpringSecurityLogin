package com.example.loggeospring.domain.servicios_api.weather.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wind {
  public double speed;
  public double deg;
  public double gust;

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public void setDeg(double deg) {
    this.deg = deg;
  }

  public void setGust(double gust) {
    this.gust = gust;
  }
}
