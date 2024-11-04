package br.edu.fesa.aquela_loja.service;

import br.edu.fesa.aquela_loja.models.entity.AppUserModel;
import br.edu.fesa.aquela_loja.repository.IAppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private IAppUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optUser = repository.findByUsername(username);

        System.out.println("Usuário capturado: " +username);

        if (optUser.isPresent()) {
            var user = optUser.get();
            return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
