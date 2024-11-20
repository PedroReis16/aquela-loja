package br.edu.fesa.aquela_loja.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fesa.aquela_loja.models.entity.UserAddressModel;
import br.edu.fesa.aquela_loja.models.entity.UserModel;

public interface IUserAddressRepository extends JpaRepository<UserAddressModel, Long> {

    Optional<List<UserAddressModel>> findByAppUser(UserModel user);
}
