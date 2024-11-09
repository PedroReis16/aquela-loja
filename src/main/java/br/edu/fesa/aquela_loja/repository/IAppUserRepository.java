package br.edu.fesa.aquela_loja.repository;

import br.edu.fesa.aquela_loja.models.entity.AppUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAppUserRepository extends JpaRepository<AppUserModel, Long> {

    Optional<AppUserModel> findByEmail(String email);
}
