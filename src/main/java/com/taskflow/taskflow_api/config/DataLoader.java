package com.taskflow.taskflow_api.config;

import com.taskflow.taskflow_api.entity.*;
import com.taskflow.taskflow_api.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PriorityRepository priorityRepository;
    private final TaskStatusRepository taskStatusRepository;

    public DataLoader(UserRepository userRepository,
                      CategoryRepository categoryRepository,
                      PriorityRepository priorityRepository,
                      TaskStatusRepository taskStatusRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.priorityRepository = priorityRepository;
        this.taskStatusRepository = taskStatusRepository;
    }

    @Override
    public void run(String... args) {
        // Cria apenas 1 registro de cada para gerar o ID 1 no banco
        userRepository.save(new User(null, "Pedro", "pedro@email.com", "GESTOR"));
        categoryRepository.save(new Category(null, "PROJETO"));
        priorityRepository.save(new Priority(null, "ALTA"));
        taskStatusRepository.save(new TaskStatus(null, "PENDENTE"));
    }
}