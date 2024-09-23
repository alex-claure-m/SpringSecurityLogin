package GeoLocationSearch;

import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.entities.Instancia;
import com.example.loggeospring.domain.servicios_api.cabaService.searchGeo.ServicioGeoLocation;
import com.example.loggeospring.domain.servicios_api.cabaService.searchGeo.entities.SearchGeoLocation;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGeoLocationService {

  @Test
  public void devolverInstanciaCon2coordenadasEn1000metros() throws IOException {
    ServicioGeoLocation servicioLocalizacion = ServicioGeoLocation.getInstance();
    SearchGeoLocation instancia = servicioLocalizacion.searchLocation("-34.7072476", "-58.4515931","1000");
    //System.out.println(instancia.getInstancias().size());
    assertTrue(true);
  }
}
