package com.example.loggeospring;

import com.example.loggeospring.domain.Persona;
import com.example.loggeospring.domain.Usuario;
import com.example.loggeospring.dto.PersonaUserDTO;
import com.example.loggeospring.repository.PersonaRepository;
import com.example.loggeospring.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
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
* si quiero ademas que sea el intermediario de un DTO va aca
* ----> cuando del front quiere modificar algo, primero va al DTO
* 		(se guarda la info del front a un objeto dto) y del DTO a esta clase,
* 		aca modifica con nuevos datos del DTO a las clases del domain
* */
@Service
public class UsuarioDetailServiceImp implements UserDetailsService {
  // el autowired basicamente es para cargar los datos a la hora de la ejecucion
  // basicamente inyecto dependencias para evitar escribir un new class() etc etc etc
  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private PersonaRepository repositorioPersona;


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


  public Usuario obtenerUsuarioPorUsername(String username) {
    Usuario user= usuarioRepository.findByUsername(username);
        if(user == null){
          throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }else
          return user;
  }

  //
  public void actualizarPerfil(String username, PersonaUserDTO perfilDTO){
    // primero busco al usuario en cuestion, que es el que estara loggeado
    Usuario user = usuarioRepository.findByUsername(username);
    // luego busco a la persona asociada al usuario
    if(user !=null){
      Persona persona = repositorioPersona.findByUsername(username); // me deberia traer la persona solo con el username asociado a persona
     	if(persona !=null){
         persona.setNombre(perfilDTO.getNombre());
         persona.setApellido(perfilDTO.getApellido());
         persona.setEmail(perfilDTO.getEmail());

        if(perfilDTO.getUsername() !=null && !perfilDTO.getUsername().isEmpty()) {
          user.setUsername(perfilDTO.getUsername()); // ya que en esta clase guardara del DTO a la clase del DOMAIN
        }
         repositorioPersona.saveAndFlush(persona);
         usuarioRepository.saveAndFlush(user);
      }else{
         throw new EntityNotFoundException("la persona no fue encontrada para el usuario " + username);
      }
    } else{
      throw new UsernameNotFoundException("el usuario no fue encontrado");
    }
  }

	public void changePass(String username, String newPass) {
    Usuario user = usuarioRepository.findByUsername(username);
    if (user != null) {
      user.setPassword(newPass);
      usuarioRepository.saveAndFlush(user);
    } else {
      throw new UsernameNotFoundException("el usuario no fue encontrado");
    }
  }
}
