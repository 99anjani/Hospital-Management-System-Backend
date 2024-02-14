package com.myproject.hospitalmanagementsystembackend.service;

import com.myproject.hospitalmanagementsystembackend.exception.PatientNotFoundException;
import com.myproject.hospitalmanagementsystembackend.exception.patientAlreadyExistsException;
import com.myproject.hospitalmanagementsystembackend.model.Patient;
import com.myproject.hospitalmanagementsystembackend.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PatientIMPL implements PatientService {
    private final PatientRepository patientRepository;
    @Override
    public List<Patient> getPatient() {
        return patientRepository.findAll();
    }
    @Override
    public Patient addPatient(Patient patient) {
        if(patientAlreadyExists(patient.getEmail())){
            throw new patientAlreadyExistsException(patient.getEmail()+"Patient Already Exist ! ");
        }
        return patientRepository.save(patient);
    }




    @Override
    public Patient updatePatient(Patient patient, Long id) {
        return patientRepository.findById(id).map(pt->{
            pt.setName(patient.getName());
            pt.setEmail(patient.getEmail());
            pt.setMobile(patient.getMobile());
            pt.setGender(patient.getGender());
            pt.setPassword(patient.getPassword());
            return patientRepository.save(pt);
        }).orElseThrow(()->new PatientNotFoundException("Sorry , this patient could not be found"));
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(()->new PatientNotFoundException("Sorry, Patient Not found with the Id: "+id));
    }

    @Override
    public void deletePatient(Long id) {
        if(!patientRepository.existsById(id)){
            throw new PatientNotFoundException("Sorry, patient Not found");
        }
        patientRepository.deleteById(id);

    }

    private boolean patientAlreadyExists(String email) {

        return patientRepository.findByEmail(email).isPresent();
    }
}
