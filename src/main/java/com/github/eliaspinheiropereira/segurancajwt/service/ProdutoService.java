package com.github.eliaspinheiropereira.segurancajwt.service;

import com.github.eliaspinheiropereira.segurancajwt.controller.dto.ProdutoDTO;
import com.github.eliaspinheiropereira.segurancajwt.controller.mapper.ProdutoMapper;
import com.github.eliaspinheiropereira.segurancajwt.model.Produto;
import com.github.eliaspinheiropereira.segurancajwt.model.Usuario;
import com.github.eliaspinheiropereira.segurancajwt.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public void salvar(ProdutoDTO produto){
        Produto novoProduto = this.produtoMapper.toEntity(produto);
        this.produtoRepository.save(novoProduto);
    }

    public void atualizar(int id, ProdutoDTO produto){
        Produto buscarProduto = this.produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("produto não encontrado"));

        Produto produtoAtualizado = this.produtoMapper.toEntity(produto);
        produtoAtualizado.setId(id);
        this.produtoRepository.save(produtoAtualizado);
    }

    public void deletar(int id){
        Produto buscarProduto = this.produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("produto não encontrado"));

        this.produtoRepository.deleteById(id);
    }

    public ProdutoDTO buscarProdutoPorId(int id){
        Produto buscarProduto = this.produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("produto não encontrado"));

        ProdutoDTO produtoDTO = this.produtoMapper.toDTO(buscarProduto);
        return produtoDTO;
    }

    public Page<ProdutoDTO> buscarTodosProdutos(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Produto> buscarTodosProdutos = this.produtoRepository.findAll(pageable);
        Page<ProdutoDTO> produtoDTOS = buscarTodosProdutos.map(this.produtoMapper::toDTO);
        return produtoDTOS;
    }
}
