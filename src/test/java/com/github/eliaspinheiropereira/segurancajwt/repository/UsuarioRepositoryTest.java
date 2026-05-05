package com.github.eliaspinheiropereira.segurancajwt.repository;

import com.github.eliaspinheiropereira.segurancajwt.model.Usuario;
import com.github.eliaspinheiropereira.segurancajwt.model.enums.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    Usuario usuario;

    @BeforeEach
    void setUp(){
        usuario = new Usuario("elias", "0802", Roles.ADMIN);
    }

    @DisplayName("test deve salvar um usuario")
    @Test
    void testDeveSalvarUsuario(){
        // given
        var u = usuario;

        // when
        var usuarioSalvo = this.usuarioRepository.save(u);

        // then
        assertThat(usuarioSalvo).isNotNull();
        assertThat(usuarioSalvo.getId()).isEqualTo(1);
    }

    @DisplayName("test deve buscar um usuario por id")
    @Test
    void testDeveBuscarUsuarioPorId(){
        // given
        var u = usuario;
        this.usuarioRepository.save(u);

        // when
        Optional<Usuario> buscarUsuarioPorId = this.usuarioRepository.findById(u.getId());

        // then
        assertThat(buscarUsuarioPorId).isPresent();
        assertThat(buscarUsuarioPorId.get().getLogin()).isEqualTo("elias");
    }

    @DisplayName("test nao deve buscar usuario por id")
    @Test
    void testNaoDeveBuscarUsuarioPorId(){
        // given
        var id = 1;

        // when
        Optional<Usuario> buscarUsuarioPorId = this.usuarioRepository.findById(id);

        // then
        assertThat(buscarUsuarioPorId).isEmpty();
    }

    @DisplayName("test deve buscar todos usuarios")
    @Test
    void testDeveBuscarTodosUsuarios(){
        //given
        var usuarios = List.of(
                new Usuario("elias", "0802", Roles.ADMIN),
                new Usuario("valeria", "12345", Roles.USUARIO)
        );
        this.usuarioRepository.saveAll(usuarios);


        // when
        List<Usuario> listaUsuarios = this.usuarioRepository.findAll();

        // then
        assertThat(listaUsuarios.size()).isEqualTo(2);
    }

    @DisplayName("test deve atualizar usuario")
    @Test
    void testDeveAtualizarUsuario(){
        // given
        var u = usuario;
        this.usuarioRepository.save(u);

        var buscarUsuario = this.usuarioRepository.findById(1);
        var atualizarUsuario = buscarUsuario.get();
        atualizarUsuario.setLogin("valeria");
        atualizarUsuario.setSenha("12345");

        // when
        this.usuarioRepository.save(atualizarUsuario);

        // then
        assertThat(atualizarUsuario).isNotNull();
        assertThat(atualizarUsuario.getLogin()).isEqualTo("valeria");
        assertThat(atualizarUsuario.getSenha()).isEqualTo("12345");
        assertThat(atualizarUsuario.getId()).isEqualTo(1);
    }

    @DisplayName("test deve excluir um usuario")
    @Test
    void testDeveExcluirUsuario(){
        // given
        var u = usuario;
        this.usuarioRepository.save(u);

        // when
        this.usuarioRepository.deleteById(1);

        // then
        Optional<Usuario> buscarUsuario = this.usuarioRepository.findById(1);
        assertThat(buscarUsuario).isEmpty();
    }
}