package com.example.bedsysteem.data.repository;

import com.example.bedsysteem.data.entity.BedList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BedListRepository extends JpaRepository<BedList, String> {
}
