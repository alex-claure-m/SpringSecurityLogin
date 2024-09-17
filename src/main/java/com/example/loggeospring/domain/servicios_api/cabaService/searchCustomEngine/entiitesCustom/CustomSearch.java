package com.example.loggeospring.domain.servicios_api.cabaService.searchCustomEngine.entiitesCustom;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomSearch {
  public String claseid;
  public String clse;
  public String fechaAlta;
  public Ubicacion ubicacion;
  public String id;
  public String dir_normalizada;
  public List<Contenido> contenido;
}
