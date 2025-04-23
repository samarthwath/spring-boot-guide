package com.learn.doctor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DoctorController {

    public static final Logger logger = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("api/v1/getAllDoctors")
    public String getAllPatients() {
        return "Get All Doctors REST call !!";
    }

    @GetMapping("api/v1/getDoctorById")
    public String getPatientById() {
        return "Get Doctor By Id REST call !!";
    }

    @GetMapping("api/v1/getAllPatientsFromDoctorService")
    public String getAllPatientsFromDoctorService() {
        logger.info("Doctor Service: getAllPatientsFromDoctorService");
        String url = "http://patient-service:8080/api/v1/getAllPatients";
        return restTemplate.getForObject(url, String.class);
    }
}
