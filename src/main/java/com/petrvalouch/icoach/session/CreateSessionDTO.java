package com.petrvalouch.icoach.session;

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

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CreateSessionDTO [sessionName=" + sessionName + ", date=" + date + "]";
    }
}
