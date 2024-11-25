package br.edu.fesa.aquela_loja.controller.user_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.fesa.aquela_loja.models.dto.card.NewUserCardDto;
import br.edu.fesa.aquela_loja.models.dto.card.UpdateUserCardDto;
import br.edu.fesa.aquela_loja.service.user_services.UserCardService;

@Controller
@RequestMapping("/user")
public class UserCardController {

    @Autowired
    private UserCardService service;

    @PostMapping("/new-card")
    public ResponseEntity<Void> newUserCard(@RequestBody NewUserCardDto newCard) {
        service.addNewCard(newCard);

        // return "redirect:/usuario/meus-cartoes";
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update-card/{id}")
    public ResponseEntity<Void> updateUserCard(@PathVariable Long id, @RequestBody UpdateUserCardDto updatedCard) {
        service.updateUserCard(id, updatedCard);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete-card/{id}")
    public String deleteUserCard(@PathVariable Long id) {

        service.deleteUserCard(id);

        return "redirect:/usuario/meus-cartoes";
    }

}
