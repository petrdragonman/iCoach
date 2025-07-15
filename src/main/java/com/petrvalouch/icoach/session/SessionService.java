package com.petrvalouch.icoach.session;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.petrvalouch.icoach.athlete.Athlete;
import com.petrvalouch.icoach.athlete.AthleteRepository;

@Service
public class SessionService {
    
    private SessionRepository sessionRepo;
    private AthleteRepository athleteRepo;
    private ModelMapper mapper;

    public SessionService(SessionRepository sessionRepo, AthleteRepository athleteRepo, ModelMapper mapper) {
        this.sessionRepo = sessionRepo;
        this.athleteRepo = athleteRepo;
        this.mapper = mapper;
    }

    public Session createSession(CreateSessionDTO data) {
        Session newSession = mapper.map(data, Session.class);
        return this.sessionRepo.save(newSession);
    }

    public List<SessionWithAthletesDTO> getAll() {
        return sessionRepo.findAll().stream()
            .map(session -> mapper.map(session, SessionWithAthletesDTO.class))
            .collect(Collectors.toList());
    }

    public Optional<Session> getById(Long id) {
        return this.sessionRepo.findById(id);
    }

    public boolean deleteById(Long id) {
        Optional<Session> result = this.getById(id);
        if (result.isEmpty()) {
            return false;
        }
        this.sessionRepo.delete(result.get());
        return true;
    }

    public Optional<Session> updateById(Long id, UpdateSessionDTO data) {
        Optional<Session> result = this.getById(id);
        if(result.isEmpty()) {
            return result;
        }
        Session foundSession = result.get();
        mapper.map(data, foundSession);
        this.sessionRepo.save(foundSession);
        return Optional.of(foundSession);
    }

    public SessionWithAthletesDTO addAthleteToSession(Long athleteId, Long sessionId) {
        Athlete athlete = athleteRepo.findById(athleteId)
            .orElseThrow(() -> new IllegalArgumentException("Athlete not found"));
        Session session = sessionRepo.findById(sessionId)
            .orElseThrow(() -> new IllegalArgumentException("Session not found"));

        // Add the session to the athlete
        athlete.getAttendedSessions().add(session);
        athleteRepo.save(athlete);

        // Map to DTO before returning
        //return mapper.map(athlete, AthleteDTO.class);
        return mapper.map(session, SessionWithAthletesDTO.class);
    }
    
}

/**
 * public Athlete addAthleteToSession(Long athleteId, Long sessionId) {
        //Optional<Athlete> athlete = this.athleteRepo.findById(athleteId);
        //Optional<Session> session = this.sessionRepo.findById(sessionId);
        Optional<Athlete> athlete = this.athleteRepo.findById(athleteId);
        Optional<Session> session = this.sessionRepo.findById(sessionId);
        if (session.isPresent() && athlete.isPresent()) {
            athlete.get().getAttendedSessions().add(session.get());
            ////////////////
            mapper.map(athlete, CreateAthleteDTO.class);
            this.athleteRepo.save(athlete.get());
            ///////////////
            //session.get().getPresentAthletes().add(athlete.get());
            //sessionRepo.save(session.get());
            ////////////////////
            return athlete.get();
        } else {
            throw new IllegalArgumentException("Athlete or Session not found");
        }
    }
 */
