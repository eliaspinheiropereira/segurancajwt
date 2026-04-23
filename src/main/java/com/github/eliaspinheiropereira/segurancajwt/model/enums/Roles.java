package com.github.eliaspinheiropereira.segurancajwt.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Roles {
    ADMIN,
    USUARIO,
    VISITANTE;

    @JsonCreator
    public static Roles fromValue(String value){
        return Roles.valueOf(value.toUpperCase());
    }
}
