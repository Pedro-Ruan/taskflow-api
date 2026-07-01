package com.taskflow.taskflow_api.controller;

import com.taskflow.taskflow_api.entity.Task;
import com.taskflow.taskflow_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

//    GET /tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAll(){
        List<Task> tasks = taskService.findAll();
        return ResponseEntity.ok(tasks);
    }

//    GET /tasks/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id){
        Task task = taskService.findById(id);
        return ResponseEntity.ok(task);
    }

//    POST /tasks
    @PostMapping
    public ResponseEntity<Task> save(@Valid @RequestBody Task task){
        Task savedTask = taskService.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

//    PUT /tasks/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @Valid @RequestBody Task task){
        Task updatedTask = taskService.update(id, task);
        return ResponseEntity.ok(updatedTask);
    }

//    DELETE /tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
