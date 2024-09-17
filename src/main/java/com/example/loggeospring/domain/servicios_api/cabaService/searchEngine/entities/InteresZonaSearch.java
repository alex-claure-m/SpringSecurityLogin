package com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.entities;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*
*
* */

@Getter
@Setter
public class InteresZonaSearch {
  public int totalFull;
  public List<ClaseEncontrada> clasesEncontradas;
  public List<Instancia> instancias;
	/*
	==> siento que esta funcion es innecesaria ya que el response me trae la lista en si!
	public List<ClaseEncontrada> getClaseEncontradaPorNombre(String nombre){
    return clasesEncontradas.stream().filter(clase -> clase.getNombre().equals(nombre)).toList();
    // filtro aquellas clases en las cuales sea "iguak" al nombre que le paso por parametro
    // tengo una lista de clase
    // y de aca mapeo para que quede solo los "nombres" de las clases => .map(ClaseEncontrada::getNombre).toList()
    // esto ultimo es lo que no quiero
  }
  */
	public List<Instancia> getInstanciaPorNombre(String nombre) {
    return instancias.stream().filter(instancia -> instancia.getNombre().toLowerCase().contains(nombre.toLowerCase())).toList();
  }
}
