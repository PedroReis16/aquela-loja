package br.edu.fesa.aquela_loja.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fesa.aquela_loja.models.entity.AppUserModel;
import br.edu.fesa.aquela_loja.models.entity.PaymentCardModel;

@Repository
public interface IPaymentCardRepository extends JpaRepository<PaymentCardModel, Long> {

    Optional<List<PaymentCardModel>> findByAppUser(AppUserModel appUser);

}
