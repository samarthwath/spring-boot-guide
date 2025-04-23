package com.learn.doctor;

import com.learn.doctor.entity.Doctor;
import com.learn.doctor.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
        String url = "http://patient-service:8080/api/v1/getAllPatients";
        return restTemplate.getForObject(url, String.class);
    }

    @PostMapping("api/v1/saveDoctor")
    public ResponseEntity<Doctor> savePatient(@RequestBody Doctor doctor) {
        logger.info("Doctor details request body: {}", doctor);
        Doctor saveDoctorDetails = doctorRepository.save(doctor);
        logger.info("Log savedDoctorDetails: {}", saveDoctorDetails);
        return ResponseEntity.ok().body(saveDoctorDetails);
    }

}
