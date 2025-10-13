package com.swetonyancelmo.gerenciamento_de_tarefas.controller;

import com.swetonyancelmo.gerenciamento_de_tarefas.dtos.AtualizarTaskRequestDTO;
import com.swetonyancelmo.gerenciamento_de_tarefas.dtos.CriarTaskRequestDTO;
import com.swetonyancelmo.gerenciamento_de_tarefas.dtos.TaskResponseDTO;
import com.swetonyancelmo.gerenciamento_de_tarefas.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    // Lista de Tarefas (simula um banco de dados)
    List<Task> listaDeTarefas = new ArrayList<>();
    // Usado para autoincrementar os ids
    private final AtomicLong contadorDeId = new AtomicLong();

    // Retorna todas as tarefas da lista
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> listarTodasTarefas() {
        List<TaskResponseDTO> dtos = listaDeTarefas.stream()
                .map(task -> new TaskResponseDTO(task))
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // Retorna uma tarefa específica por id
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> buscarTarefaPorId(@PathVariable long id) {
        return listaDeTarefas.stream()
                .filter(tarefa -> tarefa.getId().equals(id))
                .findFirst()
                .map(tarefaEncontrada -> ResponseEntity.ok(new TaskResponseDTO(tarefaEncontrada)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Cria uma nova tarefa
    @PostMapping
    public ResponseEntity<TaskResponseDTO> criarTarefa(@RequestBody CriarTaskRequestDTO dto) {
        Task task = new Task();
        task.setId(contadorDeId.incrementAndGet());
        task.setTitulo(dto.getTitulo());
        task.setDescricao(dto.getDescricao());

        listaDeTarefas.add(task);

        return new ResponseEntity<>(new TaskResponseDTO(task), HttpStatus.CREATED);
    }

    // Atualiza todos os campos de uma tarefa específica
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> atualizarTarefa(@PathVariable long id,
                                                           @RequestBody AtualizarTaskRequestDTO dto) {
        for (Task task : listaDeTarefas) {
            if (task.getId().equals(id)) {
                task.setTitulo(dto.getTitulo());
                task.setDescricao(dto.getDescricao());
                task.setConcluida(dto.isConcluida());
                return ResponseEntity.ok(new TaskResponseDTO(task));
            }
        }
        return ResponseEntity.notFound().build();
    }

    // Atualiza o campo concluido para true
    @PatchMapping("/{id}/concluir")
    public ResponseEntity<TaskResponseDTO> concluirTarefa(@PathVariable long id) {
        for (Task task : listaDeTarefas) {
            if (task.getId().equals(id)) {
                task.setConcluida(true);
                return ResponseEntity.ok(new TaskResponseDTO(task));
            }
        }
        return ResponseEntity.notFound().build();
    }

    // Exclui uma tarefa por id
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> deletarTarefa(@PathVariable long id) {
        boolean removido = listaDeTarefas.removeIf(tarefa -> tarefa.getId().equals(id));
        if (removido) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
