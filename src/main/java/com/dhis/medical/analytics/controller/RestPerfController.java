package com.dhis.medical.analytics.controller;

import com.dhis.medical.analytics.model.PerformanceData;
import com.dhis.medical.analytics.service.HospitalPerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by dehan on 6/18/16.
 */
@RestController
public class RestPerfController {

    @Autowired
    public HospitalPerformanceService hospitalPerformanceService;


    @RequestMapping(value = "/perfjson")
    public List<PerformanceData> sayHello(Model model) throws IOException {
        model.addAttribute("greeting", "Hello World");
        List perfList = hospitalPerformanceService.getAllHostpitalPerformanceData();
        return perfList;
    }
}
