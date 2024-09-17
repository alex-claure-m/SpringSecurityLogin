package CustomSearch;

import com.example.loggeospring.domain.servicios_api.cabaService.searchCategorias.ServiceCategories;
import com.example.loggeospring.domain.servicios_api.cabaService.searchCategorias.entities.Categoria;
import com.example.loggeospring.domain.servicios_api.cabaService.searchCategorias.entities.Categories;
import com.example.loggeospring.domain.servicios_api.cabaService.searchCustomEngine.ServicioCustomBusqueda;
import com.example.loggeospring.domain.servicios_api.cabaService.searchCustomEngine.entiitesCustom.CustomSearch;
import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.ServicioBusquedaZonasCaba;
import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.entities.ClaseEncontrada;
import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.entities.Instancia;
import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.entities.InteresZonaSearch;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestCustomSearch {

  @Test
  public void getCategoriasTodasJuntas() throws IOException {
    ServiceCategories categories = ServiceCategories.getInstance();
    Categoria categorias = categories.getCategoriasTotales();
    for(int i =0; i< categorias.getCategorias().size(); i++) {
      System.out.println(categorias.getCategorias().get(i).getNombre());
    }
  }
  @Test
  public void getCustomSearch() throws IOException {
    ServicioBusquedaZonasCaba servicioBusqueda = ServicioBusquedaZonasCaba.getInstance(); // put string busqueda
    ServicioCustomBusqueda servicioCustom = ServicioCustomBusqueda.getInstance(); // put id -- ex: dependencias_culturales|547

    String servicio = "Policia";
    String direccion = "";
    String categoria = "Dependencias Culturales";
    if (!servicio.isEmpty() || direccion.isEmpty()) {
      InteresZonaSearch servicioBuscado = servicioBusqueda.listadoDeInteres(servicio); // aca obtengo la clase - debo matchear el id de la ClaseEncontrada
      List<Instancia> nombreDeInstancias = servicioBuscado.getInstanciaPorNombre(servicio); // una vez obtenido la clase, voy a la clase InteresSearch y busco el nombre
      //System.out.println("servicioBuscado: " + nombreDeInstancias.get(0).getNombre());

      /* ************* obtendria los id de las instancias ya filtradas por nombre para matchear con la api GETOBJECTCustom ************** */
      /* ************ el id cumpliria con el formato: "dependencias_culturales" (sin el |xx )*/
      List<String> claseIdInstanciasFiltradaByNombre = nombreDeInstancias.stream().map(unaInstancia -> unaInstancia.getId()).toList();
      // System.out.println("lalaal: " + claseIdInstanciasFiltradaByNombre.size());
      /* *************** ahora una vez obtenido los ID, necesito que matchee ESE STRING con de CATEGORIA CON LA CLASE CUSTOMSEARCH  */
      List<CustomSearch> lista = new ArrayList<>();

      for (int i = 0; i < claseIdInstanciasFiltradaByNombre.size(); i++) {
        lista.add(servicioCustom.getObjectContenidos(claseIdInstanciasFiltradaByNombre.get(i)));
        //aca obtengo todas las clases Custom, que esten con el mismo nombre de la categoria
        // System.out.println("lalaal: " + lista.get(i).getId());
      }
    } else if (!categoria.isEmpty()) {
      CustomSearch unCustomPorCategoria = servicioCustom.getObjectContenidos(categoria);
      System.out.println("lalaal2: " + unCustomPorCategoria.getId());
    }
   }
  }
