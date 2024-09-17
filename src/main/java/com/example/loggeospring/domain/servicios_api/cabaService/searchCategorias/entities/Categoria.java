package com.example.loggeospring.domain.servicios_api.cabaService.searchCategorias.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Categoria {
  public List<Categories> categorias;
  public int cantidad_categorias;
}
