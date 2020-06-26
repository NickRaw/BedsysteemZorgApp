package com.example.bedsysteem.service;

import com.example.bedsysteem.data.entity.Bed;
import com.example.bedsysteem.data.entity.Department;
import com.example.bedsysteem.data.entity.Patient;
import com.example.bedsysteem.data.entity.SleepTimepoint;
import com.example.bedsysteem.data.repository.PatientRepository;
import com.example.bedsysteem.data.repository.SleepTimepointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SleepTimepointService {

    @Autowired
    private SleepTimepointRepository sleepTimepointRepo;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private PatientService patientService;

    @Autowired
    private BedService bedService;


    public List<SleepTimepoint> getAll(){ return sleepTimepointRepo.findAll(); }

    public SleepTimepoint getOne(String id){ return sleepTimepointRepo.getOne(id); }

    public void addOne(SleepTimepoint sleepTimepoint){ sleepTimepointRepo.save(sleepTimepoint); }

    public boolean addNew(SleepTimepoint sleepTimepoint, String bed_id){
        Bed bed = bedService.getOneById(bed_id);
        Patient patient = patientService.getOneById(bed.getPatient().getId());
        if(patient != null){
            sleepTimepoint.setPatient(patient);
            sleepTimepoint.setDate(LocalDate.now());
            sleepTimepointRepo.save(sleepTimepoint);
            patient.addSleepPoint(sleepTimepoint);
            patientRepo.save(patient);
            return true;
        }
        return false;
    }

    public void deleteOne(SleepTimepoint sleepTimepoint){ sleepTimepointRepo.delete(sleepTimepoint); }

    public void changeOne(SleepTimepoint sleepTimepoint){
        SleepTimepoint oldSleepTimepoint = sleepTimepointRepo.getOne(sleepTimepoint.getId());
        oldSleepTimepoint.setDate(sleepTimepoint.getDate());
        oldSleepTimepoint.setHour(sleepTimepoint.getHour());
        oldSleepTimepoint.setMinute(sleepTimepoint.getMinute());
        oldSleepTimepoint.setPatient(sleepTimepoint.getPatient());
        sleepTimepointRepo.save(oldSleepTimepoint);
    }

    public List<SleepTimepoint> timepointsFromDateByHour(LocalDate date){
        return sleepTimepointRepo.findAllByDateOrderByHourDescMinuteDesc(date).stream().limit(4).collect(Collectors.toList());
    }

    public List<SleepTimepoint> timepointsFromPatientByHourMinuteSecond(Patient patient){
        return sleepTimepointRepo.findAllByPatientOrderByDateDescHourDescMinuteDesc(patient);
    }
}
