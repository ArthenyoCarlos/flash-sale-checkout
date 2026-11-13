package br.com.arthenyo.flash_sale_checkout.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.arthenyo.flash_sale_checkout.dto.PedidoResponseDTO;
import br.com.arthenyo.flash_sale_checkout.service.CompraService;
import br.com.arthenyo.flash_sale_checkout.service.PedidoService;

@RestController 
@RequestMapping ("/pedidos")
public class PedidoController {

    private final CompraService compraService;

    public PedidoController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping("{produtoId}") 
    public ResponseEntity<PedidoResponseDTO> realizarCompra(@PathVariable Long produtoId) {
        PedidoResponseDTO pedidoResponseDTO = compraService.comprar(produtoId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(pedidoResponseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(pedidoResponseDTO);
    }
    
}
