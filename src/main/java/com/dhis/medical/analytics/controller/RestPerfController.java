package com.dhis.medical.analytics.controller;

import com.dhis.medical.analytics.model.HtmlString;
import com.dhis.medical.analytics.model.PerformanceData;
import com.dhis.medical.analytics.service.HospitalPerformanceService;
import com.dhis.medical.analytics.service.ReactPrerender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.script.ScriptException;
import java.io.IOException;
import java.util.List;

/**
 * Created by dehan on 6/18/16.
 */
@RestController
public class RestPerfController {

    @Autowired
    public HospitalPerformanceService hospitalPerformanceService;


    @Autowired
    ReactPrerender reactPrerender;

    @RequestMapping(value = "/perfjson")
    public List<PerformanceData> performanceDatas(Model model) throws IOException {
        model.addAttribute("greeting", "Hello World");
        List perfList = hospitalPerformanceService.getAllHostpitalPerformanceData();
        return perfList;
    }

    @RequestMapping(value = "/perfjson/preRender")
    public HtmlString performanceDatasPreRendered(Model model) throws IOException, ScriptException, NoSuchMethodException {
        model.addAttribute("greeting", "Hello World");
        List<PerformanceData> perfList = hospitalPerformanceService.getAllHostpitalPerformanceData();
        HtmlString html = reactPrerender.getHospitalPerformancePage(perfList);
        return html;
    }

}
