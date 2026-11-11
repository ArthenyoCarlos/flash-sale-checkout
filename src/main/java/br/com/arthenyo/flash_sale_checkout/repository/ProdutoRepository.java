package br.com.arthenyo.flash_sale_checkout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arthenyo.flash_sale_checkout.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
}
