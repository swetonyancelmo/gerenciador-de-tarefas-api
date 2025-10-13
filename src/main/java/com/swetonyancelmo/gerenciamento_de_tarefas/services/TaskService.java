package com.swetonyancelmo.gerenciamento_de_tarefas.services;

import com.swetonyancelmo.gerenciamento_de_tarefas.dtos.CriarTaskRequestDTO;
import com.swetonyancelmo.gerenciamento_de_tarefas.exceptions.RecursoNaoEncontradoException;
import com.swetonyancelmo.gerenciamento_de_tarefas.models.Task;
import com.swetonyancelmo.gerenciamento_de_tarefas.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> listarTodas() {
        return taskRepository.findAll();
    }

    public Task buscarPorId(Long id) throws Exception {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tarefa com ID " + id + " não encontrada."));
    }

    public Task criarTarefa(CriarTaskRequestDTO dto) {
        Task task = new Task();
        task.setTitulo(dto.getTitulo());
        task.setDescricao(dto.getDescricao());
        task.setConcluida(false);
        return taskRepository.save(task);
    }

    public Task atualizarTarefa(Long id, Task task) throws Exception {
        Task tarefaExistente = taskRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tarefa com ID " + id + " não encontrada."));
        tarefaExistente.setTitulo(task.getTitulo());
        tarefaExistente.setDescricao(task.getDescricao());
        tarefaExistente.setConcluida(task.isConcluida());

        return taskRepository.save(tarefaExistente);
    }

    public Task concluirTarefa(Long id) throws Exception {
        Task tarefaExistente = taskRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tarefa com ID " + id + " não encontrada."));
        tarefaExistente.setConcluida(true);

        return taskRepository.save(tarefaExistente);
    }

    public void deletarPorId(Long id) {
        taskRepository.deleteById(id);
    }
}
