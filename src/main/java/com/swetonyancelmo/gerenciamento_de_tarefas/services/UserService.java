package com.swetonyancelmo.gerenciamento_de_tarefas.services;

import com.swetonyancelmo.gerenciamento_de_tarefas.dtos.CriarUserRequestDTO;
import com.swetonyancelmo.gerenciamento_de_tarefas.exceptions.EmailJaCadastradoException;
import com.swetonyancelmo.gerenciamento_de_tarefas.exceptions.RecursoNaoEncontradoException;
import com.swetonyancelmo.gerenciamento_de_tarefas.mappers.UserMapper;
import com.swetonyancelmo.gerenciamento_de_tarefas.models.User;
import com.swetonyancelmo.gerenciamento_de_tarefas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<User> listarTodos(){
        return userRepository.findAll();
    }

    public User buscarPorId(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário com o ID " + id + " não encontrado."));
    }

    public User criarUsuario(CriarUserRequestDTO dto){
        if(userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailJaCadastradoException("O email informado já está cadastrado.");
        }
        User novoUsuario = userMapper.toEntity(dto);
        return userRepository.save(novoUsuario);
    }

    public void deletarUsuarioPorId(Long id){
        userRepository.deleteById(id);
    }
}
