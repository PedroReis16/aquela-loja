package br.edu.fesa.aquela_loja.service;

import br.edu.fesa.aquela_loja.models.entity.UserModel;
import br.edu.fesa.aquela_loja.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private IUserRepository repository;

    public void createClient(UserModel user) {
        repository.save(user);
    }

}
