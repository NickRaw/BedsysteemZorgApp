package com.example.bedsysteem.controller;

import com.example.bedsysteem.data.entity.SleepTimepoint;
import com.example.bedsysteem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;


@Controller
public class TemplateController {

    @Autowired
    private BedService bedService;

    @Autowired
    private SleepTimepointService sleepTimepointService;

    @GetMapping
    public String Index(Model model){
        model.addAttribute("bedsList", bedService.getAll());
        model.addAttribute("logList", sleepTimepointService.timepointsFromDateByHour(LocalDate.now()));
        return "index";
    }
}
