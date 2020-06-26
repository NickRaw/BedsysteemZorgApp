package com.example.bedsysteem.controller;

import com.example.bedsysteem.data.entity.Patient;
import com.example.bedsysteem.data.entity.SleepTimepoint;
import com.example.bedsysteem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;


@Controller
public class TemplateController {

    @Autowired
    private BedService bedService;

    @Autowired
    private SleepTimepointService sleepTimepointService;

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String Index(Model model){
        model.addAttribute("bedsList", bedService.getAll());
        model.addAttribute("logList", sleepTimepointService.timepointsFromDateByHour(LocalDate.now()));
        model.addAttribute("testPatientID", bedService.getAll().get(0).getPatient().getId());
        return "index";
    }

    @GetMapping("/ouder/{id}")
    public String Ouder(Model model, @PathVariable String id){
        Patient patient = patientService.getOneById(id);
        model.addAttribute("patient", patient);
        model.addAttribute("sleeppoints", sleepTimepointService.timepointsFromPatientByHourMinuteSecond(patient));
        return "ouder";
    }
}
