package com.github.eliaspinheiropereira.segurancajwt.service;

import com.github.eliaspinheiropereira.segurancajwt.model.Usuario;
import com.github.eliaspinheiropereira.segurancajwt.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public void salvar(Usuario usuario){
        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(usuario.getLogin());
        novoUsuario.setSenha(usuario.getSenha());
        novoUsuario.setRoles(usuario.getRoles());

        this.usuarioRepository.save(novoUsuario);
    }

    public void atualizar(int id, Usuario usuario){
        Usuario buscarUsuario = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("usuario não encontrado"));

        Usuario usuarioAtualizado = buscarUsuario;
        usuarioAtualizado.setId(buscarUsuario.getId());
        usuarioAtualizado.setLogin(usuario.getLogin());
        usuarioAtualizado.setSenha(usuario.getSenha());
        usuarioAtualizado.setRoles(usuario.getRoles());

        this.usuarioRepository.save(usuarioAtualizado);
    }

    public void deletar(int id){
        Usuario buscarUsuario = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("usuario não encontrado"));

        this.usuarioRepository.deleteById(id);
    }

    public Usuario buscarUsuarioPorId(int id){
        Usuario buscarUsuario = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("usuario não encontrado"));

        return buscarUsuario;
    }

    public Page<Usuario> buscarTodosUsuarios(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Usuario> buscandoTodosUsuarios = this.usuarioRepository.findAll(pageable);
        return buscandoTodosUsuarios;
    }
}
