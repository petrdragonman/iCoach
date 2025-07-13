package com.petrvalouch.icoach.athlete;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/athletes")
public class AthleteController {
    private AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }


    @GetMapping
    public ResponseEntity<List<Athlete>> getAllSessions() {
        List<Athlete> athletes = this.athleteService.getAll();
        return new ResponseEntity<>(athletes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Athlete> createAthlete(@RequestBody @Valid CreateAthleteDTO data) {
        Athlete newAthlete = this.athleteService.createAthlete(data);
        return new ResponseEntity<>(newAthlete, HttpStatus.CREATED);
    }
    

}