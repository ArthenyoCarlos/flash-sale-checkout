package br.com.arthenyo.flash_sale_checkout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arthenyo.flash_sale_checkout.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
}
