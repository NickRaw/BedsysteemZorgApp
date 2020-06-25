package com.example.bedsysteem.data.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Bed {

    @Id
    private String id;

    @ManyToOne
    private Department department;

    @OneToOne
    private Patient patient;

    @PrePersist
    void generateID(){ id = UUID.randomUUID().toString(); }

    public String getId() { return id; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department afdeling) { this.department = afdeling; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
}
