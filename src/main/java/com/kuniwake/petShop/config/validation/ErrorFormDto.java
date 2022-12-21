package com.kuniwake.petShop.config.validation;

import lombok.Getter;

@Getter
public class ErrorFormDto {

    private String field;
    private String error;

    public ErrorFormDto(String field, String error) {
        this.field = field;
        this.error = error;
    }
}
