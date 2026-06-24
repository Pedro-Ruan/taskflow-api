package com.taskflow.taskflow_api.controller;

import com.taskflow.taskflow_api.entity.Task;
import com.taskflow.taskflow_api.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

//    GET /tasks
    @GetMapping
    public List<Task> getAll(){
        return taskService.findAll();
    }

//    GET /tasks/{id}
    @GetMapping("/{id}")
    public Optional<Task> findById(@PathVariable Long id){
        return taskService.findById(id);
    }

//    POST /tasks
    @PostMapping
    public Task save(@RequestBody Task task){
        return taskService.save(task);
    }

//    PUT /tasks/{id}
    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task){
        return taskService.update(id, task);
    }

//    DELETE /tasks/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        taskService.delete(id);
    }



}
