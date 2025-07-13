package com.petrvalouch.icoach.session;

import java.time.LocalDate;
import java.util.Set;

import com.petrvalouch.icoach.session.Session.Craft;
import com.petrvalouch.icoach.session.Session.SessionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateSessionDTO {
    private SessionType sessionType;
    private Craft craft;
    private String location;
    private LocalDate date;

    private Set<Long> presentAthleteIds;
}
