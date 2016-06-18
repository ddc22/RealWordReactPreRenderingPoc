package com.dhis.medical.analytics.service;

import com.dhis.medical.analytics.model.PerformanceData;

import java.io.IOException;
import java.util.List;

/**
 * Created by dehan on 6/18/16.
 */
public interface HospitalPerformanceService {
    public List<PerformanceData> getAllHostpitalPerformanceData() throws IOException;
}
