package com.petrvalouch.icoach.session;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SessionService sessionService;

    private final Validator validator;

    public SessionServiceTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void createSession_ValidData_ReturnsSavedSession() {
        // Arrange
        LocalDate testDate = LocalDate.of(2023, 1, 1);
        CreateSessionDTO inputDto = createValidDto();
        
        Session mappedSession = new Session();
        mappedSession.setSessionName("Test Session");
        mappedSession.setDate(testDate);
        
        Session savedSession = new Session();
        savedSession.setId(1L);
        savedSession.setSessionName("Test Session");
        savedSession.setDate(testDate);
        
        when(modelMapper.map(inputDto, Session.class)).thenReturn(mappedSession);
        when(sessionRepository.save(mappedSession)).thenReturn(savedSession);

        // Act
        Session result = sessionService.createSession(inputDto);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Session", result.getSessionName());
        assertEquals(testDate, result.getDate());
        assertTrue(validator.validate(inputDto).isEmpty());
        
        verify(modelMapper, times(1)).map(inputDto, Session.class);
        verify(sessionRepository, times(1)).save(mappedSession);
    }

    // @Test
    // void createSession_NullInput_ThrowsException() {
    //     // Act & Assert
    //     assertThrows(IllegalArgumentException.class, () -> {
    //         sessionService.createSession(null);
    //     });
        
    //     verifyNoInteractions(modelMapper);
    //     verifyNoInteractions(sessionRepository);
    // }

    @Test
    void createSession_BlankSessionName_ValidationFails() {
        // Arrange
        CreateSessionDTO invalidDto = createValidDto();
        invalidDto.setSessionName(" ");
        
        // Act & Assert
        var violations = validator.validate(invalidDto);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("session name is required", violations.iterator().next().getMessage());
    }

    @Test
    void createSession_NullDate_ValidationFails() {
        // Arrange
        CreateSessionDTO invalidDto = createValidDto();
        invalidDto.setDate(null);
        
        // Act & Assert
        var violations = validator.validate(invalidDto);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("date is required", violations.iterator().next().getMessage());
    }

    @Test
    void createSession_DatabaseError_ThrowsException() {
        // Arrange
        CreateSessionDTO inputDto = createValidDto();
        Session mappedSession = new Session();
        mappedSession.setSessionName("Test Session");
        mappedSession.setDate(LocalDate.now());
        
        when(modelMapper.map(inputDto, Session.class)).thenReturn(mappedSession);
        when(sessionRepository.save(mappedSession))
            .thenThrow(new DataIntegrityViolationException("Database error"));

        // Act & Assert
        assertThrows(DataIntegrityViolationException.class, () -> {
            sessionService.createSession(inputDto);
        });
    }

    @Test
    void createSession_VerifyModelMapperMapping() {
        // Arrange
        CreateSessionDTO inputDto = createValidDto();
        Session expectedSession = new Session();
        expectedSession.setSessionName(inputDto.getSessionName());
        expectedSession.setDate(inputDto.getDate());
        
        when(modelMapper.map(inputDto, Session.class)).thenReturn(expectedSession);
        when(sessionRepository.save(expectedSession)).thenReturn(expectedSession);

        // Act
        sessionService.createSession(inputDto);

        // Assert
        verify(modelMapper).map(inputDto, Session.class);
        verify(sessionRepository).save(expectedSession);
    }

    @Test
    void createSession_VerifyReturnedSessionProperties() {
        // Arrange
        CreateSessionDTO inputDto = createValidDto();
        Session savedSession = new Session();
        savedSession.setId(5L);
        savedSession.setSessionName(inputDto.getSessionName());
        savedSession.setDate(inputDto.getDate());
        
        when(modelMapper.map(inputDto, Session.class)).thenReturn(new Session());
        when(sessionRepository.save(any(Session.class))).thenReturn(savedSession);

        // Act
        Session result = sessionService.createSession(inputDto);

        // Assert
        assertEquals(5L, result.getId());
        assertEquals(inputDto.getSessionName(), result.getSessionName());
        assertEquals(inputDto.getDate(), result.getDate());
    }

    private CreateSessionDTO createValidDto() {
        CreateSessionDTO dto = new CreateSessionDTO();
        dto.setSessionName("Test Session");
        dto.setDate(LocalDate.of(2023, 1, 1));
        return dto;
    }
}

/**
 * package com.petrvalouch.icoach.session;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;

public class SessionServiceTest {
    
    @Mock
    private SessionRepository repo;
    private ModelMapper mapper;
    private SessionService sessionService;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        mapper = new ModelMapper();
        sessionService = new SessionService(repo, mapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void createSession_ValidInput_ReturnsSessionWithMatchingArguments() {
        // Arrange
        Session newSession = new Session();
        newSession.setSessionName("Saturday training");
        newSession.setDate(LocalDate.of(2025, 5, 31));
        CreateSessionDTO dto = mapper.map(newSession, CreateSessionDTO.class);

        when(repo.save(any(Session.class))).thenReturn(newSession);

        // Act
        Session createdSession = sessionService.createSession(dto);

        // Assert
        Assertions.assertThat(createdSession.getSessionName())
            .isEqualTo(newSession.getSessionName());
        Assertions.assertThat(newSession.getDate())
            .isEqualTo(newSession.getDate());
        
        verify(repo, times(1)).save(any(Session.class));
    }

    @Test
    void createSession_InvalidInput_ThrowsBadRequest() {
        // Arrange
        Session invalidSession = new Session();
        invalidSession.setSessionName(null);
        invalidSession.setDate(LocalDate.of(2025, 5, 31));
        CreateSessionDTO invalidDto = mapper.map(invalidSession, CreateSessionDTO.class);

        when(repo.save(any(Session.class))).thenReturn(invalidSession);

        // Act
        Session createdSession = sessionService.createSession(invalidDto);

        // Assert
        Assertions.assertThat(() -> sessionService.createdSession(invalidDto)).equals(null);
        // Assertions.assertThatThrownBy(() -> sessionService.createSession(invalidDto))
        //     .isInstanceOf(ResponseStatusException.class)
        //     .hasMessageContaining("400 BAD_REQUEST");
        
        verify(repo, times(0)).save(any(Session.class));
    }
}
 */
