package com.github.eliaspinheiropereira.segurancajwt.controller.mapper;

import com.github.eliaspinheiropereira.segurancajwt.controller.dto.ProdutoDTO;
import com.github.eliaspinheiropereira.segurancajwt.model.Produto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    Produto toEntity(ProdutoDTO produtoDTO);
    ProdutoDTO toDTO(Produto produto);
}
