package com.swetonyancelmo.gerenciamento_de_tarefas.repositories;

import com.swetonyancelmo.gerenciamento_de_tarefas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
