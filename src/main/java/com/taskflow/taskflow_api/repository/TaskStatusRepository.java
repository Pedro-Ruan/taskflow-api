package com.taskflow.taskflow_api.repository;

import com.taskflow.taskflow_api.entity.TaskStatus;
import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
}
