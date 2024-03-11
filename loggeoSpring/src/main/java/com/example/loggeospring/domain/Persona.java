package com.example.loggeospring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Entity
public class Persona extends PersistenceId {

  private String nombre;
  private String apellido;
  private String dni;
  private String email;
  private String telefono;
  private LocalDate fechaNacimiento;

  @OneToOne
  @JoinColumn(name = "usuario_id", referencedColumnName = "id")
  private Usuario usuarioAsociado;
}
