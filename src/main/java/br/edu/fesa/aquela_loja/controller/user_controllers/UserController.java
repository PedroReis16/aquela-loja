package br.edu.fesa.aquela_loja.controller.user_controllers;

import br.edu.fesa.aquela_loja.models.dto.address.UserAddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import br.edu.fesa.aquela_loja.models.dto.NewUserDto;
import br.edu.fesa.aquela_loja.models.dto.UserDto;
import br.edu.fesa.aquela_loja.models.entity.UserModel;
import br.edu.fesa.aquela_loja.service.user_services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes("user")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/registration")
    public String registration(HttpServletRequest request, NewUserDto newUserDto) {
        userService.createNewUser(newUserDto);
        userService.authWithHttpServletRequest(request, newUserDto.getEmail(), newUserDto.getPassword());
        //  String username, String password
        return "redirect:/";
    }


    @PostMapping(value = "/edit")
    public String editUserDetails(@ModelAttribute UserDto updatedUser) {

        userService.updateUser(updatedUser);

        return "redirect:/usuario/meus-dados";
    }

    @GetMapping(value = "/delete")
    public String deleteUser(HttpServletRequest request, HttpServletResponse response) {
        userService.deleteUser(request, response);

        return "redirect:/";
    }


    @GetMapping("/documents")
    public ResponseEntity<Void> isDocumentAvailable(@RequestParam String document) {
        UserDto user = userService.FindUserByDocument(document);

        if (user != null) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/emails")
    public ResponseEntity<Void> isEmailAvailable(@RequestParam String email) {
        UserDto user = userService.FindUserByEmail(email);

        if (user != null) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name")
    public ResponseEntity<String> getAuthenticatedUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        if (!username.contains("anonymousUser")) {
            UserModel user = userService.findUserByEmail(auth.getName());

            return ResponseEntity.ok(user.getUsername());
        }

        return ResponseEntity.noContent().build();
    }
}
