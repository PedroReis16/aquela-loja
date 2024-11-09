package br.edu.fesa.aquela_loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.edu.fesa.aquela_loja.models.dto.RegistrationDto;
import br.edu.fesa.aquela_loja.models.entity.AddressModel;
import br.edu.fesa.aquela_loja.models.entity.AppUserModel;
import br.edu.fesa.aquela_loja.repository.IAddressRepository;
import br.edu.fesa.aquela_loja.repository.IAppUserRepository;

@Controller
@SessionAttributes("user")
public class UserController {

    @Autowired
    private IAppUserRepository appUserRepository;

    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping(value = "/user/registration")
    public String registration(RegistrationDto registrationDto) {

        AppUserModel appUser = AppUserModel.builder()
                .username(registrationDto.getUsername())
                .document(registrationDto.getDocument())
                .gender(registrationDto.getGender())
                .birthdate(registrationDto.getBirthdate())
                .phone(registrationDto.getPhone())
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .role(registrationDto.getRole())
                .build();

        AddressModel addressModel = AddressModel.builder()
                .cep(registrationDto.getCep())
                .addressIdentification(registrationDto.getAddressIdentification())
                .street(registrationDto.getStreet())
                .number(registrationDto.getNumber())
                .neighborhood(registrationDto.getNeighborhood())
                .city(registrationDto.getCity())
                .state(registrationDto.getState())
                .complement(registrationDto.getComplement())
                .reference(registrationDto.getReference())
                .appUser(appUser)
                .build();

        appUserRepository.save(appUser);
        addressRepository.save(addressModel);
        return "redirect:/";
    }

    @GetMapping(value = "/usuario")
    public String getMethodName(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        String userName = auth.getName();
        var obj = auth.getAuthorities();
        Object useremail = auth.getCredentials();

        //  AppUserModel currentUser = appUserRepository.findByEmail(currentUserEmail).get();

        model.addAttribute("username", userName);
        // model.addAttribute("email", useremail);

        return "pages/user_pages/usuario";
    }
    
}
