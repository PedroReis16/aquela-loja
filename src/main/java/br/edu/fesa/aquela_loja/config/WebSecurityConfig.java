package br.edu.fesa.aquela_loja.config;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
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

import static br.edu.fesa.aquela_loja.models.enums.Permission.ADMIN_CREATE;
import static br.edu.fesa.aquela_loja.models.enums.Permission.ADMIN_DELETE;
import static br.edu.fesa.aquela_loja.models.enums.Permission.ADMIN_READ;
import static br.edu.fesa.aquela_loja.models.enums.Permission.ADMIN_UPDATE;
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
                .requestMatchers(GET, "/admin/**").hasAuthority(ADMIN_READ.name())
                .requestMatchers(POST, "/admin/**").hasAuthority(ADMIN_CREATE.name())
                .requestMatchers(PUT, "/admin/**").hasAuthority(ADMIN_UPDATE.name())
                .requestMatchers(DELETE, "/admin/**").hasAuthority(ADMIN_DELETE.name())
                //Autenticação das rotas de usuário
                .requestMatchers("/user/registration").permitAll()
                .requestMatchers("/user/delete").permitAll()
                .requestMatchers(GET, "/user/**").hasAnyRole(USER.name(), ADMIN.name())
                .requestMatchers(POST, "/user/**").hasAnyRole(USER.name(), ADMIN.name())
                .requestMatchers(PUT, "/user/**").hasAnyRole(USER.name(), ADMIN.name())
                .requestMatchers(DELETE, "/user/**").hasAnyRole(USER.name(), ADMIN.name())
                .anyRequest().permitAll()
                )
                .formLogin(form -> form
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/", true))
                .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/").permitAll()
                    .logoutSuccessUrl("/login?logout=true") // Redireciona após o logout
                    .invalidateHttpSession(true)     // Garante que a sessão seja invalidada
                    .clearAuthentication(true)       // Limpa a autenticação
                    .deleteCookies("JSESSIONID")     // Apaga o cookie JSESSIONID
                    .permitAll())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
