package br.com.arthenyo.flash_sale_checkout.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "pedido")
public class Pedido {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "produto_id", nullable = false)
    private Produto produto;
    
    @Column(name = "data_pedido", nullable = false)
    private Instant dataPedido;

    protected Pedido() {
    }

    public Pedido(Produto produto) {
        this.produto = produto;
        this.dataPedido = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Instant getDataPedido() {
        return dataPedido;
    }
}
