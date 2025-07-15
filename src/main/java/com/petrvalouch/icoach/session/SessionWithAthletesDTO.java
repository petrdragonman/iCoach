package com.petrvalouch.icoach.session;

import java.util.Set;

import com.petrvalouch.icoach.athlete.AthleteDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SessionWithAthletesDTO extends SessionDTO {
    private Set<AthleteDTO> presentAthletes;
    
}
