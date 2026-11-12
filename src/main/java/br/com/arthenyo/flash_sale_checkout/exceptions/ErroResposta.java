package br.com.arthenyo.flash_sale_checkout.exceptions;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class ErroResposta {
    
    private String mensagem;
    private Integer status;
    private Long timestamp;
    private String path;
    @Enumerated(EnumType.ORDINAL)
    private CodigoErro codigoErro;

    public ErroResposta(String mensagem, Integer status, Long timestamp, String path, CodigoErro codigoErro) {
        this.mensagem = mensagem;
        this.status = status;
        this.timestamp = timestamp;
        this.path = path;
        this.codigoErro = codigoErro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Integer getStatus() {
        return status;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }

    public CodigoErro getCodigoErro() {
        return codigoErro;
    }
}
