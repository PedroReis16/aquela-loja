package br.edu.fesa.aquela_loja.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDataDto {

    private String brand;
    private Integer totalSale;
    private String day;
}
