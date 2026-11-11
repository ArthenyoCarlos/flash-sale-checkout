package br.com.arthenyo.flash_sale_checkout.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.arthenyo.flash_sale_checkout.dto.ProdutoRequestDTO;
import br.com.arthenyo.flash_sale_checkout.dto.ProdutoResponseDTO;
import br.com.arthenyo.flash_sale_checkout.entity.Produto;
import br.com.arthenyo.flash_sale_checkout.exceptions.ObjetoNaoEncontradoException;
import br.com.arthenyo.flash_sale_checkout.repository.ProdutoRepository;

@Service 
public class ProdutoService {


    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional(readOnly = true)
    public ProdutoResponseDTO findByIdProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Produto não encontrado com o ID: " + id));

        return new ProdutoResponseDTO(produto);
    }

    @Transactional
    public ProdutoResponseDTO saveProduto(ProdutoRequestDTO dto) {
        Produto produto = new Produto(dto.nome(), dto.preco(), dto.estoque());
        produtoRepository.save(produto);
        return new ProdutoResponseDTO(produto);
    }
    
}
