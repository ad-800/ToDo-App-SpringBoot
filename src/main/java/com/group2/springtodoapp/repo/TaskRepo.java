package com.group2.springtodoapp.repo;

import com.group2.springtodoapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TaskRepo extends JpaRepository<Task, Long> {
}
