package com.learn.doctor;

import com.learn.doctor.entity.Doctor;
import com.learn.doctor.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;


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
        String url = "http://patient-service:8080/api/v1/getAllDummyPatients";
        return restTemplate.getForObject(url, String.class);
    }

    @PostMapping("api/v1/saveDoctor")
    public ResponseEntity<Doctor> saveDoctor(@RequestBody Doctor doctor) {
        logger.info("saveDoctor:");
        logger.info("Doctor details request body: {}", doctor);
        Doctor saveDoctorDetails = doctorRepository.save(doctor);
        logger.info("Log savedDoctorDetails: {}", saveDoctorDetails);
        return ResponseEntity.ok().body(saveDoctorDetails);
    }

    @GetMapping("api/v1/get-doctor/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable int id) {
        logger.info("get getDoctorById: {}", id);
        Optional<Doctor> doctorById = doctorRepository.findById(id);
        return doctorById.isPresent() ? ResponseEntity.ok().body(doctorById.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found with Id: " + id);
    }

    @GetMapping("api/v1/get-all-doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        logger.info("getAllDoctors: ");
        return ResponseEntity.ok(doctorRepository.findAll());
    }


}
