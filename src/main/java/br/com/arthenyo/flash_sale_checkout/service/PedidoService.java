package br.com.arthenyo.flash_sale_checkout.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.arthenyo.flash_sale_checkout.dto.PedidoResponseDTO;
import br.com.arthenyo.flash_sale_checkout.entity.Pedido;
import br.com.arthenyo.flash_sale_checkout.entity.Produto;
import br.com.arthenyo.flash_sale_checkout.exceptions.ObjetoNaoEncontradoException;
import br.com.arthenyo.flash_sale_checkout.repository.PedidoRepository;
import br.com.arthenyo.flash_sale_checkout.repository.ProdutoRepository;

@Service 
public class PedidoService {

    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;

    public PedidoService(ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public PedidoResponseDTO realizarCompra(Long produtoId){
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Produto não encontrado com o ID: " + produtoId));

        produto.baixarEstoque(1);
        produtoRepository.save(produto);

        Pedido pedido = new Pedido(produto);
        pedidoRepository.save(pedido);
        return new PedidoResponseDTO(pedido);
    }
    
}
