package br.edu.fesa.aquela_loja.service.user_services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.edu.fesa.aquela_loja.models.dto.address.NewUserAddressDto;
import br.edu.fesa.aquela_loja.models.dto.address.UserAddressDto;
import br.edu.fesa.aquela_loja.models.entity.UserAddressModel;
import br.edu.fesa.aquela_loja.models.entity.UserModel;
import br.edu.fesa.aquela_loja.repository.IUserAddressRepository;
import br.edu.fesa.aquela_loja.repository.IUserRepository;

@Service
public class UserAddressService {

    @Autowired
    private IUserRepository appUserRepository;
    @Autowired
    private IUserAddressRepository repository;

    public List<UserAddressDto> getUserAddress() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserModel appUser = appUserRepository.findByEmail(auth.getName()).get();

        List<UserAddressModel> userAddresses = repository.findByAppUser(appUser).get();
        List<UserAddressDto> result = new ArrayList<>();

        for (UserAddressModel address : userAddresses) {
            UserAddressDto userAddress = new UserAddressDto(address);
            if (address.getIsDefault()) {
                result.add(0, userAddress); // Adiciona o endereço padrão no início da lista
            } else {
                result.add(userAddress);
            }
        }

        return result;
    }

    public void newAddressFromEntity(NewUserAddressDto newUserAddress, UserModel appUser) {
        UserAddressModel addressModel = UserAddressModel.builder()
                .cep(newUserAddress.getCep())
                .addressIdentification(newUserAddress.getAddressIdentification())
                .street(newUserAddress.getStreet())
                .number(newUserAddress.getNumber())
                .neighborhood(newUserAddress.getNeighborhood())
                .city(newUserAddress.getCity())
                .state(newUserAddress.getState())
                .complement(newUserAddress.getComplement())
                .reference(newUserAddress.getReference())
                .isDefault(false)
                .appUser(appUser)
                .build();

        repository.save(addressModel);
    }

    public void addNewAddress(NewUserAddressDto newUserAddress) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserModel appUser = appUserRepository.findByEmail(auth.getName()).get();

        UserAddressModel addressModel = UserAddressModel.builder()
                .cep(newUserAddress.getCep())
                .addressIdentification(newUserAddress.getAddressIdentification())
                .street(newUserAddress.getStreet())
                .number(newUserAddress.getNumber())
                .neighborhood(newUserAddress.getNeighborhood())
                .city(newUserAddress.getCity())
                .state(newUserAddress.getState())
                .complement(newUserAddress.getComplement())
                .reference(newUserAddress.getReference())
                .isDefault(false)
                .appUser(appUser)
                .build();

        repository.save(addressModel);
    }

    public void updateUserAddress(UserAddressDto userAddressDto) {
        UserAddressModel addressModel = repository.findById(userAddressDto.getId()).get();

        addressModel.setCep(userAddressDto.getCep());
        addressModel.setAddressIdentification(userAddressDto.getAddressIdentification());
        addressModel.setStreet(userAddressDto.getStreet());
        addressModel.setNumber(userAddressDto.getNumber());
        addressModel.setNeighborhood(userAddressDto.getNeighborhood());
        addressModel.setCity(userAddressDto.getCity());
        addressModel.setState(userAddressDto.getState());
        addressModel.setComplement(userAddressDto.getComplement());
        addressModel.setReference(userAddressDto.getReference());
        addressModel.setIsDefault(userAddressDto.isDefault());

        repository.save(addressModel);
    }

    public void deleteUserAddress(Long id) {
        //TODO: Colocar uma validação que não permita deletar o endereço caso ele seja o único disponível
        repository.deleteById(id);
    }

    public void setDefaultAddress(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserModel appUser = appUserRepository.findByEmail(auth.getName()).get();

        List<UserAddressModel> userAddresses = repository.findByAppUser(appUser).get();

        for (UserAddressModel address : userAddresses) {
            if (Objects.equals(address.getId(), id)) {
                address.setIsDefault(true);
            } else {
                address.setIsDefault(false);
            }
        }

        repository.saveAll(userAddresses);
    }
}
