package br.edu.fesa.aquela_loja.service.user_services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.edu.fesa.aquela_loja.models.dto.card.NewUserCardDto;
import br.edu.fesa.aquela_loja.models.dto.card.UpdateUserCardDto;
import br.edu.fesa.aquela_loja.models.dto.card.UserCardDto;
import br.edu.fesa.aquela_loja.models.entity.UserModel;
import br.edu.fesa.aquela_loja.models.entity.UserCardModel;
import br.edu.fesa.aquela_loja.repository.IUserCardRepository;

@Service
public class UserCardService {

    @Autowired
    private UserService userService;

    @Autowired
    private IUserCardRepository repository;

    public List<UserCardDto> getUserCard() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserModel appUser = userService.findUserByEmail(auth.getName());

        List<UserCardModel> userCards = repository.findByAppUser(appUser).get();

        List<UserCardDto> result = new ArrayList<>();

        for (UserCardModel card : userCards) {
            UserCardDto userCard = new UserCardDto(card);
            result.add(userCard);
        }

        return result;
    }

    public void addNewCard(NewUserCardDto newCard) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserModel appUser = userService.findUserByEmail(auth.getName());

        UserCardModel card = UserCardModel.builder()
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
        UserCardModel card = repository.findById(id).get();

        card.setHolderName(updatedCard.getHolderName());
        card.setNumber(updatedCard.getExpirationDate());

        repository.save(card);
    }

}
