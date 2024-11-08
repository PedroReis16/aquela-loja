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

    @ManyToOne
    @JoinColumn(name = "userId")
    private AppUserModel appUser;
}
