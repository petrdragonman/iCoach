package com.petrvalouch.icoach.session;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SessionRepositoryTest {
    
    @Autowired
    private SessionRepository repo;
    Session session1;

    @BeforeEach
    void setUp() {
        Session session1 = new Session();
        session1.setSessionName("Thursday training session");
        //session.setDate(LocalDate.of(2025, 5, 28));
        session1.setDate(LocalDate.now());
        repo.save(session1);
    }

    @AfterEach
    void tearDown() {
        session1 = null;
        repo.deleteAll();
    }

    // Test case SUCCESS
    @Test
    public void SessionRepository_save_returnsSavedSession(){

        // arrange
        //Session session1 = new Session();
        //session1.setSessionName("Thursday training session");
        //session1.setDate(LocalDate.of(2025, 5, 28));
        //session1.setDate(LocalDate.now());

        // act
        //Session savedSession = repo.save(session1);

        // assert
        //Assertions.assertThat(savedSession).isNotNull();
        //Assertions.assertThat(savedSession.getId()).isEqualTo(1);
        Optional<Session> foundSession = repo.findById(1L);
        Assertions.assertThat(foundSession.get().getSessionName()).isEqualTo("Thursday training session");
    }

    // Test case FAILURE

    
}
