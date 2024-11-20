package br.edu.fesa.aquela_loja.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fesa.aquela_loja.models.entity.UserCardModel;
import br.edu.fesa.aquela_loja.models.entity.UserModel;

@Repository
public interface IUserCardRepository extends JpaRepository<UserCardModel, Long> {

    Optional<List<UserCardModel>> findByAppUser(UserModel appUser);

}
