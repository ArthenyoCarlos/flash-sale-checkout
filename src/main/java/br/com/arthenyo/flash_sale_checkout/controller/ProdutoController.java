package br.com.arthenyo.flash_sale_checkout.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.arthenyo.flash_sale_checkout.dto.ProdutoRequestDTO;
import br.com.arthenyo.flash_sale_checkout.dto.ProdutoResponseDTO;
import br.com.arthenyo.flash_sale_checkout.service.ProdutoService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long id) {
        ProdutoResponseDTO produtoResponseDTO = produtoService.findByIdProduto(id);
        return ResponseEntity.ok(produtoResponseDTO);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> saveProduto(@Valid @RequestBody ProdutoRequestDTO dto) {
        ProdutoResponseDTO savedProduto = produtoService.saveProduto(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedProduto.id()).toUri();
        return ResponseEntity.created(uri).body(savedProduto);
    }
    
}
