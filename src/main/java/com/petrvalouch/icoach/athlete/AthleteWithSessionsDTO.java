package com.petrvalouch.icoach.athlete;

import java.util.Set;

import com.petrvalouch.icoach.session.SessionDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AthleteWithSessionsDTO extends AthleteDTO {
    private Set<SessionDTO> attendedSessions;
}
