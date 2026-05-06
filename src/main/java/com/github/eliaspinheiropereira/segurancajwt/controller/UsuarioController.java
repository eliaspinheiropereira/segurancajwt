package com.github.eliaspinheiropereira.segurancajwt.controller;

import com.github.eliaspinheiropereira.segurancajwt.controller.dto.UsuarioDTO;
import com.github.eliaspinheiropereira.segurancajwt.model.Usuario;
import com.github.eliaspinheiropereira.segurancajwt.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
@Slf4j
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> salvar(
            @RequestBody UsuarioDTO usuario
    ) {
        log.info("POST -> /usuarios");
        this.usuarioService.salvar(usuario);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable int id,
            @RequestBody UsuarioDTO usuario
    ){
        log.info("PUT -> /usuarios/{}", id);
        this.usuarioService.atualizar(id, usuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable int id
    ){
        log.info("DELETE -> /usuarios/{}", id);
        this.usuarioService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<UsuarioDTO>> buscarUsuarioPorId(
            @PathVariable int id
    ){
        log.info("GET -> /usuarios/{}", id);
        List<UsuarioDTO> buscarUsuarioPorId = this.usuarioService.buscarUsuarioPorId(id);
        return new ResponseEntity<>(buscarUsuarioPorId, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Usuario>> buscarTodosUsuarios(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ){
        log.info("GET -> /usuarios?page={}&size={}", page, size);
        Page<UsuarioDTO> buscandoTodosUsuarios = this.usuarioService.buscarTodosUsuarios(page, size);
        return new ResponseEntity(buscandoTodosUsuarios.getContent(), HttpStatus.OK);
    }

}
