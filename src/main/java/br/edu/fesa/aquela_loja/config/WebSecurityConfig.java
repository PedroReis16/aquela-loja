package br.edu.fesa.aquela_loja.config;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import static br.edu.fesa.aquela_loja.models.enums.Role.ADMIN;
import static br.edu.fesa.aquela_loja.models.enums.Role.USER;
import br.edu.fesa.aquela_loja.service.AppUserService;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    @Autowired
    private final AppUserService appUserService;

    @Bean
    public UserDetailsService userDetailsService() {
        return appUserService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.ignoringRequestMatchers(toH2Console()).disable())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(httpSecurityHeadersConfigurer -> {
                    httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
                })
                .authorizeHttpRequests(auth -> auth
                //Autenticação das páginas de administrador e usuário
                .requestMatchers("/administrador/**").hasRole(ADMIN.name())
                .requestMatchers("/usuario/**").hasAnyRole(USER.name(), ADMIN.name())
                //Autenticação das rotas de administrador
                .requestMatchers("/estoque/**").hasRole(ADMIN.name())
                .requestMatchers("/product/**").hasRole(ADMIN.name())
                //Autenticação das rotas de usuário
                .requestMatchers("/cart-auth").authenticated()
                .requestMatchers("/user/registration").permitAll()
                .requestMatchers("/user/delete").permitAll()
                .requestMatchers("/user/documents").permitAll()
                .requestMatchers("/user/emails").permitAll()
                .requestMatchers("/user/name").permitAll()
                .requestMatchers("/user/**").hasAnyRole(USER.name(), ADMIN.name())
                .anyRequest().permitAll()
                )
                .formLogin(form -> form
                .loginPage("/login").permitAll()
                .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
                )
                .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/").permitAll()
                .invalidateHttpSession(true) // Garante que a sessão seja invalidada
                .clearAuthentication(true) // Limpa a autenticação
                .deleteCookies("JSESSIONID")
                .deleteCookies("CartItems")
                .permitAll())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
