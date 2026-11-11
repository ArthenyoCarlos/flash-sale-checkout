package br.com.arthenyo.flash_sale_checkout.dto;

import java.math.BigDecimal;
import java.time.Instant;

import br.com.arthenyo.flash_sale_checkout.entity.Pedido;

public record PedidoResponseDTO(
    Long id,
    String nomeProduto,
    BigDecimal valorTotal,
    Instant dataPedido
) {

    public PedidoResponseDTO(Pedido p){
        this(
            p.getId(),
            p.getProduto().getNome(),
            p.getProduto().getPreco(),
            p.getDataPedido()
        );
    }
    
}
