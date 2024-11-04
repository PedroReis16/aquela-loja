package br.edu.fesa.aquela_loja.models.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Table(name = "address")
@Entity
@NoArgsConstructor
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;
}
