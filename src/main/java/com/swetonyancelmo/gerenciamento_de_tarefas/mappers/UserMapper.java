package com.swetonyancelmo.gerenciamento_de_tarefas.mappers;

import com.swetonyancelmo.gerenciamento_de_tarefas.dtos.CriarUserRequestDTO;
import com.swetonyancelmo.gerenciamento_de_tarefas.dtos.UserResponseDTO;
import com.swetonyancelmo.gerenciamento_de_tarefas.exceptions.EmailJaCadastradoException;
import com.swetonyancelmo.gerenciamento_de_tarefas.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toResponseDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getNome(),
                user.getEmail()
        );
    }

    public User toEntity(CriarUserRequestDTO dto){
        User user = new User();
        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());
        user.setSenha(dto.getSenha());
        return user;
    }
}
