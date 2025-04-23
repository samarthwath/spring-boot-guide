package com.learn.patient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    public static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @GetMapping("api/v1/getAllPatients")
    public String getAllPatients() {
        logger.info("getAllPatients:");
        return "Get All Patients REST call !!";
    }

    @GetMapping("api/v1/getPatientById")
    public String getPatientById() {
        logger.info("getPatientById:");
        return "Get Patient By Id REST call !!";
    }
}
