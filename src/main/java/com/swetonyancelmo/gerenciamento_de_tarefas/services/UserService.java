package com.swetonyancelmo.gerenciamento_de_tarefas.services;

import com.swetonyancelmo.gerenciamento_de_tarefas.dtos.CriarUserRequestDTO;
import com.swetonyancelmo.gerenciamento_de_tarefas.exceptions.EmailJaCadastradoException;
import com.swetonyancelmo.gerenciamento_de_tarefas.exceptions.RecursoNaoEncontradoException;
import com.swetonyancelmo.gerenciamento_de_tarefas.models.User;
import com.swetonyancelmo.gerenciamento_de_tarefas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listarTodos(){
        return userRepository.findAll();
    }

    public User buscarPorId(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário com o ID " + id + " não encontrado."));
    }

    public User criarUsuario(CriarUserRequestDTO dto){
        User user = new User();
        if(userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailJaCadastradoException("O email informado já está cadastrado.");
        }
        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());
        user.setSenha((dto.getSenha()));
        return userRepository.save(user);
    }

    public void deletarUsuarioPorId(Long id){
        userRepository.deleteById(id);
    }
}
