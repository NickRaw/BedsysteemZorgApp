package com.example.bedsysteem.data.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class SleepTimepoint {
    @Id
    private String id;

    @ManyToOne
    private Patient patient;

    private LocalDate date;

    private Integer hour;
    private Integer minute;
    private Integer second;


    public enum BedTypes{
        UITBED,
        INBED,
        TELAAT
    }
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255)")
    private BedTypes bedType;

    @PrePersist
    void generateID(){ id = UUID.randomUUID().toString(); }
    public String getId() { return id; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Integer getHour() { return hour; }
    public void setHour(Integer hour) { this.hour = hour; }

    public Integer getMinute() { return minute; }
    public void setMinute(Integer minute) { this.minute = minute; }

    public Integer getSecond() { return second; }
    public void setSecond(Integer second) { this.second = second; }

    public BedTypes getBedType() { return bedType; }
    public void setBedType(BedTypes bedType) { this.bedType = bedType; }
}
