package com.swetonyancelmo.gerenciamento_de_tarefas.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CriarUserRequestDTO {

    @NotBlank
    @Size(min = 3, max = 100)
    private String nome;

    @NotBlank
    @Email(message = "O formato do email é inválido.")
    private String email;

    @NotBlank
    @Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 caracteres")
    private String senha;
}
