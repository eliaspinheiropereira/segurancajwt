package com.github.eliaspinheiropereira.segurancajwt.service;

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

    public void salvar(Produto produto){
        Produto novoProduto = new Produto();
        novoProduto.setNome(produto.getNome());
        novoProduto.setDescricao(produto.getDescricao());
        novoProduto.setValor(produto.getValor());

        this.produtoRepository.save(novoProduto);
    }

    public void atualizar(int id, Produto produto){
        Produto buscarProduto = this.produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("produto não encontrado"));

        Produto produtoAtualizado = buscarProduto;
        produtoAtualizado.setId(buscarProduto.getId());
        produtoAtualizado.setNome(produto.getNome());
        produtoAtualizado.setDescricao(produto.getDescricao());
        produtoAtualizado.setValor(produto.getValor());

        this.produtoRepository.save(produtoAtualizado);
    }

    public void deletar(int id){
        Produto buscarProduto = this.produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("produto não encontrado"));

        this.produtoRepository.deleteById(id);
    }

    public Produto buscarProdutoPorId(int id){
        Produto buscarProduto = this.produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("produto não encontrado"));

        return buscarProduto;
    }

    public Page<Produto> buscarTodosProdutos(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Produto> buscarTodosProdutos = this.produtoRepository.findAll(pageable);
        return  buscarTodosProdutos;
    }
}
