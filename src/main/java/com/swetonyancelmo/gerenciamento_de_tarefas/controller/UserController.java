package com.swetonyancelmo.gerenciamento_de_tarefas.controller;

import com.swetonyancelmo.gerenciamento_de_tarefas.dtos.CriarUserRequestDTO;
import com.swetonyancelmo.gerenciamento_de_tarefas.dtos.UserResponseDTO;
import com.swetonyancelmo.gerenciamento_de_tarefas.mappers.UserMapper;
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

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listarTodosUsuarios() {
        List<UserResponseDTO> dtos = userService.listarTodos()
                .stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> buscarUsuarioPorId(@PathVariable Long id) {
        User user = userService.buscarPorId(id);
        return ResponseEntity.ok(userMapper.toResponseDTO(user));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> criarNovoUsuario(@Valid @RequestBody CriarUserRequestDTO dto){
        User novoUsuario = userService.criarUsuario(dto);
        return new ResponseEntity<>(userMapper.toResponseDTO(novoUsuario), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizarUsuario(@PathVariable Long id,
                                                            @Valid @RequestBody CriarUserRequestDTO dto){
        User usuarioAtualizado = userService.atualizarUsuario(id, dto);
        return ResponseEntity.ok(userMapper.toResponseDTO(usuarioAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> deletarUsuario(@PathVariable Long id){
        userService.deletarUsuarioPorId(id);
        return ResponseEntity.noContent().build();
    }
}
