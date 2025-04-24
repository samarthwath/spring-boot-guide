package com.learn.doctor.controller;

import com.learn.doctor.entity.Doctor;
import com.learn.doctor.payload.request.DoctorRequest;
import com.learn.doctor.services.DoctorService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    public static final Logger logger = LoggerFactory.getLogger(DoctorController.class);


    @PostMapping("/save-doctor")
    public ResponseEntity<Doctor> saveDoctor(@Valid @RequestBody DoctorRequest doctorRequest) {
        logger.info("----- saveDoctor: -----");
        logger.info("Doctor details request body: {}", doctorRequest);
        Doctor savedDoctor = doctorService.saveDoctor(doctorRequest);
        return ResponseEntity.ok().body(savedDoctor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int id) throws Exception {
        logger.info("----- getDoctorById -----: {}", id);
        Doctor doctorById = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctorById);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        logger.info("----- getAllDoctors: -----");
        List<Doctor> allDoctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(allDoctors);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@Valid @RequestBody DoctorRequest doctorRequest, @PathVariable int id) throws Exception {
        logger.info("----- updateDoctor -----: {} {}", doctorRequest, id);
        Doctor updatedDoctor = doctorService.updateDoctor(id, doctorRequest);
        return ResponseEntity.ok(updatedDoctor);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<Doctor> getDoctorByEmail(@PathVariable String email) throws Exception {
        logger.info("----- getDoctorByEmail -----: {}", email);
        Doctor doctorByEmail = doctorService.getDoctorByEmail(email);
        return ResponseEntity.ok(doctorByEmail);
    }

}
