package com.petrvalouch.icoach.session;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;

import com.petrvalouch.icoach.session.Session.SessionType;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    private static final LocalDate TEST_DATE = LocalDate.of(2025, 1, 1);
    private static final SessionType TEST_SESSION_TYPE = SessionType.TRAINING;
    private static final Long TEST_SESSION_ID = 1L;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SessionService sessionService;

    private final Validator validator;
    private List<Session> testSessions;

    public SessionServiceTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    void setUp() {
        testSessions = List.of(
            createTestSession(1L, SessionType.TRAINING, LocalDate.of(2005, 6, 1)),
            createTestSession(2L, SessionType.TRAINING, LocalDate.of(2005, 6, 3))
        );
    }

    @Test
    void createSession_ValidData_ReturnsSavedSession() {
        // Arrange
        CreateSessionDTO inputDto = createValidDto();
        Session mappedSession = createTestSession(null, TEST_SESSION_TYPE, TEST_DATE);
        Session savedSession = createTestSession(TEST_SESSION_ID, TEST_SESSION_TYPE, TEST_DATE);
        
        when(modelMapper.map(inputDto, Session.class)).thenReturn(mappedSession);
        when(sessionRepository.save(mappedSession)).thenReturn(savedSession);

        // Act
        Session result = sessionService.createSession(inputDto);

        // Assert
        assertThat(result)
            .isNotNull()
            .extracting(Session::getId, Session::getSessionType, Session::getDate)
            .containsExactly(TEST_SESSION_ID, TEST_SESSION_TYPE, TEST_DATE);
            
        assertTrue(validator.validate(inputDto).isEmpty());
        
        verify(modelMapper).map(inputDto, Session.class);
        verify(sessionRepository).save(mappedSession);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidSessionData")
    void createSession_InvalidData_ValidationFails(
            String testName,
            Consumer<CreateSessionDTO> modifier,
            String expectedMessage) {
        
        CreateSessionDTO invalidDto = createValidDto();
        modifier.accept(invalidDto);

        var violations = validator.validate(invalidDto);
        
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals(expectedMessage, violations.iterator().next().getMessage());
    }

    @Test
    void createSession_DatabaseError_ThrowsException() {
        CreateSessionDTO inputDto = createValidDto();
        Session mappedSession = createTestSession(null, TEST_SESSION_TYPE, LocalDate.now());
        
        when(modelMapper.map(inputDto, Session.class)).thenReturn(mappedSession);
        when(sessionRepository.save(mappedSession))
            .thenThrow(new DataIntegrityViolationException("Database error"));

        assertThrows(DataIntegrityViolationException.class, () -> 
            sessionService.createSession(inputDto)
        );
    }

    @Test
    void createSession_VerifyModelMapperMapping() {
        CreateSessionDTO inputDto = createValidDto();
        Session expectedSession = createTestSession(null, inputDto.getSessionType(), inputDto.getDate());
        
        when(modelMapper.map(inputDto, Session.class)).thenReturn(expectedSession);
        when(sessionRepository.save(expectedSession)).thenReturn(expectedSession);

        sessionService.createSession(inputDto);

        verify(modelMapper).map(inputDto, Session.class);
        verify(sessionRepository).save(expectedSession);
    }

    @Test
    void createSession_VerifyReturnedSessionProperties() {
        CreateSessionDTO inputDto = createValidDto();
        Long expectedId = 5L;
        Session mappedSession = new Session();
        Session savedSession = createTestSession(expectedId, inputDto.getSessionType(), inputDto.getDate());
        
        when(modelMapper.map(inputDto, Session.class)).thenReturn(mappedSession);
        when(sessionRepository.save(any(Session.class))).thenReturn(savedSession);

        Session result = sessionService.createSession(inputDto);

        assertThat(result)
            .extracting(Session::getId, Session::getSessionType, Session::getDate)
            .containsExactly(expectedId, inputDto.getSessionType(), inputDto.getDate());
        verify(sessionRepository, times(1)).save(mappedSession);
    }

    @Test
    void getAllSessions_ReturnsAllSessions() {
        when(sessionRepository.findAll()).thenReturn(testSessions);
        
        List<Session> result = sessionService.getAll();
        
        assertThat(result)
            .hasSize(2)
            .extracting(Session::getSessionType);
            //.contains(SessionType.TRAINING, "TRAINING");
        verify(sessionRepository, times(1)).findAll();
    }

    @Test
    void getSessionById_ReturnSession() {
        // Arrange
        Long id = 1L;
        Session expectedSession = testSessions.get(0);
        when(sessionRepository.findById(id)).thenReturn(Optional.of(expectedSession));

        // Act
        Optional<Session> result = sessionService.getById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedSession, result.get());
        verify(sessionRepository, times(1)).findById(id);
    }

    private static Stream<Arguments> provideInvalidSessionData() {
        return Stream.of(
            // Arguments.of(
            //     "Blank session name",
            //     (Consumer<CreateSessionDTO>) dto -> dto.setSessionType(" "),
            //     "session name is required"
            // ),
            Arguments.of(
                "Null date",
                (Consumer<CreateSessionDTO>) dto -> dto.setDate(null),
                "date is required"
            )
        );
    }

    private CreateSessionDTO createValidDto() {
        CreateSessionDTO dto = new CreateSessionDTO();
        dto.setSessionType(TEST_SESSION_TYPE);
        dto.setDate(TEST_DATE);
        return dto;
    }

    private Session createTestSession(Long id, SessionType testSessionType, LocalDate date) {
        Session session = new Session();
        session.setId(id);
        session.setSessionType(testSessionType);
        session.setDate(date);
        return session;
    }
}