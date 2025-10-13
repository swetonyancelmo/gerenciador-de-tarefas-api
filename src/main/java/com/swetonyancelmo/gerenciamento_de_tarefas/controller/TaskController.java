package com.swetonyancelmo.gerenciamento_de_tarefas.controller;

import com.swetonyancelmo.gerenciamento_de_tarefas.dtos.AtualizarTaskRequestDTO;
import com.swetonyancelmo.gerenciamento_de_tarefas.dtos.CriarTaskRequestDTO;
import com.swetonyancelmo.gerenciamento_de_tarefas.dtos.TaskResponseDTO;
import com.swetonyancelmo.gerenciamento_de_tarefas.models.Task;
import com.swetonyancelmo.gerenciamento_de_tarefas.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    private TaskResponseDTO convertToResponseDTO(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitulo(),
                task.getDescricao(),
                task.isConcluida()
        );
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> listarTodasTarefas() {
        List<Task> tasks = taskService.listarTodas();
        List<TaskResponseDTO> dtos = tasks.stream()
                .map(TaskResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> buscarTarefaPorId(@PathVariable Long id) {
        try {
            Task task = taskService.buscarPorId(id);
            return ResponseEntity.ok(new TaskResponseDTO(task));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> criarTarefa(@RequestBody CriarTaskRequestDTO dto) {
        Task novaTarefa = taskService.criarTarefa(dto);
        return new ResponseEntity<>(convertToResponseDTO(novaTarefa), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> atualizarTarefa(@PathVariable long id,
                                                           @RequestBody AtualizarTaskRequestDTO dto) {
        try {
            Task task = new Task();
            task.setTitulo(dto.getTitulo());
            task.setDescricao(dto.getDescricao());
            task.setConcluida(dto.isConcluida());

            Task atualizado = taskService.atualizarTarefa(id, task);
            return ResponseEntity.ok(new TaskResponseDTO(atualizado));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<TaskResponseDTO> concluirTarefa(@PathVariable long id) {
        try {
            Task concluido = taskService.concluirTarefa(id);
            return ResponseEntity.ok(new TaskResponseDTO(concluido));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> deletarTarefa(@PathVariable long id) {
        taskService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
