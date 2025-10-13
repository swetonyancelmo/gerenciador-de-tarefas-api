package com.swetonyancelmo.gerenciamento_de_tarefas.dtos;

import com.swetonyancelmo.gerenciamento_de_tarefas.models.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private boolean concluida;

    public TaskResponseDTO(Task task) {
        this.id = task.getId();
        this.titulo = task.getTitulo();
        this.descricao = task.getDescricao();
        this.concluida = task.isConcluida();
    }
}
