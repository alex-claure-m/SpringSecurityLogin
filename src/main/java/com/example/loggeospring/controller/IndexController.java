package com.example.loggeospring.controller;

import com.example.loggeospring.domain.servicios_api.cabaService.searchCategorias.ServiceCategories;
import com.example.loggeospring.domain.servicios_api.cabaService.searchCategorias.entities.Categoria;
import com.example.loggeospring.domain.servicios_api.cabaService.searchCategorias.entities.Categories;
import com.example.loggeospring.domain.servicios_api.cabaService.searchCustomEngine.ServicioCustomBusqueda;
import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.ServicioBusquedaZonasCaba;
import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.entities.Instancia;
import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.entities.InteresZonaSearch;
import com.example.loggeospring.domain.servicios_api.cabaService.searchGeo.ServicioGeoLocation;
import com.example.loggeospring.domain.servicios_api.cabaService.searchGeo.entities.GeoLocation;
import com.example.loggeospring.domain.servicios_api.cabaService.searchGeo.entities.GeoTransform;
import com.example.loggeospring.domain.servicios_api.cabaService.searchGeo.entities.SearchGeoLocation;
import com.example.loggeospring.domain.servicios_api.weather.ServicioClimaOpenSource;
import com.example.loggeospring.domain.servicios_api.weather.entities.Pais;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.locationtech.jts.geom.Point;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

	//autowired para la inyeccion de dependencias
  //requiero usar clases, interfaces , los componentes de una api
  // y necesito que me lo instancie en este controller

  // @Autowired -- update: capaz no hace falta!
  //private ServicioClimaOpenSource servicioClimaOpenSource;

  //traigo info de la api para que lo muestre, ademas de autenticar el usuario
  @GetMapping("/")
  public String index(@AuthenticationPrincipal UserDetails userdetails, Model model) {
    if (userdetails != null) {

      model.addAttribute("username", userdetails);
    }
    try {
      ServicioClimaOpenSource servicioClima = ServicioClimaOpenSource.getInstance();
      Pais ciudad = servicioClima.getCountry("Buenos Aires");
      model.addAttribute("ciudad", ciudad.getName());
			model.addAttribute("temperatura", ciudad.getTemperatura());
      model.addAttribute("sensacionTermica", ciudad.getSensacionTermica());
      model.addAttribute("viento", ciudad.getViento());
      model.addAttribute("descripcion", ciudad.getDescripcionCielo());
    } catch (IOException e) {
      model.addAttribute("error", "no se pudo obtener datos");
    }
    return "index"; //retorna el nombre de la vista a la cual quiero acceder
  }

	@GetMapping("/search_service")
  public String searchService(@RequestParam("query")String query, Model model) throws IOException {
    ServicioBusquedaZonasCaba servicioBusqueda = ServicioBusquedaZonasCaba.getInstance();
    //tengo qe traer las instancias
    InteresZonaSearch instancias = servicioBusqueda.listadoDeInteres(query);
    List<Instancia> instanciasList = instancias.getInstanciaPorNombre(query);
    /*
    System.out.println("las instancias encontradas son:" + instanciasList.size());
    for(int i = 0; i < instanciasList.size(); i++) {
      System.out.println("la instancia es:" + instanciasList.get(i).getNombre());
    }
    */
    //de una vez que me traiga todas las instancias, que sera ina lista
    // de cada lista quiero que tome el nombre de cada una de las instancias
    //el primer parametro _ listaInstancias debe coincidir en el front del html listaServicioPorNombre en el <tr ${listaInstancias}>
    model.addAttribute("listaInstancias", instanciasList);
    return "listaServicioPorNombre";
  }

  @PostMapping("/geo-location-position-search")
  @ResponseBody // por que al final este POSTMAPPING no devolvera una pantilla, devolvera DATOS, Y labura con datos que recibe del front
  // pero si quiero retornarlo en plantilla html con esa anotacion, el retorno del method NO debe ser solo string
  // debo retornar que devuelva un tipo de datos y a partir de qe devuelve un tipo de datos,
  // mediante otro metodo GET, lo redivisualizo a la plantilla que quiero que se muestre
  public Map<String,Object> procesarGeoLocation(@RequestBody GeoLocation geolocalizacion, Model model) throws IOException {
    double latitude = geolocalizacion.getLat();
    double longitude = geolocalizacion.getLng();
    String doubleToString = String.valueOf(latitude);
    String doubleToString2 = String.valueOf(longitude);
    ServicioGeoLocation servicioLocalizacion = ServicioGeoLocation.getInstance();
    SearchGeoLocation instancias = servicioLocalizacion.searchLocation(doubleToString, doubleToString2,"1000");
		List<Instancia> instanciasList = instancias.getInstancias();
    System.out.println("latitud:" + latitude);
    System.out.println("longitud:" + longitude);
		System.out.println("las instancias encontradas son:" + instanciasList.size());
    // ESTO NO VA POR QUE NO ES PARTE PARA UE SE VISUALICE EN PLANTILLA HTML
		//model.addAttribute("listaInstancias", instanciasList);
    //redirigo al metodo GETMAPPING del metodo listaServicioPorNombre
    // return "redirect:/listaServicioPorNombre";

    Map<String, Object> response = new HashMap<>();
    response.put("listaInstancias", instanciasList); // guardo el string (lo usara el metodo de abajo y tambien el js) y el objeto aca!
    response.put("redireccionlista", "/lista-Servicio-Por-Nombre"); // guardo un string para el js y el objeto aca

    return response; // con esto le digo que devuelve un objeto de tipo map y se lo pasa a javascript

  }
	//una vez que haya traido y analizado en
  //  el method de procesarGeoLocalizacion en el postmapping, lo que hago es devolverle
  // a javascript mediante un response, este response lo que haga es redirigir los tipos de datos con href
  // el href tendra el siguiente endpoint que es /listaServicioPorNombre
  // que sera este method getmapping
	@GetMapping("/lista-Servicio-Por-Nombre")
  //este requestparam "listaInstancias" es en el que se DEBERA ASOCIAR con el listaInstancias del HTML: listaServicioPorNombre
  // que esta en la tabla!
  public String listaServicioPorNombre(@RequestParam("listaInstancias") String listaInstanciasJson, Model model) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    //aca es para deserializar!
    List<Instancia> instanciasList = objectMapper.readValue(listaInstanciasJson, new TypeReference<List<Instancia>>(){});
    model.addAttribute("listaInstancias", instanciasList);
    return "listaServicioPorNombre";
  }

  @GetMapping("/custom-search")
  public String customSearch(Model model) throws IOException {
    //traer el getinstance
    ServiceCategories servicioCategorias = ServiceCategories.getInstance();
    Categoria categoriasDelServicio = servicioCategorias.getCategoriasTotales();
    List<Categories> categorias = categoriasDelServicio.getCategorias().stream().toList();

    model.addAttribute("listacategorias", categorias);
    return "listaServicioCustom";
  }

}
