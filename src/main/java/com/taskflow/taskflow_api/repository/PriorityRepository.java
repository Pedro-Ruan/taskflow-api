package com.taskflow.taskflow_api.repository;

import com.taskflow.taskflow_api.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
