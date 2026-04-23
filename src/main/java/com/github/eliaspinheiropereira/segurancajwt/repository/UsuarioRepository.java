package com.github.eliaspinheiropereira.segurancajwt.repository;

import com.github.eliaspinheiropereira.segurancajwt.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
