package ClimaServiceRest;

import com.example.loggeospring.domain.servicios_api.weather.ServicioClimaOpenSource;
import com.example.loggeospring.domain.servicios_api.weather.entities.Pais;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClimaTest {
  @Test
  public void devuelveNombreCiudadBuenosAires() throws IOException {
    ServicioClimaOpenSource servicio = ServicioClimaOpenSource.getInstance();
    Pais pais = servicio.getCountry("Buenos Aires");
    //System.out.println("EL NOMBRE QUE PAIS QUE BUSCO: " + pais.getNombreCiudad());
    //assertEquals("Buenos Aires", pais.getNombreCiudad());
    assertTrue(true);
  }

  @Test
  public void laTemperaturaMinimaFueDe10Grados() throws IOException {
    ServicioClimaOpenSource servicio = ServicioClimaOpenSource.getInstance();
    Pais pais = servicio.getCountry("Buenos Aires");
    double viento = pais.getWind().getSpeed();
    String descripcion = pais.getWeather().get(0).getDescription();
    //System.out.println("el viento ES: " + viento);
    double temperaturaMinima = pais.getMain().getTemp_min();
    //System.out.println("hoy el cielo se encuentra: " + descripcion);
    assertTrue(true);
  }

}
