package br.com.arthenyo.flash_sale_checkout.exceptions;

public class EstoqueInsuficienteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EstoqueInsuficienteException(String message) {
        super(message);
    }
    
}
