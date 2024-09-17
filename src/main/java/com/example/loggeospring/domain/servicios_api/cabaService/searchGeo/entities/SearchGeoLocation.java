package com.example.loggeospring.domain.servicios_api.cabaService.searchGeo.entities;

import com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.entities.Instancia;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchGeoLocation  {
  public int total;
  public List<Instancia> instancias;




}
