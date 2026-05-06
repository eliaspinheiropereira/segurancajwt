package com.github.eliaspinheiropereira.segurancajwt.service;

import com.github.eliaspinheiropereira.segurancajwt.controller.dto.UsuarioDTO;
import com.github.eliaspinheiropereira.segurancajwt.controller.mapper.UsuarioMapper;
import com.github.eliaspinheiropereira.segurancajwt.model.Usuario;
import com.github.eliaspinheiropereira.segurancajwt.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public void salvar(UsuarioDTO usuario){
        Usuario novoUsuario = this.usuarioMapper.toEntity(usuario);
        this.usuarioRepository.save(novoUsuario);
    }

    public void atualizar(int id, UsuarioDTO usuario){
        Usuario buscarUsuario = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("usuario não encontrado"));

        Usuario usuarioAtualizado = this.usuarioMapper.toEntity(usuario);
        usuarioAtualizado.setId(id);
        this.usuarioRepository.save(usuarioAtualizado);
    }

    public void deletar(int id){
        Usuario buscarUsuario = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("usuario não encontrado"));

        this.usuarioRepository.deleteById(id);
    }

    public List<UsuarioDTO> buscarUsuarioPorId(int id){
        Usuario buscarUsuario = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("usuario não encontrado"));

        return List.of(this.usuarioMapper.toDTO(buscarUsuario));
    }

    public Page<UsuarioDTO> buscarTodosUsuarios(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Usuario> buscandoTodosUsuarios = this.usuarioRepository.findAll(pageable);
        Page<UsuarioDTO> usuarioDTOS = buscandoTodosUsuarios.map(this.usuarioMapper::toDTO);
        return usuarioDTOS;
    }
}
