package com.example.loggeospring.repository;

import com.example.loggeospring.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//esta interface es para buscar a la PERSONA que hay en la base de datos usando una implementacion de springboot
// que es JPAREPOSITORY, no es NECESARIO pero es noremal que se use (ver la firma de cada funcion)
// si no queres usar esta extension, podes implementar tu propio DAO (mas paja)
public interface PersonaRepository extends JpaRepository<Persona,Long> {
	//Persona findByUsername(String name); // esta funcion es para buscar por nombre de usuario la Persona asociada -- but no sirve
  Persona saveAndFlush(Persona persona);

  //fuerzo a que lo busque por la tabla de datos a usuario asociado por el username
  @Query("select p from Persona p where p.usuarioAsociado.username = :username")
  Persona findByUsername(@Param("username") String username);

  @Query("SELECT p FROM Persona p WHERE p.usuarioAsociado.id = :usuarioId")
  Persona findByUsuarioId(@Param("usuarioId") Long usuarioId);
}
