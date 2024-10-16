package com.example.loggeospring.security;

import com.example.loggeospring.PasswordEncoderConfig;
import com.example.loggeospring.UsuarioDetailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
* esta clase es la configuracion de spring segurity, maneja las solicitudes entrantes
* como por ejemplo el hecho de que defino las reglas de seguridad

* */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final UsuarioDetailServiceImp usuarioDetailsService;

  @Autowired
  private PasswordEncoderConfig bCryptPasswordEncoder;

  public SecurityConfig(UsuarioDetailServiceImp usuarioDetailsService) {
    this.usuarioDetailsService = usuarioDetailsService;
  }

  /*
Permite el acceso a la ruta /login” para todos y requiere autenticación para todas las demás rutas.
También configura el inicio de sesión y el cierre de sesión.
le da mucha mas potencial ya cualquier persona que quiere acceder a una ruta especial
pasa primero por el loggin
// es clave esta clase en SPRING SEGURITY
 */

  // bean es propio de SBoot, me permite crear e iniciarlizar valores personalizadas que luego
  // lo puedo inyectar en otros componentes

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/login", "/register/**", "/confirm-perfil").permitAll() //permito acceso a las rutas, en este caso a /login y /register sin pedir autenticacion
            .requestMatchers("/js/**", "/css/**", "/images/**").permitAll() // permito el acceso a los recursos sin pedir autenticacion
            .anyRequest().authenticated()) // para todos los demas rutas -endpoint, pido autenticacion
        .formLogin(formLogin -> formLogin // esto seria el manejo del formulario dell ogin
            .loginPage("/login") // para la seguridad, defino la ruta donde se hara el login
            .failureUrl("/login?error=true") // para redirigir al user si la autentitcacion falla
            .defaultSuccessUrl("/dashboard",true) // a donde va a redirigir luego de aceptar las credenciales
            .permitAll()) // permito el acceso al /login para todos los users esten o no logeados
        //mantener esta estrucutra del logout!
        .logout(logout -> logout // el manejo del logout es muy similar al login
            .logoutUrl("/logout") // la direccion que debe tener cuando se quiere desloggear
            .logoutSuccessUrl("/login?logout=true") // cuando se cierra una sesion de manera exitosa
            .permitAll())
        .build();

  }

  @Bean
  public UserDetailsService userDetailsService() {
    return usuarioDetailsService;
  }

  // cojfiguro el spring security para cargar al usuario durante la autenticacion
  //y de ahi usar el usuarioDetailsService y su passwordEncoder
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(usuarioDetailsService).passwordEncoder(bCryptPasswordEncoder.passwordEncoder());
  }


  /*
  * los beans instancian , configuran e inicializan un nuevo objeto a la hora de ejecutar con spring
  * y en este caso ignoro las solicitudes que vienen de los css, js y imagenes-> son estaticos que no necesitan seguridad
  */
  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/images/**");
  }
}