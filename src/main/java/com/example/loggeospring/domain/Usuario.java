package com.example.loggeospring.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario extends PersistenceId {


  private String username;
  private String password;

  //se agrega para que sea bidireccional y me pueda mostrar datos en el front perfil
  //@OneToOne(fetch= FetchType.LAZY , cascade = CascadeType.ALL)
  //@JoinColumn(name = "persona_id", referencedColumnName = "id")
  /*
  * el mapped basicamente para las relaciones bidireccionales
   aclara que el campo usuarioAsociado sera el mapped de la clase Usuario
   y que justamente el mappedby aclara la clase PERSONA sera la dueña de esa relacion
   ya que considero que para un usuairo primero hay que crearse una persona
   siguiente:
   * 		si creo una relacion bidireccional con mappedby , del lado de la persona, estara el joincolumn
  * */

  @OneToOne(mappedBy = "usuarioAsociado", cascade = CascadeType.ALL)
  private Persona persona;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
