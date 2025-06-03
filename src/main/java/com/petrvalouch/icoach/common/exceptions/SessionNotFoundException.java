package com.petrvalouch.icoach.common.exceptions;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(Long id) {
        super("Could not found session id: " + id);
    }
}
