package com.example.loggeospring.repository;

import com.example.loggeospring.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona,Long> {

  Persona saveAndFlush(Persona persona);
}
