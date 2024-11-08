package br.edu.fesa.aquela_loja.controller;

import br.edu.fesa.aquela_loja.models.entity.AppUserModel;
import br.edu.fesa.aquela_loja.repository.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class CadastroController {

    @Autowired
    private IAppUserRepository appUserRepository;

//    @ModelAttribute("user")
//    public NewUserDTO newUser() {
//        NewUserDTO user = new NewUserDTO();
//        user.setAddressDTO(new UserAddressDTO());
//        return user;
//    }
//
//    @PostMapping("/new-user-address")
//    public String setUserDetails(@ModelAttribute("user") NewUserDTO user, @RequestBody UserDetailsDTO userDetails) {
//
//        // user.setUserDetails(userDetails);
//        return "user-address";
//    }
//
//    @PostMapping("/new-user-card")
//    public String setUserAddress(@ModelAttribute("user") NewUserDTO user, @RequestBody UserAddressDTO userAddress) {
//        // user.setAddress(userAddress);
//        return "user-card";
//    }
//
//    @PostMapping("/new-user")
//    public String setUserCard(@ModelAttribute("user") NewUserDTO user, @RequestBody(required = false) UserCardDTO userCard) {
//
//        if (userCard != null) {
//            //Tratar o novo usuário
//        }
//
//        AppUserModel userModel = AppUserModel.builder()
//                .username(user.getName())
//                .email(user.getEmail())
//                .password(user.getPassword())
//                .build();
//
//        appUserRepository.save(userModel);
//
//        return "/";
//    }
}
