package com.example.bedsysteem.data.repository;

import com.example.bedsysteem.data.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Department, String> {
}
