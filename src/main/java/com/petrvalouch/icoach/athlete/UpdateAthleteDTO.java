package com.petrvalouch.icoach.athlete;

import java.util.Set;

import com.petrvalouch.icoach.athlete.Athlete.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAthleteDTO {
    private String firstName;
    private String lastName;
    private String nickName;
    private Gender gender;
    private Double weight;

    private Set<Long> sessionsAttendedIds;
}
