package com.petrvalouch.icoach.session;

import java.time.LocalDate;

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
public class CreateSessionDTO {

    @NotBlank(message = "session name is required")
    private String sessionName;

    @NotNull(message = "date is required")
    private LocalDate date;
}
