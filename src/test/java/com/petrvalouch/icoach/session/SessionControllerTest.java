package com.petrvalouch.icoach.session;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class SessionControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    // @Test
    // public void SessionController_createSession_validSession() throws JsonProcessingException, Exception {
    //     Session session1 = new Session();
    //     session1.setSessionName("Thursday training");
    //     session1.setDate(LocalDate.of(2025, 5, 28));

    //     mockMvc.perform(MockMvcRequestBuilders
    //         .post("/sessions")
    //         .contentType(MediaType.APPLICATION_JSON)
    //         .content(objectMapper.writeValueAsString(session1))
    //     )
    //         .andExpect(MockMvcResultMatchers.status().isCreated()) // 201 Created
    //         .andExpect(MockMvcResultMatchers.jsonPath("$.sessionName").value("Thursday training"))
    //         .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2025-05-28"))
    //         .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    // }

    // @Test
    // public void SessionController_createSession_invalidSession_noSessionName() throws JsonProcessingException, Exception {
    //     Session session1 = new Session();
    //     session1.setDate(LocalDate.of(2025, 5, 28));

    //     mockMvc.perform(MockMvcRequestBuilders
    //         .post("/sessions")
    //         .contentType(MediaType.APPLICATION_JSON)
    //         .content(objectMapper.writeValueAsString(session1))
    //     )
    //         .andExpect(MockMvcResultMatchers.status().isBadRequest()); // 400 Bad Request
    // }

    // @Test
    // public void SessionController_createSession_invalidSession_noDate() throws JsonProcessingException, Exception {
    //     Session session1 = new Session();
    //     session1.setSessionName("Thursday training");

    //     mockMvc.perform(MockMvcRequestBuilders
    //         .post("/sessions")
    //         .contentType(MediaType.APPLICATION_JSON)
    //         .content(objectMapper.writeValueAsString(session1))
    //     )
    //         .andExpect(MockMvcResultMatchers.status().isBadRequest()); // 400 Bad Request
    // }

    // @Test
    // public void SessionController_createSession_invalidSession_noArgs() throws JsonProcessingException, Exception {
    //     Session session1 = new Session();

    //     mockMvc.perform(MockMvcRequestBuilders
    //         .post("/sessions")
    //         .contentType(MediaType.APPLICATION_JSON)
    //         .content(objectMapper.writeValueAsString(session1))
    //     )
    //         .andExpect(MockMvcResultMatchers.status().isBadRequest()); // 400 Bad Request
    // }

    // @Test
    // public void SessionController_getAllSessions_returnsAllSessions() {
    //     Session session1 = new Session();
    //     session1.setSessionName("Thursday training");
    //     session1.setDate(LocalDate.of(2025, 5, 28));
    //     Session session2 = new Session();
    //     session2.setSessionName("Monday training");
    //     session2.setDate(LocalDate.of(2025, 6, 2));
    // }

    
}
