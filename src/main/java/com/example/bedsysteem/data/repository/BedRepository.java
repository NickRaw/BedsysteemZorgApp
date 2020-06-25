package com.example.bedsysteem.data.repository;

import com.example.bedsysteem.data.entity.Bed;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BedRepository extends JpaRepository<Bed, String> {
}
