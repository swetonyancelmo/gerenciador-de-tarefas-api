package com.swetonyancelmo.gerenciamento_de_tarefas.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriarTaskRequestDTO {

    @NotBlank(message = "O título não pode estar em branco.")
    @Size(max = 100, message = "O título não pode ter mais que 100 caracteres.")
    private String titulo;

    @Size(max = 255, message = "A descrição não pode ter mais que 255 caracteres.")
    private String descricao;
}
