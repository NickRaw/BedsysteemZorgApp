package com.example.bedsysteem.service;

import com.example.bedsysteem.data.entity.Department;
import com.example.bedsysteem.data.entity.Patient;
import com.example.bedsysteem.data.entity.SleepTimepoint;
import com.example.bedsysteem.data.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SleepTimepointService sleepTimepointService;

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    public List<Patient> getAll(){ return patientRepo.findAll(); }
    public Patient getOneById(String id){ return patientRepo.getOne(id); }


}
