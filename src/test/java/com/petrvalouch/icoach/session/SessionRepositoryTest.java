package com.petrvalouch.icoach.session;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class SessionRepositoryTest {
    
    @Autowired
    private SessionRepository sessionRepository;
    
    private Session session1;
    private Session session2;

    @BeforeEach
    void setUp() {
        session1 = new Session();
        session1.setSessionName("Thursday training");
        session1.setDate(LocalDate.of(2025, 5, 28));
        
        session2 = new Session();
        session2.setSessionName("Saturday training");
        session2.setDate(LocalDate.of(2025, 5, 31));
        
        sessionRepository.saveAll(List.of(session1, session2));
    }

    @AfterEach
    void tearDown() {
        sessionRepository.deleteAll();
    }

    @Test
    void save_shouldPersistSession() {
        // Arrange
        Session newSession = new Session();
        newSession.setSessionName("New session");
        newSession.setDate(LocalDate.now());

        // Act
        Session savedSession = sessionRepository.save(newSession);

        // Assert
        assertNotNull(savedSession.getId());
        assertEquals("New session", savedSession.getSessionName());
        assertThat(sessionRepository.count()).isEqualTo(3);
    }

    @Test
    void findAll_shouldReturnAllSessions() {
        // Act
        List<Session> sessions = sessionRepository.findAll();

        // Assert
        assertThat(sessions)
            .hasSize(2)
            .extracting(Session::getSessionName)
            .containsExactlyInAnyOrder("Thursday training", "Saturday training");
    }

    @Test
    void findAll_shouldReturnEmptySessionList() {
        // Arrange
        sessionRepository.deleteAll();
        // Act
        List<Session> sessions = sessionRepository.findAll();

        // Assert
        assertThat(sessions)
            .hasSize(0);
    }

    // @Test
    // void findById_shouldReturnSession() {
    //     // Act
    //     Optional<Session> foundSession = sessionRepository.findById(session1.getId());

    //     // Assert
    //     assertTrue(foundSession.isPresent());
    //     assertEquals("Thursday training", foundSession.get().getSessionName());
    //     assertEquals(LocalDate.of(2025, 5, 28), foundSession.get().getDate());
    // }

    // @Test
    // void deleteById_shouldRemoveSession() {
    //     // Act
    //     sessionRepository.deleteById(session1.getId());
        
    //     // Assert
    //     assertThat(sessionRepository.count()).isEqualTo(1);
    //     assertThat(sessionRepository.findById(session1.getId())).isEmpty();
    // }

    // @Test
    // void findByDateBetween_shouldReturnSessionsInDateRange() {
    //     // Act
    //     List<Session> sessions = sessionRepository.findByDateBetween(
    //         LocalDate.of(2025, 5, 27),
    //         LocalDate.of(2025, 5, 30)
    //     );

    //     // Assert
    //     assertThat(sessions)
    //         .hasSize(1)
    //         .extracting(Session::getSessionName)
    //         .containsExactly("Thursday training");
    // }

    // @Test
    // void findBySessionNameContaining_shouldReturnMatchingSessions() {
    //     List<Session> results = sessionRepository.findBySessionNameContaining("train");
    //     assertThat(results).hasSize(2);
    // }

    // @Test
    // void countByDate_shouldReturnCorrectCount() {
    //     long count = sessionRepository.countByDate(LocalDate.of(2025, 5, 28));
    //     assertThat(count).isEqualTo(1);
    // }
}
