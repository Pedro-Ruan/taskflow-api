package com.taskflow.taskflow_api.service;

import com.taskflow.taskflow_api.entity.User;
import com.taskflow.taskflow_api.exception.ResourceNotFoundException;
import com.taskflow.taskflow_api.repository.TaskRepository;
import com.taskflow.taskflow_api.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public UserService(UserRepository userRepository, TaskRepository taskRepository){
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

//    GetAll
    public List<User> findAll(){
        return userRepository.findAll();
    }

//    GetById
    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

//    Post
    public User save(User user){
        return userRepository.save(user);
    }

//    Delete
    public void delete(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        if (taskRepository.existsByUserId(id)){
            throw new DataIntegrityViolationException("Cannot delete user. This user is currently assigned to one or more tasks");
        }

        userRepository.delete(user);
    }

}
