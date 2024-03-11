package com.example.loggeospring.repository;

import com.example.loggeospring.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

//para buscar en la base de datos
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  Usuario findByUsername(String username);

  Usuario saveAndFlush(Usuario usuario);

}
