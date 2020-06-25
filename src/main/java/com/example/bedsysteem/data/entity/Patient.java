package com.example.bedsysteem.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Entity
public class Patient {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private Integer age;
    private Integer totalTimeAway;

    @JsonIgnore
    @OneToOne
    private Bed bed;

    @JsonIgnore
    @ManyToOne
    private Department departement;

    @OneToMany
    private List<SleepTimepoint> sleepPoints;

    @PrePersist
    void generateID(){ id = UUID.randomUUID().toString(); }
    public String getId() { return id; }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public Bed getBed() {
        return bed;
    }
    public void setBed(Bed bed) {
        this.bed = bed;
    }

    public Department getDepartement() {
        return departement;
    }
    public void setDepartement(Department departement) {
        this.departement = departement;
    }

    public Integer getTotalTimeAway() { return totalTimeAway; }
    public void setTotalTimeAway(Integer totalTimeAway) { this.totalTimeAway = totalTimeAway; }

    public List<SleepTimepoint> getSleepPoints() { return sleepPoints; }
    public void addSleepPoint(SleepTimepoint sleepPoint) { this.sleepPoints.add(sleepPoint); }
    public SleepTimepoint getLastSleepPoint(){
        if(sleepPoints.size() > 0){
            return sleepPoints.get(sleepPoints.size()-1);
        }
        else{
            return null;
        }
    }
    public Long timeSinceOutBed(){
        SleepTimepoint sleepTimepoint = getLastSleepPoint();
        LocalTime oldTime;
        if(sleepTimepoint.getBedType() == SleepTimepoint.BedTypes.TELAAT){
            SleepTimepoint previousSleeppoint = sleepTimepoint.getPatient().getSleepPoints().get(sleepTimepoint.getPatient().getSleepPoints().size()-2);
            oldTime = LocalTime.of(previousSleeppoint.getHour(),previousSleeppoint.getMinute(),previousSleeppoint.getSecond());
        }
        else{
            oldTime = LocalTime.of(sleepTimepoint.getHour(),sleepTimepoint.getMinute(),sleepTimepoint.getSecond());
        }
        LocalTime time = LocalTime.now();
        Long difference = oldTime.until(time, ChronoUnit.SECONDS);


        return difference;
    }
}
