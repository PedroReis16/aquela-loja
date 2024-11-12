package br.edu.fesa.aquela_loja.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fesa.aquela_loja.models.entity.AddressModel;
import br.edu.fesa.aquela_loja.models.entity.AppUserModel;

public interface IAddressRepository extends JpaRepository<AddressModel, Long> {

    Optional<List<AddressModel>> findByAppUser(AppUserModel user);
}
