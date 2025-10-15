package com.swetonyancelmo.gerenciamento_de_tarefas.dtos;

import com.swetonyancelmo.gerenciamento_de_tarefas.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String nome;
    private String email;

    public UserResponseDTO(User user){
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
    }
}
