package com.myproject.hospitalmanagementsystembackend.controller;

import com.myproject.hospitalmanagementsystembackend.exception.PatientNotFoundException;
import com.myproject.hospitalmanagementsystembackend.exception.patientAlreadyExistsException;
import com.myproject.hospitalmanagementsystembackend.model.Patient;
import com.myproject.hospitalmanagementsystembackend.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    @GetMapping("/getPatients")
    public ResponseEntity<List<Patient>> getPatient(){
        return new ResponseEntity<>(patientService.getPatient(), HttpStatus.OK);
    }


    @PostMapping

    public ResponseEntity<?> addPatient(@RequestBody Patient patient) {
        try {
            return new ResponseEntity<>(patientService.addPatient(patient), HttpStatus.CREATED);
        } catch (patientAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")

    public ResponseEntity<?> updatePatient(@RequestBody Patient patient, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(patientService.updatePatient(patient, id), HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
//    public void deletePatient(@PathVariable Long id){
//        patientService.deletePatient(id);
//
//    }

    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        try {
            patientService.deletePatient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/patient/{id}")

    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        try {
            Patient patient = patientService.getPatientById(id);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
