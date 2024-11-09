package br.edu.fesa.aquela_loja.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table(name = "address")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "userId")
    private AppUserModel appUser;
}
