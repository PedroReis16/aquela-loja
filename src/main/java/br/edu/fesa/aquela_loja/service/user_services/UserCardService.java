package br.edu.fesa.aquela_loja.service.user_services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.edu.fesa.aquela_loja.models.dto.NewUserCardDto;
import br.edu.fesa.aquela_loja.models.dto.UpdateUserCardDto;
import br.edu.fesa.aquela_loja.models.dto.UserCardDto;
import br.edu.fesa.aquela_loja.models.entity.AppUserModel;
import br.edu.fesa.aquela_loja.models.entity.PaymentCardModel;
import br.edu.fesa.aquela_loja.repository.IPaymentCardRepository;

@Service
public class UserCardService {

    @Autowired
    private UserService userService;

    @Autowired
    private IPaymentCardRepository repository;

    public List<UserCardDto> getUserCard() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUserModel appUser = userService.findUserByEmail(auth.getName());

        List<PaymentCardModel> userCards = repository.findByAppUser(appUser).get();

        List<UserCardDto> result = new ArrayList<>();

        for (PaymentCardModel card : userCards) {
            UserCardDto userCard = new UserCardDto(card);
            result.add(userCard);
        }

        return result;
    }

    public void addNewCard(NewUserCardDto newCard) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUserModel appUser = userService.findUserByEmail(auth.getName());

        PaymentCardModel card = PaymentCardModel.builder()
                .holderName(newCard.getHolderName())
                .number(newCard.getNumber())
                .expirationDate(newCard.getExpirationDate())
                .cvv(newCard.getCvv())
                .appUser(appUser)
                .build();

        repository.save(card);
    }

    public void deleteUserCard(Long id) {
        repository.deleteById(id);
    }

    public void updateUserCard(Long id, UpdateUserCardDto updatedCard) {
        PaymentCardModel card = repository.findById(id).get();

        card.setHolderName(updatedCard.getHolderName());
        card.setNumber(updatedCard.getExpirationDate());

        repository.save(card);
    }

}
