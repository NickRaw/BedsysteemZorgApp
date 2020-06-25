package com.example.bedsysteem.data.repository;

import com.example.bedsysteem.data.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
}
