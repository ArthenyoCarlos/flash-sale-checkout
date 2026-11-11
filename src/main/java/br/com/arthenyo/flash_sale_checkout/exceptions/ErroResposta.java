package br.com.arthenyo.flash_sale_checkout.exceptions;

public class ErroResposta {
    
    private String mensagem;
    private Integer status;
    private Long timestamp;
    private String path;

    public ErroResposta(String mensagem, Integer status, Long timestamp, String path) {
        this.mensagem = mensagem;
        this.status = status;
        this.timestamp = timestamp;
        this.path = path;
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

    
}
