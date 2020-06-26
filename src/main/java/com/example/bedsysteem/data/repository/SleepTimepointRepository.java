package com.example.bedsysteem.data.repository;

import com.example.bedsysteem.data.entity.Patient;
import com.example.bedsysteem.data.entity.SleepTimepoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SleepTimepointRepository extends JpaRepository<SleepTimepoint, String> {
    List<SleepTimepoint> findAllByDateOrderByHourDescMinuteDesc(LocalDate date);
    List<SleepTimepoint> findAllByPatientOrderByDateDescHourDescMinuteDesc(Patient patient);
}
