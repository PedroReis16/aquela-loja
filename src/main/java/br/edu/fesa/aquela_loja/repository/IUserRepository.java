package br.edu.fesa.aquela_loja.repository;

import br.edu.fesa.aquela_loja.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {
}
