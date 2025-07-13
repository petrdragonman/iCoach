package com.petrvalouch.icoach.session;

import java.time.LocalDate;
import java.util.Set;

import com.petrvalouch.icoach.session.Session.Craft;
import com.petrvalouch.icoach.session.Session.SessionType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSessionDTO {

    @NotNull(message = "session type required")
    private SessionType sessionType;

    @NotNull(message = "craft type required")
    private Craft craft;

    @NotNull(message = "session location required")
    private String location;

    @NotNull(message = "date is required")
    private LocalDate date;

    private Set<Long> presentAthleteIds;
}
