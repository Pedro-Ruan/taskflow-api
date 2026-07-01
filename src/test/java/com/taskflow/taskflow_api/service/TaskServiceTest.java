package com.taskflow.taskflow_api.service;

import com.taskflow.taskflow_api.entity.Task;
import com.taskflow.taskflow_api.entity.TaskStatus;
import com.taskflow.taskflow_api.entity.Priority; // IMPORTANTE: Importar o seu novo Enum de Prioridade
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
        dummyTask.setTaskStatus(TaskStatus.PENDING);
        dummyTask.setPriority(Priority.HIGH); // Definindo a prioridade inicial

        when(taskRepository.findById(1L)).thenReturn(Optional.of(dummyTask));

        Task result = taskService.findById(1L);

        assertNotNull(result);
        assertEquals("Study Unit Tests", result.getTitle());
        assertEquals(TaskStatus.PENDING, result.getTaskStatus());
        assertEquals(Priority.HIGH, result.getPriority()); // Verificando a prioridade

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
        inputTask.setTaskStatus(TaskStatus.PENDING);
        inputTask.setPriority(Priority.MEDIUM); // Definindo prioridade no input

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle("New Task");
        savedTask.setTaskStatus(TaskStatus.PENDING);
        savedTask.setPriority(Priority.MEDIUM); // Definindo prioridade no retorno salvo

        when(taskRepository.save(inputTask)).thenReturn(savedTask);

        Task result = taskService.save(inputTask);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(TaskStatus.PENDING, result.getTaskStatus());
        assertEquals(Priority.MEDIUM, result.getPriority()); // Validando prioridade salva
        verify(taskRepository, times(1)).save(inputTask);
    }

    @Test
    void shouldUpdateTaskWhenIdExists(){
        // Task original simulada no banco (PENDING e LOW)
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setTitle("Old Title");
        existingTask.setTaskStatus(TaskStatus.PENDING);
        existingTask.setPriority(Priority.LOW);

        // Dados enviados para atualização (IN_PROGRESS e HIGH)
        Task updatedDetails = new Task();
        updatedDetails.setTitle("Updated Title");
        updatedDetails.setTaskStatus(TaskStatus.IN_PROGRESS);
        updatedDetails.setPriority(Priority.HIGH);

        // O que o repository vai de fato salvar e retornar
        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle("Updated Title");
        savedTask.setTaskStatus(TaskStatus.IN_PROGRESS);
        savedTask.setPriority(Priority.HIGH);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        Task result = taskService.update(1L, updatedDetails);

        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
        assertEquals(TaskStatus.IN_PROGRESS, result.getTaskStatus());
        assertEquals(Priority.HIGH, result.getPriority()); // Garante que o service atualizou a prioridade com sucesso!

        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void shouldDeleteTaskWhenIdExists() {
        Task existingTask = new Task();
        existingTask.setId(1L);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        doNothing().when(taskRepository).delete(existingTask);

        assertDoesNotThrow(() -> taskService.delete(1L));

        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).delete(existingTask);
    }
}