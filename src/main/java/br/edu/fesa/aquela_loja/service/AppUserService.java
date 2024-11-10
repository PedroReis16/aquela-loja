package br.edu.fesa.aquela_loja.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.fesa.aquela_loja.repository.IAppUserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private IAppUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var optUser = repository.findByEmail(email);

        System.out.println("Usuário capturado: " + email);

        if (optUser.isPresent()) {
            var user = optUser.get();
            return User.builder()
                    .username(user.getUsername())
                    .roles(user.getRole().name())
                    .password(user.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException(email);
        }
    }
}