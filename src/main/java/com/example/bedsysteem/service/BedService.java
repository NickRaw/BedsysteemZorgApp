package com.example.bedsysteem.service;

import com.example.bedsysteem.data.entity.Bed;
import com.example.bedsysteem.data.repository.BedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedService {
    @Autowired
    private BedRepository bedRepo;

    public List<Bed> getAll(){ return bedRepo.findAll(); }
    public Bed getOneById(String id){return bedRepo.getOne(id);}

}
