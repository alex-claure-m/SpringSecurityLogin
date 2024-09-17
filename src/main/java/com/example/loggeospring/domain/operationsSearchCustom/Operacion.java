package com.example.loggeospring.domain.operationsSearchCustom;

import com.example.loggeospring.domain.servicios_api.cabaService.searchCategorias.ServiceCategories;
import com.example.loggeospring.domain.servicios_api.cabaService.searchCategorias.entities.Categoria;
import com.example.loggeospring.domain.servicios_api.cabaService.searchCategorias.entities.Categories;
import com.example.loggeospring.domain.servicios_api.cabaService.searchCustomEngine.SearchCustom;
import com.example.loggeospring.domain.servicios_api.cabaService.searchCustomEngine.ServicioCustomBusqueda;
import com.example.loggeospring.domain.servicios_api.cabaService.searchCustomEngine.entiitesCustom.CustomSearch;
import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.ServicioBusquedaZonasCaba;
import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.entities.Instancia;
import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.entities.InteresZonaSearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Operacion implements ServiceOperation {


  public List<CustomSearch> obtenerListaDeServiciosCustomizados(ServicioBusquedaZonasCaba servicioBusqueda, ServicioCustomBusqueda servicioCustom, String servicio, String categoria, String direccion) throws IOException {

    //ServicioBusquedaZonasCaba servicioBusqueda = ServicioBusquedaZonasCaba.getInstance(); // put string busqueda
    //ServicioCustomBusqueda servicioCustom = ServicioCustomBusqueda.getInstance(); // put id -- ex: dependencias_culturales|547
    ServiceCategories serviceCategories = ServiceCategories.getInstance();


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
      return lista;
    } else if (!categoria.isEmpty()) {
      Categoria serviciosCategoria = serviceCategories.getCategoriasTotales();
      List<Categories> listaServicioCategoria = serviciosCategoria.getCategorias().stream().toList();
      List<CustomSearch> listaFiltradaPorCategoria = new ArrayList<>();

      // Bucle para recorrer la lista de categorías
      for (Categories categoriaServicio : listaServicioCategoria) {
        // Verificamos si el nombre de la categoría coincide con el filtro
        if (categoriaServicio.getNombre().equals(categoria)) {
          // Obtenemos el CustomSearch correspondiente a esa categoría
          CustomSearch unCustomPorCategoria = servicioCustom.getObjectContenidos(categoriaServicio.getNombre());

          // Si el CustomSearch no es null, lo agregamos a la lista filtrada
          if (unCustomPorCategoria != null) {
            listaFiltradaPorCategoria.add(unCustomPorCategoria);
          }
        }
      }
      System.out.println("lalaal2: " + listaFiltradaPorCategoria.size());
      return listaFiltradaPorCategoria;
    } return null;
  }

  @Override
  public Boolean perteneceALaMismaClase(String a, String b) {
    return null;
  }
}
