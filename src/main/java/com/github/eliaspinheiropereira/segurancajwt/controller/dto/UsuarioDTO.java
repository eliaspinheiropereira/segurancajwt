package com.github.eliaspinheiropereira.segurancajwt.controller.dto;

import com.github.eliaspinheiropereira.segurancajwt.model.enums.Roles;

public record UsuarioDTO(
        String login,
        String senha,
        Roles roles
) {
}
