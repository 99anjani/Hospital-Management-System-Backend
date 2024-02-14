package com.myproject.hospitalmanagementsystembackend.exception;

public class patientAlreadyExistsException extends RuntimeException {
    public patientAlreadyExistsException(String message) {
        super(message);
    }
}
