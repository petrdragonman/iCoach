package com.petrvalouch.icoach.session;

import java.time.LocalDate;
import java.util.Set;

import com.petrvalouch.icoach.session.Session.Craft;
import com.petrvalouch.icoach.session.Session.SessionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionDTO {
    private Long id;
    private SessionType sessionType;
    private Craft craft;
    private String location;
    private LocalDate date;
    private Set<Long> presentAthleteIds;
}
