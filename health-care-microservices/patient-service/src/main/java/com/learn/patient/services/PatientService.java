package com.learn.patient.services;

import com.learn.patient.entity.Patient;
import com.learn.patient.exceptions.PatientNotFoundException;
import com.learn.patient.payload.request.PatientRequest;
import com.learn.patient.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;

    public Patient savePatient(PatientRequest patientRequest) {
        logger.info("----- savePatient service: -----");
        Patient patient = new Patient(patientRequest.getFirstName(), patientRequest.getLastName(), patientRequest.getEmail(), patientRequest.getAge(), patientRequest.getPhone());
        Patient savedPatientDetails = patientRepository.save(patient);
        logger.info("----- savedPatientDetails: ----- {}", savedPatientDetails);
        return savedPatientDetails;
    }

    public Patient getPatientById(int id) throws Exception {
        logger.info("----- getPatientById service: -----");
        Optional<Patient> patientById = patientRepository.findById(id);
        if (patientById.isPresent()) {
            return patientById.get();
        }
        throw new PatientNotFoundException("Patient not found with id: " + id);
    }

    public List<Patient> getAllPatients() {
        logger.info("----- getAllPatients service: -----");
        return patientRepository.findAll();
    }

    public Patient updatePatient(int id, PatientRequest patientRequest) throws Exception {
        logger.info("----- updatePatient service: -----");
        Optional<Patient> patientById = patientRepository.findById(id);
        if (patientById.isEmpty()) {
            throw new PatientNotFoundException("Patient not found with id: " + id);
        }
        Patient patient = patientById.get();
        patient.setFirstName(patientRequest.getFirstName());
        patient.setLastName(patientRequest.getLastName());
        patient.setAge(patientRequest.getAge());
        patient.setEmail(patientRequest.getEmail());
        patient.setPhone(patientRequest.getPhone());
        return patientRepository.save(patient);
    }

    public Patient getPatientByEmail(String email) throws Exception {
        logger.info("----- getPatientByEmail service: -----");
        Optional<Patient> patientByEmail = patientRepository.findByEmail(email);
        if (patientByEmail.isEmpty()) {
            throw new PatientNotFoundException("Patient not found with email: " + email);
        }
        return patientByEmail.get();

    }
}
