package com.petrvalouch.icoach.session;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    
    private SessionRepository repo;
    private ModelMapper mapper;

    public SessionService(SessionRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Session createSession(CreateSessionDTO data) {
        Session newSession = mapper.map(data, Session.class);
        return this.repo.save(newSession);
    }

    public List<Session> getAll() {
        return this.repo.findAll();
    }

    public Optional<Session> getById(Long id) {
        return this.repo.findById(id);
    }

    public boolean deleteById(Long id) {
        Optional<Session> result = this.getById(id);
        if (result.isEmpty()) {
            return false;
        }
        this.repo.delete(result.get());
        return true;
    }

    public Optional<Session> updateById(Long id, UpdateSessionDTO data) {
        Optional<Session> result = this.getById(id);
        if(result.isEmpty()) {
            return result;
        }
        Session foundSession = result.get();
        mapper.map(data, foundSession);
        this.repo.save(foundSession);
        return Optional.of(foundSession);
    }
    
}
