package com.swetonyancelmo.gerenciamento_de_tarefas.controller;

import com.swetonyancelmo.gerenciamento_de_tarefas.dtos.CriarUserRequestDTO;
import com.swetonyancelmo.gerenciamento_de_tarefas.dtos.UserResponseDTO;
import com.swetonyancelmo.gerenciamento_de_tarefas.models.User;
import com.swetonyancelmo.gerenciamento_de_tarefas.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private UserResponseDTO convertToResponseDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getNome(),
                user.getEmail()
        );
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listarTodosUsuarios() {
        List<User> usuarios = userService.listarTodos();
        List<UserResponseDTO> dtos = usuarios.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> buscarUsuarioPorId(@PathVariable Long id) {
        User user = userService.buscarPorId(id);
        return ResponseEntity.ok(convertToResponseDTO(user));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> criarNovoUsuario(@Valid @RequestBody CriarUserRequestDTO dto){
        User novoUsuario = userService.criarUsuario(dto);
        return new ResponseEntity<>(convertToResponseDTO(novoUsuario), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> deletarUsuario(@PathVariable Long id){
        userService.deletarUsuarioPorId(id);
        return ResponseEntity.noContent().build();
    }
}
