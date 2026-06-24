package com.taskflow.taskflow_api.repository;

import com.taskflow.taskflow_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
