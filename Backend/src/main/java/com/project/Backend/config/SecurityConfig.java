package com.project.Backend.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final UserDetailsService userDetailsService;

    @Lazy
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpSecurity filterChain(HttpSecurity http) throws Exception {
       return http
               .authorizeHttpRequests(auth -> auth
                       .requestMatchers("/v1/usuarios/**").hasRole("ADMIN")
                       .requestMatchers("/v1/productos/**").hasAnyRole("USER", "WAREHOUSE_MANAGER", "SALES")
                       .requestMatchers("/v1/almacenes/**").hasRole("ADMIN")
                       .requestMatchers("/v1/inventario/**").hasAnyRole("ADMIN", "WAREHOUSE_MANAGER")
                       .anyRequest().authenticated()
               )

                .httpBasic(Customizer.withDefaults());

    }
    @Autowired
    public void configureglobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
