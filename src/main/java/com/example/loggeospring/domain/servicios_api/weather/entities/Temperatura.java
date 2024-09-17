package com.example.loggeospring.domain.servicios_api.weather.entities;

import com.example.loggeospring.domain.servicios_api.weather.ConverKelvinToCelsius;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Temperatura implements ConverKelvinToCelsius {
  @JsonProperty("temp")
  public double temp;
  @JsonProperty("feels_like")
  public double feels_like;
  @JsonProperty("temp_min")
  public double temp_min;
  @JsonProperty("temp_max")
  public double temp_max;

  @Override
  public double convertKelvinToCelsius(double temperaturaKelvin) {
    return (temperaturaKelvin - 273.15);
  }

  public double getTemp() {
    return this.convertKelvinToCelsius(temp);
  }

  public double getFeel_like() {
    return this.convertKelvinToCelsius(feels_like);
  }

  public double getTemp_min() {
    return this.convertKelvinToCelsius(temp_min);
  }

  public double getTemp_max() {
    return this.convertKelvinToCelsius(temp_max);
  }

}
