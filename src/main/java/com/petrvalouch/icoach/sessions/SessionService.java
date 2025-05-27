package com.petrvalouch.icoach.sessions;

import org.modelmapper.ModelMapper;

public class SessionService {
    
    private SessionRepository repo;
    private ModelMapper mapper;

    public SessionService(SessionRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    
    
}
