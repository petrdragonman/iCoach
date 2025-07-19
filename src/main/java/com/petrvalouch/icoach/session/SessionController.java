package com.petrvalouch.icoach.session;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;

import com.petrvalouch.icoach.common.exceptions.SessionNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    private SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<Session> createSession(@RequestBody @Valid CreateSessionDTO data) {
        Session newSession = this.sessionService.createSession(data);
        return new ResponseEntity<Session>(newSession, HttpStatus.CREATED);
    }

    // @GetMapping
    // public ResponseEntity<List<Session>> getAllSessions() {
    //     List<Session> sessions = this.sessionService.getAll();
    //     return new ResponseEntity<>(sessions, HttpStatus.OK);
    // }

    @GetMapping
    public ResponseEntity<List<SessionWithAthletesDTO>> getAllSessions() {
        List<SessionWithAthletesDTO> sessionsWithAthletes = this.sessionService.getAll();
        return new ResponseEntity<>(sessionsWithAthletes, HttpStatus.OK);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable Long id) throws SessionNotFoundException {
        Optional<Session> result = this.sessionService.getById(id);
        Session foundSession = result.orElseThrow(() -> new SessionNotFoundException(id));
        return new ResponseEntity<>(foundSession, HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws SessionNotFoundException {
        boolean wasDeleted = this.sessionService.deleteById(id);
        if (!wasDeleted) {
            throw new SessionNotFoundException(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Session> updateById(@PathVariable Long id, @Valid @RequestBody UpdateSessionDTO data) {
        Optional<Session> result = this.sessionService.updateById(id, data);
        Session foundSession =result.orElseThrow(() -> new SessionNotFoundException(id));
        return new ResponseEntity<Session>(foundSession, HttpStatus.OK);
    }

    // http://localhost:8080/sessions/1?athleteId=3
    @PostMapping("/add/{id}")
    public ResponseEntity<SessionWithAthletesDTO> addAthleteIntoSession(@PathVariable Long id, @RequestParam Long athleteId) {
        SessionWithAthletesDTO sessionWithAthletes = this.sessionService.addAthleteToSession(athleteId, id);
        return new ResponseEntity<SessionWithAthletesDTO>(sessionWithAthletes, HttpStatus.OK);
    }
    
    @PostMapping("/remove/{id}")
    public ResponseEntity<SessionWithAthletesDTO> removeAthleteFromSession(@PathVariable Long id, @RequestParam Long athleteId) {
        SessionWithAthletesDTO sessionWithAthletes = this.sessionService.removeAthleteFromSession(athleteId, id);
        return new ResponseEntity<SessionWithAthletesDTO>(sessionWithAthletes, HttpStatus.OK);
    }
    

    // @PostMapping("/add-athlete-to-session")
    // public ResponseEntity<AthleteDTO> addAthleteToSession(
    // @RequestParam Long athleteId, 
    // @RequestParam Long sessionId
    // ) {
    // AthleteDTO athleteDTO = this.sessionService.addAthleteToSession(athleteId, sessionId);
    // return ResponseEntity.ok(athleteDTO);
    // }

    @PostMapping("/add-athlete-to-session")
    public ResponseEntity<SessionWithAthletesDTO> addAthleteToSession(
    @RequestParam Long athleteId, 
    @RequestParam Long sessionId
    ) {
    SessionWithAthletesDTO sessionWithAthletes = this.sessionService.addAthleteToSession(athleteId, sessionId);
        return ResponseEntity.ok(sessionWithAthletes);
    }
    

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ServerErrorException("NPE", ex)); // 500
    }
    
}

/**
 * @PostMapping("/add-athlete-to-session")
    public ResponseEntity<Athlete> addAthleteToSessio(@RequestParam Long athleteId, @RequestParam Long sessionId) {
        Athlete addedAthlete = this.sessionService.addAthleteToSession(athleteId, sessionId);
        return new ResponseEntity<Athlete>(addedAthlete, HttpStatus.OK); 
    }
 */
