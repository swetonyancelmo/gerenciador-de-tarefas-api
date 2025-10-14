package com.swetonyancelmo.gerenciamento_de_tarefas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApiErrorResponse {
    private String message;
    private List<String> errors;
    private int statusCode;
}
