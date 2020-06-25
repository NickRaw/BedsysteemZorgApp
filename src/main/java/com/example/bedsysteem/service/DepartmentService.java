package com.example.bedsysteem.service;

import com.example.bedsysteem.data.entity.Department;
import com.example.bedsysteem.data.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartementRepository departementRepo;

    public List<Department> getAll(){ return departementRepo.findAll(); }
}
