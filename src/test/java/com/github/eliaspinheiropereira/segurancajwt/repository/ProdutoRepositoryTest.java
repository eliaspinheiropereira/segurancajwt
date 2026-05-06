package com.github.eliaspinheiropereira.segurancajwt.repository;

import com.github.eliaspinheiropereira.segurancajwt.model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ProdutoRepositoryTest {

    @Autowired
    ProdutoRepository produtoRepository;

    Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto("Nintendo Switch 2", "console da nintendo", 5000);
    }

    @DisplayName("test deve buscar um produto por id")
    @Test
    void testBuscandoUmProdutoPorId() {
        // given
        var p = produto;
        this.produtoRepository.save(p);

        // when
        Optional<Produto> buscarProdutoPorId = this.produtoRepository.findById(p.getId());

        // then
        assertThat(buscarProdutoPorId).isPresent();
        assertThat(buscarProdutoPorId.get().getNome())
                .isEqualTo("Nintendo Switch 2");
    }

    @DisplayName("test não deve encontrar um produto por id")
    @Test
    void testNaoDeveEncontrarProdutoPorId() {
        var id = 2;

        // when
        Optional<Produto> buscarProdutoPorId = this.produtoRepository.findById(id);

        // then
        assertThat(buscarProdutoPorId).isEmpty();
    }

    @DisplayName("test deve encontrar todos produtos")
    @Test
    void testDeveEncontrarTodosProdutos(){
        // given
        var listaProdtuos = List.of(
          new Produto("Playsatation 5", "console sony", 4000),
          new Produto("Nintendo Switch", "console nintendo", 2500),
          new Produto("Xbox One", "console windows", 4000)
        );
        this.produtoRepository.saveAll(listaProdtuos);

        // when
        List<Produto> buscarTodosProdutos = this.produtoRepository.findAll();

        // then
        assertThat(buscarTodosProdutos.size()).isEqualTo(3);

    }

    @DisplayName("test deve criar um produto")
    @Test
    void testCriandoProduto() {
        // given
        var p = produto;

        // when
        var produtoSalvo = this.produtoRepository.save(p);

        // then
        assertThat(produtoSalvo).isNotNull();
    }

    @DisplayName("test deve atualizar um produto")
    @Test
    void testDeveAtualizarProduto() {
        // given
        var p = produto;
        this.produtoRepository.save(p);
        var buscarProduto = this.produtoRepository.findById(p.getId());
        var atualizarProduto = buscarProduto.get();
        atualizarProduto.setNome("Playstation 5");
        atualizarProduto.setDescricao("console da sony");
        atualizarProduto.setValor(4500);

        // when
        this.produtoRepository.save(atualizarProduto);

        // then
        assertThat(atualizarProduto).isNotNull();
        assertThat(atualizarProduto.getId()).isEqualTo(p.getId());
        assertThat(atualizarProduto.getNome()).isEqualTo("Playstation 5");
    }

    @DisplayName("test deve deletar um produto por id")
    @Test
    void testDeveDeletarProdutoPorId() {
        // given
        var p = produto;
        this.produtoRepository.save(p);

        // when
        this.produtoRepository.deleteById(p.getId());

        // then
        Optional<Produto> buscarProduto = this.produtoRepository.findById(p.getId());
        assertThat(buscarProduto).isEmpty();
    }
}