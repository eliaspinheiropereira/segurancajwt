package com.github.eliaspinheiropereira.segurancajwt.model;

import com.github.eliaspinheiropereira.segurancajwt.model.enums.Roles;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String senha;
    @Enumerated(EnumType.STRING)
    private Roles roles;

    public Usuario(String login, String senha, Roles roles) {
        this.login = login;
        this.senha = senha;
        this.roles = roles;
    }
}
