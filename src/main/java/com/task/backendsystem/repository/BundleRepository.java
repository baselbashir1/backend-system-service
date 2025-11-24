package com.task.backendsystem.repository;

import com.task.backendsystem.model.Bundle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BundleRepository extends JpaRepository<Bundle, Long> {
}