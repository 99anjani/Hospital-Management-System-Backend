package com.myproject.hospitalmanagementsystembackend.service;

import com.myproject.hospitalmanagementsystembackend.model.Patient;

import java.util.List;

public interface PatientService {

    Patient addPatient(Patient patient);
    List<Patient> getPatient();
    Patient updatePatient(Patient patient , Long id);
    Patient getPatientById(Long id);
    void deletePatient(Long id);
}
