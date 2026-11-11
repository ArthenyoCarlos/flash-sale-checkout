package br.com.arthenyo.flash_sale_checkout.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.arthenyo.flash_sale_checkout.dto.PedidoResponseDTO;
import br.com.arthenyo.flash_sale_checkout.service.PedidoService;

@RestController 
@RequestMapping ("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("{produtoId}") 
    public ResponseEntity<PedidoResponseDTO> saveCompra(@PathVariable Long produtoId) {
        PedidoResponseDTO pedidoResponseDTO = pedidoService.saveCompra(produtoId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(pedidoResponseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(pedidoResponseDTO);
    }
    
}
