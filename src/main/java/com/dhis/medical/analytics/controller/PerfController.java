package com.dhis.medical.analytics.controller;

import com.dhis.medical.analytics.service.HospitalPerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * Created by dehan on 6/18/16.
 */
@Controller
public class PerfController {

    @Autowired
    public HospitalPerformanceService hospitalPerformanceService;

    @RequestMapping(value = "/greeting")
    public String sayHello(Model model) throws IOException{
        model.addAttribute("greeting", "Hello World");
        hospitalPerformanceService.getAllHostpitalPerformanceData();
        return "hello";
    }
}
