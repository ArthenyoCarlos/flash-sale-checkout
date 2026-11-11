package br.com.arthenyo.flash_sale_checkout.dto;

import java.math.BigDecimal;

import br.com.arthenyo.flash_sale_checkout.entity.Produto;

public record ProdutoResponseDTO(
    Long id,
    String nome,
    BigDecimal preco,
    Integer estoque
) {
    public ProdutoResponseDTO(Produto p) {
        this(p.getId(), p.getNome(), p.getPreco(), p.getEstoque());
    }
}