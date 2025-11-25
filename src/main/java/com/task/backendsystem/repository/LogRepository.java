package com.task.backendsystem.repository;

import com.task.backendsystem.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}