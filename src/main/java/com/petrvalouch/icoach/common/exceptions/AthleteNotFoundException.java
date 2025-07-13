package com.petrvalouch.icoach.common.exceptions;

public class AthleteNotFoundException extends RuntimeException {
    public AthleteNotFoundException(Long id) {
        super("Could not found athlete with id: " + id);
    }
}
