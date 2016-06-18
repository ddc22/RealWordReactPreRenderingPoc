package com.dhis.medical.analytics.service;

import com.dhis.medical.analytics.model.PerformanceData;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dehan on 6/18/16.
 */

@Service
public class HospitalPerformanceServiceImpl implements HospitalPerformanceService{

    public List<PerformanceData> getAllHostpitalPerformanceData() throws IOException{
        List<PerformanceData> perfList = new ArrayList<>();

        InputStream in = getClass().getClassLoader().getResourceAsStream("data/dataset.csv");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        for (int i = 0; i < 200; i++) {
            String line = bufferedReader.readLine();
            String[] row = bufferedReader.readLine().split(",");
            PerformanceData performanceData = new PerformanceData();
            performanceData.setProviderID(row[0]);
            performanceData.setHospitalName(row[1]);
            performanceData.setAddress(row[2]);
            performanceData.setCity(row[3]);
            performanceData.setState(row[4]);
            performanceData.setZIPCode(row[5]);
            performanceData.setCriteria(row[8]);
            performanceData.setScore(row[9]);
            System.out.println(line);

            perfList.add(performanceData);



        }


        return perfList;
    }
}
