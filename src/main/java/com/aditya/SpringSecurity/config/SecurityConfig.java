package com.aditya.SpringSecurity.config;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http.csrf(customizer->customizer.disable())
              .authorizeHttpRequests(request->request
                      .requestMatchers("register","login")
                      .permitAll()
                      .anyRequest().authenticated())
              .httpBasic(Customizer.withDefaults())
              .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
               .build();
        // httpSecurity.formLogin(Customizer.withDefaults());
    }
//    @Bean
//    public UserDetailsService userDetailsService()
//    {
//        UserDetails user1= User
//                .withDefaultPasswordEncoder()
//                .username("prince")
//                .password("pk@123")
//                .roles("USER")
//                .build();
//
//        UserDetails user2= User
//                .withDefaultPasswordEncoder()
//                .username("Sager")
//                .password("Sager@11")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1,user2);
//    }

      @Bean
    public AuthenticationProvider authenticationProvider()
      {
          DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
          provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
          provider.setUserDetailsService(userDetailsService);
          return provider;
      }
      @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config ) throws Exception
      {
          return config.getAuthenticationManager();

      }

}
