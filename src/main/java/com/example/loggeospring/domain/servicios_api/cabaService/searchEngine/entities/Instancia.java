package com.example.loggeospring.domain.servicios_api.cabaService.searchEngine.entities;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

// ya no recuerdo porque este serializable .... -- investigar --
public class Instancia implements Serializable {
  private static final long serialVersionUID = 1L;
  public String id;
	public String nombre;
  public String clase; // esta es la clase que en el front es CATEGORIA
  public String headline;

}
