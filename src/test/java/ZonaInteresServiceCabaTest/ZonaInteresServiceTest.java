package ZonaInteresServiceCabaTest;

import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.ServicioBusquedaZonasCaba;
import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.entities.Instancia;
import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.entities.InteresZonaSearch;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class ZonaInteresServiceTest {
  @Test
  public void interesesBusquedaPorNombreHospital() throws IOException {
   // ver en la clase INTERESZONASSEARCH para entender que es cada atributo!
    ServicioBusquedaZonasCaba servicio = ServicioBusquedaZonasCaba.getInstance();
    InteresZonaSearch interesZonaSearch = servicio.listadoDeInteres("hospital");
    System.out.println("las clases que me mostrara son" + interesZonaSearch.getClasesEncontradas());
    //esta bien que me devuelva todas las clases, ya que mi idea en si es hacer una tabla
    // con los resultados
  }
  @Test
	public void interesBusquedaPorNombreUniversidadPorInstancia() throws IOException {
    ServicioBusquedaZonasCaba servicio = ServicioBusquedaZonasCaba.getInstance();
    InteresZonaSearch interesZonaSearch = servicio.listadoDeInteres("universidad");
    List<Instancia> interesBusqueda = interesZonaSearch.getInstancias().stream().filter(instancia -> instancia.getNombre().toLowerCase().contains("universidad")).toList();
    //System.out.println("las instancias universidad encontradas son:"+ interesBusqueda.size());
    //for(int i = 0; i < interesBusqueda.size(); i++) {
     // System.out.println("la instancia es:" + interesBusqueda.get(i).getNombre());
    //}

  }
}
