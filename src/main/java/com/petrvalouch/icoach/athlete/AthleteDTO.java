package com.petrvalouch.icoach.athlete;

import java.util.Set;

import com.petrvalouch.icoach.athlete.Athlete.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AthleteDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String nickName;
    private Gender gender;
    private Double weight;
    private Set<Long> attendedSessionIds;
}
