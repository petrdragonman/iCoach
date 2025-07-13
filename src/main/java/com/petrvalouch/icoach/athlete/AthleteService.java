package com.petrvalouch.icoach.athlete;

import java.util.List;
import java.util.Optional;

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

    public Optional<Athlete> getById(Long id) {
        return this.repo.findById(id);
    }

    public boolean deleteById(Long id) {
        Optional<Athlete> result = this.getById(id);
        if (result.isEmpty()) {
            return false;
        }
        this.repo.delete(result.get());
        return true;
    }

    public Optional<Athlete> updateById(Long id, UpdateAthleteDTO data) {
        Optional<Athlete> result = this.getById(id);
        if (result.isEmpty()) {
            return result;
        }
        Athlete foundAthlete = result.get();
        mapper.map(data, foundAthlete);
        this.repo.save(foundAthlete);
        return Optional.of(foundAthlete);
    }
}
