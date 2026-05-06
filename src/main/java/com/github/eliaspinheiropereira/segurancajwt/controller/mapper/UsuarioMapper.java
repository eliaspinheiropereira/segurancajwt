package com.github.eliaspinheiropereira.segurancajwt.controller.mapper;

import com.github.eliaspinheiropereira.segurancajwt.controller.dto.UsuarioDTO;
import com.github.eliaspinheiropereira.segurancajwt.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioDTO usuarioDTO);
    UsuarioDTO toDTO(Usuario usuario);
}
