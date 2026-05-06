package com.github.eliaspinheiropereira.segurancajwt.controller.dto;

public record BuscarProdutoDTO(
        int id,
        String nome,
        String descricao,
        double valor
) {
}
