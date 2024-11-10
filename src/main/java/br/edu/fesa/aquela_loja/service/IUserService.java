package br.edu.fesa.aquela_loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.fesa.aquela_loja.models.dto.NewUserDto;
import br.edu.fesa.aquela_loja.models.dto.UpdateUserDto;
import br.edu.fesa.aquela_loja.models.dto.UserDto;
import br.edu.fesa.aquela_loja.models.entity.AddressModel;
import br.edu.fesa.aquela_loja.models.entity.AppUserModel;
import br.edu.fesa.aquela_loja.repository.IAddressRepository;
import br.edu.fesa.aquela_loja.repository.IAppUserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IUserService {

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

    public void updateUser(Long userId, UpdateUserDto updatedUser) {

    }
}
