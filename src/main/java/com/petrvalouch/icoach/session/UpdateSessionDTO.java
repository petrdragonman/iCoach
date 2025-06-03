package com.petrvalouch.icoach.session;

import java.time.LocalDate;

public class UpdateSessionDTO {
    private String sessionName;
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
        return "UpdateSessionDTO [sessionName=" + sessionName + ", date=" + date + "]";
    }
}
