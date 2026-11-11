package br.com.arthenyo.flash_sale_checkout.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProdutoRequestDTO(

    @NotBlank(message = "O nome do produto não pode ser vazio.")
    String nome,

    @NotNull(message = "O preço do produto não pode ser vazio.")
    @Positive (message = "O preço do produto deve ser maior que zero.")
    BigDecimal preco,
    
    @NotNull (message = "O estoque do produto não pode ser vazio.")
    Integer estoque
) {}
