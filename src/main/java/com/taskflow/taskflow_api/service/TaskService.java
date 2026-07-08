package com.taskflow.taskflow_api.service;

import com.taskflow.taskflow_api.entity.Category;
import com.taskflow.taskflow_api.entity.Task;
import com.taskflow.taskflow_api.entity.User;
import com.taskflow.taskflow_api.exception.ResourceNotFoundException;
import com.taskflow.taskflow_api.repository.CategoryRepository;
import com.taskflow.taskflow_api.repository.TaskRepository;
import com.taskflow.taskflow_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, CategoryRepository categoryRepository){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

//    GetAll
    public List<Task> findAll() {
        return taskRepository.findAll();
    }


//    GetById
    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

//    Post
public Task save(Task task) {

    if (task.getUser() == null || task.getUser().getId() == null) {
        throw new ResourceNotFoundException("User id must not be null to create a task");
    }
    User user = userRepository.findById(task.getUser().getId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + task.getUser().getId()));

    if (task.getCategory() == null || task.getCategory().getId() == null) {
        throw new ResourceNotFoundException("Category id must not be null to create a task");
    }
    Category category = categoryRepository.findById(task.getCategory().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + task.getCategory().getId()));

    task.setUser(user);
    task.setCategory(category);

    return taskRepository.save(task);
}

//    Update
    public Task update(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        if (updatedTask.getUser() == null || updatedTask.getUser().getId() == null) {
            throw new ResourceNotFoundException("User id must not be null to update a task");
        }
        User user = userRepository.findById(updatedTask.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + updatedTask.getUser().getId()));

        if (updatedTask.getCategory() == null || updatedTask.getCategory().getId() == null) {
            throw new ResourceNotFoundException("Category id must not be null to update a task");
        }
        Category category = categoryRepository.findById(updatedTask.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + updatedTask.getCategory().getId()));

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setPriority(updatedTask.getPriority());
        task.setTaskStatus(updatedTask.getTaskStatus());

        task.setUser(user);
        task.setCategory(category);

        return taskRepository.save(task);
    }

//    Delete
    public void delete(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        taskRepository.delete(task);
    }

}
