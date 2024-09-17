package com.example.loggeospring.controller;


import com.example.loggeospring.domain.operationsSearchCustom.Operacion;
import com.example.loggeospring.domain.servicios_api.cabaService.searchCustomEngine.ServicioCustomBusqueda;
import com.example.loggeospring.domain.servicios_api.cabaService.searchCustomEngine.entiitesCustom.CustomSearch;
import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.ServicioBusquedaZonasCaba;
import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.entities.Instancia;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchCustonController {

  @PostMapping("/search-service-custom")
  @ResponseBody
  //los RequestParam son los parametros que recibe del html - listaServicioCustom
  // tiene que ser el mismo name que tiene en el html y traerlos en ("xxx") por que sino, no lo toma
  public Map<String,Object> getListaServicioCustom(@RequestParam("servicio") String servicio,
                                                   @RequestParam("categoria") String categoria,
                                                   @RequestParam("direccion") String direccion, Model model) throws IOException {
    Operacion operacion = new Operacion();

    ServicioBusquedaZonasCaba servicioBusqueda = ServicioBusquedaZonasCaba.getInstance(); // put string busqueda
    ServicioCustomBusqueda servicioCustom = ServicioCustomBusqueda.getInstance(); // put id -- ex: dependencias_culturales|547
    //traer el getinstance
    //puntos a realizar
    // una vez que traiga el servicio Busqueda por string -> debo ver el ID de la CLASEINSTANCIA y hacerle put al SERVICIOCUSTOM
    List<CustomSearch> listaCustomizadas = operacion.obtenerListaDeServiciosCustomizados(servicioBusqueda, servicioCustom, servicio,categoria,direccion);
    //model.addAttribute("listaInstancias", instanciasList);
    Map<String,Object> response = new HashMap<>();
    response.put("listaCustomizadas", listaCustomizadas);
    return response;
  }

  @GetMapping("/response-custom-search")
  public String responseCustomSearch(Model model) {
    ObjectMapper objectMapper = new ObjectMapper();
    //List<Instancia> instanciasList = objectMapper.readValue(listaInstanciasJson, new TypeReference<List<Instancia>>(){});
    return "response-custom-search";
  }

}
