package com.learn.patient;

import com.learn.patient.entity.Patient;
import com.learn.patient.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    public static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @GetMapping("api/v1/getAllPatients")
    public String getAllPatients() {
        logger.info("getAllPatients:");
        List<Patient> allPatients = patientRepository.findAll();
        return "Get All Patients REST call !!";
    }

    @GetMapping("api/v1/getPatientById")
    public String getPatientById() {
        logger.info("getPatientById:");
        return "Get Patient By Id REST call !!";
    }

    @PostMapping("api/v1/savePatient")
    public ResponseEntity<Patient> savePatient(@RequestBody Patient patient) {
        logger.info("Patient details request body: {}", patient);
        Patient savedPatientDetails = patientRepository.save(patient);
        logger.info("Log savedPatientDetails: {}", savedPatientDetails);
        return ResponseEntity.ok().body(savedPatientDetails);
    }

}
