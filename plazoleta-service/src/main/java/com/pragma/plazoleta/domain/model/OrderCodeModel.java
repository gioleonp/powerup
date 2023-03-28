package com.pragma.plazoleta.domain.model;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderCodeModel {

    private String code;
    private Long idOrder;

    public static String generateCode() {
        int numberOfDigits = 6;
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < numberOfDigits; i++) {
            int number = (int) (Math.random() * 10);
            code.append(number);
        }
        return code.toString();
    }
}
