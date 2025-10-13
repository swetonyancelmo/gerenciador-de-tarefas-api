package com.swetonyancelmo.gerenciamento_de_tarefas.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriarTaskRequestDTO {

    private String titulo;
    private String descricao;
}
