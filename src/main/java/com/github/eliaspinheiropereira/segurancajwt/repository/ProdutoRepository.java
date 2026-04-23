package com.github.eliaspinheiropereira.segurancajwt.repository;

import com.github.eliaspinheiropereira.segurancajwt.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
