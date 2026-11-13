package br.com.arthenyo.flash_sale_checkout.service;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

import br.com.arthenyo.flash_sale_checkout.dto.PedidoResponseDTO;

@Service 
public class CompraService {

    private final PedidoService pedidoService;

    public CompraService(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Retryable (
        includes = ObjectOptimisticLockingFailureException.class,
        maxRetries = 5,
        delay = 20,
        multiplier = 2,
        jitter = 10,
        maxDelay = 300
    )
    public PedidoResponseDTO comprar(Long produtoId) {
        return pedidoService.realizarCompra(produtoId);
    }
    
}
