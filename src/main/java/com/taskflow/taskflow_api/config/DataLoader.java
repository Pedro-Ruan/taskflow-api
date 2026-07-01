package com.taskflow.taskflow_api.config;

import com.taskflow.taskflow_api.entity.*;
import com.taskflow.taskflow_api.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    public DataLoader(UserRepository userRepository,
                      CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        userRepository.save(new User(null, "Pedro", "pedro@email.com", "GESTOR"));
        categoryRepository.save(new Category(null, "PROJETO"));
    }
}