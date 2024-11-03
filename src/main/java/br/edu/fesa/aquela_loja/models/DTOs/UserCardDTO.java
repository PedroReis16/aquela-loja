package br.edu.fesa.aquela_loja.models.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class UserCardDTO {
        private String number;
        private String name;
        private String expirationDate;
        private String cvv;   
}
