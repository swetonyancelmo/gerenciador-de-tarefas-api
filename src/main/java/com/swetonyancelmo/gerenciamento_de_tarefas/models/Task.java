package com.swetonyancelmo.gerenciamento_de_tarefas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private Long id;
    private String titulo;
    private String descricao;
    private boolean concluida = false;
}
