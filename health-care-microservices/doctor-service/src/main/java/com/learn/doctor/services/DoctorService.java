package com.learn.doctor.services;

import com.learn.doctor.entity.Doctor;
import com.learn.doctor.exceptions.DoctorNotFoundException;
import com.learn.doctor.payload.request.DoctorRequest;
import com.learn.doctor.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor saveDoctor(DoctorRequest doctorRequest) {
        logger.info("----- saveDoctor service: -----");
        Doctor doctor = new Doctor(doctorRequest.getFirstName(), doctorRequest.getLastName(), doctorRequest.getEmail(), doctorRequest.getSpeciality(), doctorRequest.getPhone(), doctorRequest.getStatus(), doctorRequest.getExperience());
        Doctor savedDoctorDetails = doctorRepository.save(doctor);
        logger.info("----- saveDoctor: ----- {}", savedDoctorDetails);
        return savedDoctorDetails;
    }

    public Doctor getDoctorById(int id) throws Exception {
        logger.info("----- getDoctorById service: -----");
        Optional<Doctor> doctorById = doctorRepository.findById(id);
        if (doctorById.isPresent()) {
            return doctorById.get();
        }
        throw new DoctorNotFoundException("Doctor not found with id: " + id);
    }

    public List<Doctor> getAllDoctors() {
        logger.info("----- getAllDoctors service: -----");
        return doctorRepository.findAll();
    }

    public Doctor updateDoctor(int id, DoctorRequest doctorRequest) throws Exception {
        logger.info("----- updateDoctor service: -----");
        Optional<Doctor> doctorById = doctorRepository.findById(id);
        if (doctorById.isEmpty()) {
            throw new DoctorNotFoundException("Doctor not found with id: " + id);
        }
        Doctor doctor = doctorById.get();
        doctor.setFirstName(doctorRequest.getFirstName());
        doctor.setLastName(doctorRequest.getLastName());
        doctor.setSpeciality(doctorRequest.getSpeciality());
        doctor.setEmail(doctorRequest.getEmail());
        doctor.setPhone(doctorRequest.getPhone());
        doctor.setStatus(doctorRequest.getStatus());
        doctor.setExperience(doctorRequest.getExperience());
        return doctorRepository.save(doctor);
    }

    public Doctor getDoctorByEmail(String email) throws Exception {
        logger.info("----- getDoctorByEmail service: -----");
        Optional<Doctor> doctorByEmail = doctorRepository.findByEmail(email);
        if (doctorByEmail.isEmpty()) {
            throw new DoctorNotFoundException("Doctor not found with email: " + email);
        }
        return doctorByEmail.get();

    }
}
