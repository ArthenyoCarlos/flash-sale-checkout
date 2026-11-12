package br.com.arthenyo.flash_sale_checkout.exceptions;

public enum CodigoErro {
    ESTOQUE_ESGOTADO("O produto está esgotado."),
    PRODUTO_NAO_ENCONTRADO("O produto não foi encontrado."),
    CONFLITO_CONCORRENCIA("Ocorreu um conflito de concorrência ao tentar atualizar o estoque."),
    REGRAS_NEGOCIO( "Violação de regras de negócio.");

    private final String mensagem;

    CodigoErro(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
