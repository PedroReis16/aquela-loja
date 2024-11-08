package br.edu.fesa.aquela_loja.repository;

import br.edu.fesa.aquela_loja.models.entity.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<AddressModel, Long> {
}
