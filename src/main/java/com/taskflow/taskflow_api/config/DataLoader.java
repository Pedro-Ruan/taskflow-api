package com.taskflow.taskflow_api.config;

import com.taskflow.taskflow_api.entity.Category;
import com.taskflow.taskflow_api.entity.User;
import com.taskflow.taskflow_api.repository.CategoryRepository;
import com.taskflow.taskflow_api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public DataLoader(UserRepository userRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0 && categoryRepository.count() == 0) {
            User defaultUser = new User(null, "Pedro Ruan", "pedro@email.com", "DEVELOPER");
            userRepository.save(defaultUser);

            Category defaultCategory = new Category(null, "PROJETO");
            categoryRepository.save(defaultCategory);

            System.out.println("🌱 DataLoader: Banco vazio detectado. Carga inicial executada com sucesso!");
        } else {
            System.out.println("🌱 DataLoader: Banco já possui dados. Pulando carga inicial.");
        }
    }
}