package com.github.eliaspinheiropereira.segurancajwt.controller;

import com.github.eliaspinheiropereira.segurancajwt.model.Produto;
import com.github.eliaspinheiropereira.segurancajwt.model.Usuario;
import com.github.eliaspinheiropereira.segurancajwt.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
@Slf4j
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Void> salvar(
            @RequestBody Produto produto
    ) {
        log.info("POST -> /produtos");
        this.produtoService.salvar(produto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable int id,
            @RequestBody Produto produto
    ){
        log.info("PUT -> /produtos/{}", id);
        this.produtoService.atualizar(id, produto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable int id
    ){
        log.info("DELETE -> /produtos/{}", id);
        this.produtoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(
            @PathVariable int id
    ){
        log.info("GET -> /produtos/{}", id);
        Produto buscarProdutoPorId = this.produtoService.buscarProdutoPorId(id);
        return new ResponseEntity<>(buscarProdutoPorId, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Produto>> buscarTodosProdutos(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ){
        log.info("GET -> /produtos?page={}&size={}", page, size);
        Page<Produto> buscandoTodosProdutos = this.produtoService.buscarTodosProdutos(page, size);
        return new ResponseEntity(buscandoTodosProdutos.getContent(), HttpStatus.OK);
    }
}
