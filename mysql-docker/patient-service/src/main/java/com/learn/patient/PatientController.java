package com.learn.patient;

import com.learn.patient.entity.Patient;
import com.learn.patient.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    public static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @GetMapping("api/v1/getAllDummyPatients")
    public String getAllDummyPatients() {
        logger.info("getAllPatients:");
        List<Patient> allPatients = patientRepository.findAll();
        return "Get All Patients REST call !!";
    }

    @GetMapping("api/v1/getPatientById")
    public String getPatientById() {
        logger.info("getPatientById:");
        return "Get Patient By Id REST call !!";
    }

    @PostMapping("api/v1/patients/savePatient")
    public ResponseEntity<Patient> savePatient(@RequestBody Patient patient) {
        logger.info("savePatient");
        logger.info("Patient details request body: {}", patient);
        Patient savedPatientDetails = patientRepository.save(patient);
        logger.info("Log savedPatientDetails: {}", savedPatientDetails);
        return ResponseEntity.ok().body(savedPatientDetails);
    }

    @GetMapping("api/v1/patients/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable int id) {
        logger.info("getPatientById: {}", id);
        Optional<Patient> patientById = patientRepository.findById(id);
        return patientById.isPresent() ? ResponseEntity.ok(patientById.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found!!");
    }

    @GetMapping("api/v1/patients/get-all")
    public ResponseEntity<List<Patient>> getAllPatients() {
        logger.info("getAllPatients: ");
        return ResponseEntity.ok(patientRepository.findAll());
    }

}
