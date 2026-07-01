package com.taskflow.taskflow_api.service;

import com.taskflow.taskflow_api.entity.Task;
import com.taskflow.taskflow_api.exception.ResourceNotFoundException;
import com.taskflow.taskflow_api.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
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
        return taskRepository.save(task);
    }

//    Update

    public Task update(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setCategory(updatedTask.getCategory());
        task.setPriority(updatedTask.getPriority());
        task.setTaskStatus(updatedTask.getTaskStatus());
        task.setUser(updatedTask.getUser());

        return taskRepository.save(task);
    }

//    Delete
    public void delete(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        taskRepository.delete(task);
    }

}
