package com.learn.patient.controller;

import com.learn.patient.entity.Patient;
import com.learn.patient.payload.request.PatientRequest;
import com.learn.patient.repository.PatientRepository;
import com.learn.patient.services.PatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    public static final Logger logger = LoggerFactory.getLogger(PatientController.class);


    @PostMapping("/save-patient")
    public ResponseEntity<Patient> savePatient(@Valid @RequestBody PatientRequest patientRequest) {
        logger.info("----- savePatient: -----");
        logger.info("Patient details request body: {}", patientRequest);
        Patient savedPatient = patientService.savePatient(patientRequest);
        return ResponseEntity.ok().body(savedPatient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int id) throws Exception {
        logger.info("----- getPatientById -----: {}", id);
        Patient patientById = patientService.getPatientById(id);
        return ResponseEntity.ok(patientById);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Patient>> getAllPatients() {
        logger.info("----- getAllPatients: -----");
        List<Patient> allPatients = patientService.getAllPatients();
        return ResponseEntity.ok(allPatients);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@Valid @RequestBody PatientRequest patientRequest, @PathVariable int id) throws Exception {
        logger.info("----- updatePatient -----: {} {}", patientRequest, id);
        Patient updatedPatient = patientService.updatePatient(id, patientRequest);
        return ResponseEntity.ok(updatedPatient);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<Patient> getPatientByEmail(@PathVariable String email) throws Exception {
        logger.info("----- getPatientByEmail -----: {}", email);
        Patient patientByEmail = patientService.getPatientByEmail(email);
        return ResponseEntity.ok(patientByEmail);
    }

}
