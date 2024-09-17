package com.example.loggeospring.controller;

import com.example.loggeospring.repository.PersonaRepository;
import com.example.loggeospring.repository.UsuarioRepository;
import com.example.loggeospring.domain.Persona;
import com.example.loggeospring.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class PersonaController {

  @Autowired
  private PersonaRepository personaRepository;
  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;


  //la ruta debe coincidir con href de register.html de -registrese si no tiene usuario-
  @GetMapping("/register")
  public ModelAndView register() {
    ModelAndView modelAndView = new ModelAndView("register");
    modelAndView.addObject("persona", new Persona());
    return modelAndView;
  }
  //la ruta debe coincidir con el method del html register. en este caso registration
  @PostMapping("/register")
  //el model atribute es toda la clase/OBJETO Persona cuando recibe del formulario del html
  // en vez de hacer @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido, etc
  // paso todo el MODELO del ATRIBUTO
  public String createNewPersona(@ModelAttribute Persona persona,
                                 @RequestParam String username,
                                 @RequestParam String password) {
    Usuario newUser = new Usuario();
    newUser.setUsername(username);
    // newUser.setPassword(password);
    newUser.setPassword(bCryptPasswordEncoder.encode(password));


    usuarioRepository.saveAndFlush(newUser);
    persona.setUsuarioAsociado(newUser);
    personaRepository.saveAndFlush(persona);

    System.out.println("Datos recibidos:");
    System.out.println("Nombre: " + persona.getNombre());
    System.out.println("Apellido: " + persona.getApellido());
    System.out.println("DNI: " + persona.getDni());
    System.out.println("Email: " + persona.getEmail());
    System.out.println("Teléfono: " + persona.getTelefono());
    System.out.println("Fecha de Nacimiento: " + persona.getFechaNacimiento());
    System.out.println("Nombre de usuario: " + username);
    System.out.println("Contraseña: " + password);

    return "login";
  }
}
