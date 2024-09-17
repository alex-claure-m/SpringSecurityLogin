package com.example.loggeospring.domain.servicios_api.cabaService.searchCategorias.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Categories {
  public String nombre; //este es el que muestro al front
  public String nombre_normalizado; // este es para condiciones en el back
  public String fuente;
}
