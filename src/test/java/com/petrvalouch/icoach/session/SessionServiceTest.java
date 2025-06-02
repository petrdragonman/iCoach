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
import static org.mockito.ArgumentMatchers.any;
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

    @Test
    void getAllSessions_ReturnsAllSessions() {
        
    }




    private CreateSessionDTO createValidDto() {
        CreateSessionDTO dto = new CreateSessionDTO();
        dto.setSessionName("Test Session");
        dto.setDate(LocalDate.of(2023, 1, 1));
        return dto;
    }

}