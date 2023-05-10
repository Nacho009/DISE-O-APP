// package edu.uclm.esi.ds.games.config;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import static org.springframework.security.config.Customizer.withDefaults;


// /*********************************************************************
//  *
//  * Class Name: WebSecurityConfig Author/s name: Gregorio Release/Creation date:
//  * 19/10/2022 Class description: Se encarga de configurar la seguridad en la web
//  * usando la implementación de Spring. Especifica el encargado del login y
//  * encriptación de contraseñas
//  *
//  **********************************************************************
//  */
// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfig {

//   @Autowired
//   private UserDetailsService userDetailsService;

//   @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             .authorizeHttpRequests((authz) -> authz
//                 .anyRequest().authenticated()
//             )
//             .httpBasic(withDefaults());
//         return http.build();
//     }

//   @Autowired
//   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//     auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//   }

// }
