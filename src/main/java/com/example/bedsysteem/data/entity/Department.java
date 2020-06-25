package com.example.bedsysteem.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import java.util.List;
import java.util.UUID;

@Entity
public class Department {
    @Id
    private String id;
    private String departementName;

    @JsonIgnore
    @OneToMany
    private List<Patient> patients;

    @JsonIgnore
    @OneToMany
    private List<Bed> beds;

    @PrePersist
    void generateID(){ id = UUID.randomUUID().toString(); }
    public String getId (){ return this.id; }

    public String getDepartementName() { return departementName; }
    public void setDepartementName(String departementName) { this.departementName = departementName; }

    public List<Patient> getPatients() { return patients; }
    public void addToPatientList(Patient patient){ patients.add(patient); }
    public void removeFromPatientList(Patient patient){ patients.remove(patient); }

    public List<Bed> getBeds() { return beds; }
    public void addToBeds(Bed bed){ beds.add(bed); }
    public void removeFromBeds(Bed bed){ beds.remove(bed); }


}
