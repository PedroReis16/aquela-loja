package br.edu.fesa.aquela_loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.fesa.aquela_loja.models.dto.NewUserDto;
import br.edu.fesa.aquela_loja.models.dto.UserDto;
import br.edu.fesa.aquela_loja.models.entity.AddressModel;
import br.edu.fesa.aquela_loja.models.entity.AppUserModel;
import br.edu.fesa.aquela_loja.repository.IAddressRepository;
import br.edu.fesa.aquela_loja.repository.IAppUserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private IAppUserRepository appUserRepository;

    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void createNewUser(NewUserDto newUser) {
        AppUserModel appUser = AppUserModel.builder()
                .username(newUser.getUsername())
                .document(newUser.getDocument())
                .gender(newUser.getGender())
                .birthdate(newUser.getBirthdate())
                .phone(newUser.getPhone())
                .email(newUser.getEmail())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .role(newUser.getRole())
                .build();

        AddressModel addressModel = AddressModel.builder()
                .cep(newUser.getCep())
                .addressIdentification(newUser.getAddressIdentification())
                .street(newUser.getStreet())
                .number(newUser.getNumber())
                .neighborhood(newUser.getNeighborhood())
                .city(newUser.getCity())
                .state(newUser.getState())
                .complement(newUser.getComplement())
                .reference(newUser.getReference())
                .isDefault(true)
                .appUser(appUser)
                .build();

        appUserRepository.save(appUser);
        addressRepository.save(addressModel);
    }

    public UserDto getAuthenticatedUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUserModel appUser = appUserRepository.findByEmail(auth.getName()).get();
        // AppUserModel appUser = appUserRepository.findByDocument(auth.getCredentials().)

        UserDto user = new UserDto(appUser);

        return user;
    }

    public void updateUser(UserDto updatedUser) {
        AppUserModel savedUser = appUserRepository.findByDocument(updatedUser.getDocument()).get();

        savedUser.setUsername(updatedUser.getUserName());
        savedUser.setEmail(updatedUser.getEmail());
        savedUser.setGender(updatedUser.getGender());
        savedUser.setPhone(updatedUser.getPhone());

        appUserRepository.save(savedUser);
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUserModel appUser = appUserRepository.findByEmail(auth.getName()).get();

        appUserRepository.delete(appUser);

        
        // Invalida a sessão
        request.getSession().invalidate();

        // Remove todos os cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }
}
