package com.example.bedsysteem.controller;
import com.example.bedsysteem.data.entity.*;
import com.example.bedsysteem.service.BedService;
import com.example.bedsysteem.service.PatientService;
import com.example.bedsysteem.service.SleepTimepointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    @Autowired
    private SleepTimepointService sleepTimepointService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private BedService bedService;

    @PostMapping("/{id}/timepoint/new")
    public ResponseMessage addTimepoint(@PathVariable String id, @RequestBody SleepTimepoint timepoint){
        Boolean timepointResponse = sleepTimepointService.addNew(timepoint, id);

        ResponseMessage message = new ResponseMessage();
        if(timepointResponse){
            message.setStatusType(ResponseMessage.StatusTypes.SUCCESS);
            message.setMessage("Your request has been send!");
        }
        else{
            message.setStatusType(ResponseMessage.StatusTypes.FAILED);
            message.setMessage("There is no patient in bed!");
        }
        return message;
    }

    @GetMapping("/timepoint/{id}")
    public SleepTimepoint getTimepoint(@PathVariable String id){
        return sleepTimepointService.getOne(id);
    }

    @PostMapping("/timepoint/change")
    public SleepTimepoint changeTimepoint(@RequestBody SleepTimepoint timepoint){
        sleepTimepointService.changeOne(timepoint);
        return sleepTimepointService.getOne(timepoint.getId());
    }

    @PostMapping("/timepoint/delete")
    public void deleteTimepoint(@RequestBody SleepTimepoint timepoint){
        sleepTimepointService.deleteOne(timepoint);
    }

    @GetMapping("/patient/{id}")
    public Patient getPatient(@PathVariable String id){
        return patientService.getOneById(id);
    }

    @GetMapping("/beds")
    public BedList getBeds(){
        BedList beds = new BedList();
        beds.setBeds(bedService.getAll());
        return beds;
    }

}
