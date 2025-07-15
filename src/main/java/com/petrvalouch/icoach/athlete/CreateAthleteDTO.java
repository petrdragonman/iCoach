package com.petrvalouch.icoach.athlete;

import java.util.Set;

import com.petrvalouch.icoach.athlete.Athlete.Gender;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAthleteDTO {
    @NotBlank(message = "fist name is required")
    private String firstName;

    @NotBlank(message = "last name is required")
    private String lastName;

    private String nickName;

    @NotNull(message = "gender required.")
    private Gender gender;

    @NonNull()
    private Double weight;

    private Set<Long> attendedSessionIds;
}
