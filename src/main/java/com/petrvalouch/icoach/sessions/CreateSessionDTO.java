package com.petrvalouch.icoach.sessions;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateSessionDTO {

    @NotBlank(message = "session name is required")
    private String sessionName;

    @NotNull(message = "date is required")
    private LocalDate date;

    public String getSessionName() {
        return sessionName;
    }
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "CreateSessionDTO [sessionName=" + sessionName + ", date=" + date + "]";
    }
}
