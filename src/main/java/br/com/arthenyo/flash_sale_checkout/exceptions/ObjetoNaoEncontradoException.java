package br.com.arthenyo.flash_sale_checkout.exceptions;

public class ObjetoNaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ObjetoNaoEncontradoException(String message) {
        super(message);
    }
    
}
