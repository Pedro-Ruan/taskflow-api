package com.taskflow.taskflow_api.service;

import com.taskflow.taskflow_api.entity.Task;
import com.taskflow.taskflow_api.exception.ResourceNotFoundException;
import com.taskflow.taskflow_api.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnTaskWhenIdExists(){
        Task dummyTask = new Task();
        dummyTask.setId(1L);
        dummyTask.setTitle("Study Unit Tests");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(dummyTask));

        Task result = taskService.findById(1L);

        assertNotNull(result);
        assertEquals("Study Unit Tests", result.getTitle());

        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenIdDoesNotExist(){

        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            taskService.findById(99L);
        });

        verify(taskRepository, times(1)).findById(99L);
    }

    @Test
    void shouldSaveTaskSuccessfully(){
        Task inputTask = new Task();
        inputTask.setTitle("New Task");

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle("New Task");

        when(taskRepository.save(inputTask)).thenReturn(savedTask);

        Task result = taskService.save(inputTask);

        assertNotNull(result);
        assertEquals(1L,result.getId());
        verify(taskRepository, times(1)).save(inputTask);
    }

    @Test
    void shouldUpdateTaskWhenIdExists(){
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setTitle("Old Title");

        Task updatedDetails = new Task();
        updatedDetails.setTitle("Updated Title");

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle("Updated Title");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        Task result = taskService.update(1L, updatedDetails);

        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void shouldDeleteTaskWhenIdExists() {
        // 1. ARRANGE
        Task existingTask = new Task();
        existingTask.setId(1L);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        doNothing().when(taskRepository).delete(existingTask);

        // 2. ACT & ASSERT
        assertDoesNotThrow(() -> taskService.delete(1L));

        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).delete(existingTask);
    }

}
