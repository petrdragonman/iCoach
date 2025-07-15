package com.petrvalouch.icoach.config;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.petrvalouch.icoach.athlete.Athlete;
import com.petrvalouch.icoach.athlete.AthleteDTO;
import com.petrvalouch.icoach.athlete.AthleteWithSessionsDTO;
import com.petrvalouch.icoach.session.Session;
import com.petrvalouch.icoach.session.SessionDTO;
import com.petrvalouch.icoach.session.SessionWithAthletesDTO;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.typeMap(String.class, String.class).setConverter(new StringTrimConverter());

        configureAthleteMappings(mapper);
        configureSessionMappings(mapper);

        return mapper;
    }

    // Athlete → AthleteDTO: Converts Set<Session> to Set<Long> (session IDs).
    @SuppressWarnings("unchecked")
    private void configureAthleteMappings(ModelMapper mapper) {
        // Map Athlete → AthleteDTO
        mapper.createTypeMap(Athlete.class, AthleteDTO.class)
            .addMappings(m -> {
                // Convert Set<Session> → Set<Long> (session IDs)
                m.using(ctx -> ((Set<Session>) ctx.getSource()).stream()
                        .map(Session::getId)
                        .collect(Collectors.toSet()))
                  .map(Athlete::getAttendedSessions, AthleteDTO::setAttendedSessionIds);
            });

        // Map Athlete -> AthleteWithSessionsDTO
        mapper.createTypeMap(Athlete.class, AthleteWithSessionsDTO.class)
        .includeBase(Athlete.class, AthleteDTO.class)
        .addMappings(m -> {
            m.map(Athlete::getAttendedSessions, AthleteWithSessionsDTO::setAttendedSessions);
        });
    }

    // Session → SessionDTO: Converts Set<Athlete> to Set<Long> (athlete IDs).
    @SuppressWarnings("unchecked")
    private void configureSessionMappings(ModelMapper mapper) {
        // Map Session → SessionDTO
        mapper.createTypeMap(Session.class, SessionDTO.class)
            .addMappings(m -> {
                // Convert Set<Athlete> → Set<Long> (athlete IDs)
                m.using(ctx -> ((Set<Athlete>) ctx.getSource()).stream()
                        .map(Athlete::getId)
                        .collect(Collectors.toSet()))
                  .map(Session::getPresentAthletes, SessionDTO::setPresentAthleteIds);
            });

        // Map Session -> SessionWithAthletesDTO
        mapper.createTypeMap(Session.class, SessionWithAthletesDTO.class)
            .includeBase(Session.class, SessionDTO.class)
            .addMappings(m -> {
                m.map(Session::getPresentAthletes, SessionWithAthletesDTO::setPresentAthletes);
            });
    }

    private class StringTrimConverter implements Converter<String, String> {
        @Override
        public String convert(MappingContext<String, String> context) {
            if (context.getSource() == null) {
                return null;
            }
            return context.getSource().trim();
        }
    }
}
