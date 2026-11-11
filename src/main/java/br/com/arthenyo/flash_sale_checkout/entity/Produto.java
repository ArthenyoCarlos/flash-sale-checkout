package br.com.arthenyo.flash_sale_checkout.entity;

import java.math.BigDecimal;

import br.com.arthenyo.flash_sale_checkout.exceptions.EstoqueInsuficienteException;
import br.com.arthenyo.flash_sale_checkout.exceptions.RegrasNegocioException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table (name = "produto")
public class Produto {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String nome;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private Integer estoque;

    protected Produto() {
    }

    public Produto(String nome, BigDecimal preco, Integer estoque) {
        if (estoque == null || estoque < 0) {
            throw new RegrasNegocioException("O estoque inicial não pode ser negativo.");
        }
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public Integer baixarEstoque(Integer quantidade) {
        if (quantidade <= 0) {
            throw new RegrasNegocioException("A quantidade deve ser maior que zero.");
        }
        if (quantidade > estoque) {
            throw new EstoqueInsuficienteException("Estoque insuficiente para o produto: " + nome);
        }
        estoque -= quantidade;
        return estoque;
    }

    public void aumentarEstoque(Integer quantidade) {
        if (quantidade <= 0) {
            throw new RegrasNegocioException("A quantidade deve ser maior que zero.");
        }
        estoque += quantidade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }
}

