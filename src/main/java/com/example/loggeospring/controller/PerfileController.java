package com.example.loggeospring.controller;

import com.example.loggeospring.UsuarioDetailServiceImp;
import com.example.loggeospring.domain.Persona;
import com.example.loggeospring.domain.Usuario;
import com.example.loggeospring.domain.servicios_api.weather.ServicioClimaOpenSource;
import com.example.loggeospring.domain.servicios_api.weather.entities.Pais;
import com.example.loggeospring.dto.PersonaUserDTO;
import com.example.loggeospring.repository.PersonaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
public class PerfileController  {

  //esta esta interfaz por que va a buscar al usuario, necesito este servicio
  @Autowired
  private UsuarioDetailServiceImp usuarioServicio;

  @Autowired
  private PersonaRepository personaRepository;

  @Qualifier("userDetailsService")
  @Autowired
  private UserDetailsService userDetailsService;


  @GetMapping("/index/perfile")
  public String mostrarPerfil(@AuthenticationPrincipal UserDetails userDetails, Model model){

    if(userDetails!= null) {
      model.addAttribute("username", userDetails);

      String username = userDetails.getUsername();
      System.out.println("username: " + username);
      Usuario usuario = usuarioServicio.obtenerUsuarioPorUsername(username);
      System.out.println("usuario: " + usuario);
      // se tuvo que agregar un findbyusuarioid, para asociar al usuario a la persona en cuestion
      Persona persona = personaRepository.findByUsuarioId(usuario.getId());
	 		// este findbyrepository funciona con inyeccion , un @autowired que debe estar al ppio
      // como justamente esta funcion no esta presente en la extension jparepository de personarepository
      // se la declaro y definio -> que busque mediante un query a la base de datos y lo traiga!
      if (usuario != null ) {
        PersonaUserDTO personaUserDTO = new PersonaUserDTO(
            persona.getNombre(),
            persona.getApellido(),
            usuario.getUsername(),
            persona.getEmail()
        );
        if( personaUserDTO != null) {
          model.addAttribute("personaUserDTO_nombre", personaUserDTO.getNombre());
          model.addAttribute("personaUserDTO_apellido", personaUserDTO.getApellido());
          model.addAttribute("personaUserDTO_email", personaUserDTO.getEmail());
          model.addAttribute("personaUserDTO_username", personaUserDTO.getUsername());
          model.addAttribute("fechaNacimiento", persona.getFechaNacimiento());
          model.addAttribute("dni", persona.getDni());
        }else{
          model.addAttribute("error", "No se pudo cargar el perfil");
        }

      }
      else{
        System.out.println("usuario no es nulo");
      }
    }

    try{
      ServicioClimaOpenSource servicioClima = ServicioClimaOpenSource.getInstance();
      Pais ciudad = servicioClima.getCountry("Buenos Aires");
      model.addAttribute("ciudad", ciudad.getName());
      model.addAttribute("temperatura", ciudad.getTemperatura());
      model.addAttribute("sensacionTermica", ciudad.getSensacionTermica());
      model.addAttribute("viento", ciudad.getViento());
      model.addAttribute("descripcion", ciudad.getDescripcionCielo());
    }catch (Exception e){
      model.addAttribute("error", "no se pudo obtener datos");
    }
    return "perfile";
}

  @PostMapping("/confirm-perfil")
  @ResponseBody //respuesta json
  public ResponseEntity<String> confirmPerfil(@AuthenticationPrincipal UserDetails userDetails, @RequestBody PersonaUserDTO personaDTO) {
    //el requestBody se refiere al cuerpo de la peticion, que es el json que envia el front
		// (vs de un ModelAttribute que vienen de la forma traidcional de un html , sin que sea en otro formato como json)

		try{
      String username = userDetails.getUsername();
      usuarioServicio.actualizarPerfil(username,personaDTO);
      return ResponseEntity.ok("Perfil actualizado con éxito");

    } catch (EntityNotFoundException | UsernameNotFoundException ex) {
      // Manejar las excepciones y devolver una respuesta de error
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    } catch (Exception ex) {
      // Manejar cualquier otro tipo de excepción
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el perfil");
    }
  }
  /*
    // si necesito modificar desde el front, hacia el backend, tengo que pasar por un DTO
    // es clave un dto para acumular todas los atribtos y guardarlo en variables para luego
    // pasarlo a sus clases del domain y de ahi a sus base de datos
    String nombre = personaDTO.getNombre();
    String apellido = personaDTO.getApellido();
    String email = personaDTO.getEmail();
    String usuario = personaDTO.getUsername();


    Map<String, Object> response = new HashMap<>();

    response.put("status", "success");
    response.put("message", "Perfil actualizado correctamente");

    return response;
  }*/

}
