package br.edu.fesa.aquela_loja.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fesa.aquela_loja.models.entity.AppUserModel;

@Repository
public interface IAppUserRepository extends JpaRepository<AppUserModel, Long> {

    Optional<AppUserModel> findByDocument(String document);

    Optional<AppUserModel> findByEmail(String email);


}
