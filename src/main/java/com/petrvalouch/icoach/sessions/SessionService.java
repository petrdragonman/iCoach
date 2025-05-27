package com.petrvalouch.icoach.sessions;

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
    
}
