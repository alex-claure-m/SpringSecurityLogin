package com.example.loggeospring;

import com.example.loggeospring.domain.Usuario;
import com.example.loggeospring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
* esta clase basicamente implementa la interfaz UserDetailsService de spring security
* para cargar los datos durante la auntenticacion
* si quiero verificar los ROLES en usuario tambien tendria que toquetear esta parte
* */
@Service
public class UsuarioDetailServiceImp implements UserDetailsService {
  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByUsername(username);
    if (usuario == null) {
      throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    }
    //return new User(usuario.getUsername(), usuario.getPassword(), new ArrayList<>());
    return User.withUsername(usuario.getUsername())
        .password(usuario.getPassword())
        .build();
  }



}
