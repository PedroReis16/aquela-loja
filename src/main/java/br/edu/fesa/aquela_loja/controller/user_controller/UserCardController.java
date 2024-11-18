package br.edu.fesa.aquela_loja.controller.user_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.fesa.aquela_loja.models.dto.NewUserCardDto;
import br.edu.fesa.aquela_loja.models.dto.UpdateUserCardDto;
import br.edu.fesa.aquela_loja.service.user_services.UserCardService;

@Controller
@RequestMapping("/user")
public class UserCardController {

    @Autowired
    private UserCardService service;

    @PostMapping("/new-card")
    public String newUserCard(@ModelAttribute("newUserAddress") NewUserCardDto newCard) {
        service.addNewCard(newCard);

        return "redirect:/usuario/meus-cartoes";
    }

    @PostMapping("/update-card/{id}")
    public String updateUserCard(@PathVariable Long id, @RequestBody UpdateUserCardDto updatedCard) {
        service.updateUserCard(id, updatedCard);

        return "redirect:/usuario/meus-cartoes";
    }

    @PostMapping("/delete-card/{id}")
    public String deleteUserCard(@PathVariable Long id) {

        service.deleteUserCard(id);

        return "redirect:/usuario/meus-cartoes";
    }

}
