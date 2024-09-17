package com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClaseEncontrada {
  public String nombreId; // seria el nombre de la categorizacion con
  public String nombre; //
  public String nombreNormalizador; // nombre normalizado , algo que no deberia mostrarse al user como rta final

}
