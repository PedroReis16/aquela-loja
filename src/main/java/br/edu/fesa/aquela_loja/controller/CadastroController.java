package br.edu.fesa.aquela_loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.edu.fesa.aquela_loja.models.DTOs.NewUserDTO;
import br.edu.fesa.aquela_loja.models.DTOs.UserAddressDTO;
import br.edu.fesa.aquela_loja.models.DTOs.UserCardDTO;
import br.edu.fesa.aquela_loja.models.DTOs.UserDetailsDTO;

@Controller
@SessionAttributes("user")
public class CadastroController {

    @ModelAttribute("user")
    public NewUserDTO newUser() {
        NewUserDTO user = new NewUserDTO();
        user.setAddressDTO(new UserAddressDTO());
        return user;
    }

    @PostMapping("/new-user-address")
    public String setUserDetails(@ModelAttribute("user") NewUserDTO user, @RequestBody UserDetailsDTO userDetails) {

        // user.setUserDetails(userDetails);
        return "user-address";
    }

    @PostMapping("/new-user-card")
    public String setUserAddress(@ModelAttribute("user") NewUserDTO user, @RequestBody UserAddressDTO userAddress) {

        // user.setAddress(userAddress);
        return "user-card";
    }

    @PostMapping("/new-user")
    public String setUserCard(@ModelAttribute("user") NewUserDTO user, @RequestBody(required = false) UserCardDTO userCard) {

        if (userCard != null) {
            //Tratar o novo usuário
        }

        return "/";
    }
}
