package br.edu.fesa.aquela_loja.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "wallet")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserCardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String holderName;

    private String number;

    private String expirationDate;

    private int cvv;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserModel appUser;
}
