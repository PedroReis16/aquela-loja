package br.edu.fesa.aquela_loja.repository;

import br.edu.fesa.aquela_loja.models.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long> {
}
