package com.example.loggeospring.domain.servicios_api.weather.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// recordatorio: para el mappeo, tiene que ser los mismos nombres de argumentos
@Getter
@Setter
public class Pais {
  @JsonProperty("weather")
	//descripcion rapida del clima
  public List<Weather> weather;
  // temperaturas de la ciudad
  @JsonProperty("main")
  public Temperatura main;
  // viento de la ciudad
  @JsonProperty("wind")
  public Wind wind;
  public int id;
  public String name;


  public String getNombreCiudad() {
    return name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getTemperatura() {
    return (int) main.getTemp();
  }
  public int getTemperaturaMinima() {
    return (int)  main.getTemp_min();
  }
	public int getTemperaturaMaxima() {
    return (int)  main.getTemp_max();
  }

  public int getSensacionTermica() {
    return (int)  main.getFeel_like();
  }
  public double getViento() {
    return wind.getSpeed();
  }
  public String getDescripcionCielo(){
    return weather.get(0).getDescription();
  }
  public String getDescripcionWeather() {
    return weather.get(0).getMain();
  }

}
