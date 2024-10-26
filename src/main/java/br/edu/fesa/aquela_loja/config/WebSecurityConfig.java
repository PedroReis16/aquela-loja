package br.edu.fesa.aquela_loja.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/produtos", "/fragments/**", "/styles/**").permitAll()
                .anyRequest().authenticated()
                )
                .formLogin(form -> form.defaultSuccessUrl("/", true))
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .build();
    }
}
