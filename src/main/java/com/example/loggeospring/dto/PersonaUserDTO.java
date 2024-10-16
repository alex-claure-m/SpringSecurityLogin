package com.example.loggeospring.dto;

import java.time.LocalDate;


public class PersonaUserDTO {
  private String nombre;
  private String apellido;
  private String username;
  private String email;


  public PersonaUserDTO(String nombre, String apellido, String username, String email) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.username = username;
    this.email = email;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

}
