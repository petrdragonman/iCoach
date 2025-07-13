package com.petrvalouch.icoach.athlete;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petrvalouch.icoach.common.exceptions.AthleteNotFoundException;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public ResponseEntity<Athlete> getAthleteById(@PathVariable Long id) throws AthleteNotFoundException {
        Optional<Athlete> result = this.athleteService.getById(id);
        Athlete foundAthlete = result.orElseThrow(() -> new AthleteNotFoundException(id));
        return new ResponseEntity<>(foundAthlete, HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws AthleteNotFoundException {
        boolean wasDeleted = this.athleteService.deleteById(id);
        if (!wasDeleted) {
            throw new AthleteNotFoundException(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    private ResponseEntity<Athlete> updateAthleteById(@PathVariable Long id, @Valid @RequestBody UpdateAthleteDTO data) {
        Optional<Athlete> result = this.athleteService.updateById(id, data);
        Athlete foundAthlete = result.orElseThrow(() -> new AthleteNotFoundException(id));
        return new ResponseEntity<Athlete>(foundAthlete, HttpStatus.OK);
    }
    
    

}