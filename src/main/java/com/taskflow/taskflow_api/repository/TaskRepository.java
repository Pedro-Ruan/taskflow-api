package com.taskflow.taskflow_api.repository;

import com.taskflow.taskflow_api.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean existsByUserId(Long id);
}
