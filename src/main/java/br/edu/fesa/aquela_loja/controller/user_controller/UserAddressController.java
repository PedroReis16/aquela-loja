package br.edu.fesa.aquela_loja.controller.user_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.fesa.aquela_loja.models.dto.address.NewUserAddressDto;
import br.edu.fesa.aquela_loja.models.dto.address.UserAddressDto;
import br.edu.fesa.aquela_loja.service.user_services.UserAddressService;

@Controller
@RequestMapping("/user")
public class UserAddressController {

    @Autowired
    private UserAddressService service;

    @PostMapping("/new-address")
    public String newUserAddress(@ModelAttribute("newUserAddress") NewUserAddressDto newUserAddress) {
        service.addNewAddress(newUserAddress);

        return "redirect:/usuario/meus-dados";
    }

    @PostMapping("/update-address")
    public ResponseEntity<Void> updateUserAddress(@RequestBody UserAddressDto updatedAddress) {
        service.updateUserAddress(updatedAddress);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/update-address/{id}")
    public ResponseEntity<Void> setDefaultAddress(@PathVariable Long id) {

        service.setDefaultAddress(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete-address/{id}")
    public ResponseEntity<Void> deleteUserAddress(@PathVariable Long id) {
        service.deleteUserAddress(id);

        return ResponseEntity.ok().build();
    }
}
