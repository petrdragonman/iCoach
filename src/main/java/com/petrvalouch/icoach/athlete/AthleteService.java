package com.petrvalouch.icoach.athlete;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AthleteService {
    private AthleteRepository repo;
    private ModelMapper mapper;

    public AthleteService(AthleteRepository repo, ModelMapper mapper) {
        this.mapper = mapper;
        this.repo = repo;
    }

    public List<Athlete> getAll() {
        return this.repo.findAll();
    }

    public Athlete createAthlete(CreateAthleteDTO data) {
        Athlete newAthlete = mapper.map(data, Athlete.class);
        return this.repo.save(newAthlete);
    }
}
