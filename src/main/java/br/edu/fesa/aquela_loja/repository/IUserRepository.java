package br.edu.fesa.aquela_loja.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fesa.aquela_loja.models.entity.UserModel;
import br.edu.fesa.aquela_loja.models.enums.Role;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByDocument(String document);

    Optional<UserModel> findByEmail(String email);

    Optional<List<UserModel>> findByRole(Role admin);


}
