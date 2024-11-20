package br.edu.fesa.aquela_loja.models.dto.address;

import br.edu.fesa.aquela_loja.models.entity.UserAddressModel;
import lombok.Data;

@Data
public class UserAddressDto {

    private Long id;

    private String cep;

    private String addressIdentification;

    private String street;

    private String number;

    private String neighborhood;

    private String city;

    private String state;

    private String complement;

    private String reference;

    private boolean isDefault;

    // Front details
    private String numberString;
    private String details;

    public UserAddressDto() {
    }

    public UserAddressDto(UserAddressModel address) {
        this.id = address.getId();
        this.cep = address.getCep();
        this.addressIdentification = address.getAddressIdentification();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.neighborhood = address.getNeighborhood();
        this.city = address.getCity();
        this.state = address.getState();
        this.complement = address.getComplement();
        this.reference = address.getReference();
        this.isDefault = address.getIsDefault();

        numberString = "Número: " + number;

        if (complement != null && !complement.isEmpty()) {
            numberString += ", " + complement;
        }
        details = "CEP " + cep + " - " + city + ", " + state;
    }
}
