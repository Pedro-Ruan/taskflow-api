package com.taskflow.taskflow_api.repository;

import com.taskflow.taskflow_api.entity.Category;
import com.taskflow.taskflow_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
