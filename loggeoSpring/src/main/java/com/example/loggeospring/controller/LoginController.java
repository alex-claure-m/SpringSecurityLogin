package com.example.loggeospring.controller;

import com.example.loggeospring.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
  private final UsuarioRepository usuarioRepository;

  public LoginController(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }
 /*
  @GetMapping("/login")
  public String login() {
    return "login";
  }
  */
  @GetMapping("/login")
  //este name=error, corresponde al error que esta en el html , recorda que debe ser el mismo NOMBRE
  public String login(@RequestParam(name = "error", required = false) String error, Model model) {
    if (error != null) {
      model.addAttribute("error", "¡Nombre de usuario o contraseña incorrectos!");
    }
    return "login";
  }

  @GetMapping("/dashboard")
  public String dashboard() {
    return "index";
  }
}
