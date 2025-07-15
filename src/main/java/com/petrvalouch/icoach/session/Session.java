package com.petrvalouch.icoach.session;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.petrvalouch.icoach.athlete.Athlete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id",
  scope = Session.class)
@Entity
@Table(name = "sessions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    public enum SessionType {
        TRAINING,
        RACING,
        LAND,
        OTHER,
    }

    public enum Craft {
        DB20,
        DB10,
        SINGLE,
        OC6,
        OTHER,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionType sessionType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Craft craft;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToMany(mappedBy = "attendedSessions")
    //@JsonIgnore
    private Set<Athlete> presentAthletes = new HashSet<>();
    
    // public void addPresentAthlete(Athlete athlete) {
    //     presentAthletes.add(athlete);
    // }

    // public void removePresentAthlete(Athlete athlete) {
    //     presentAthletes.remove(athlete);
    // }
}

/*
 * {
    "sessionType": "TRAINING",
    "craft": "DB10",
    "location": "Bank Street",
    "date": "2025-06-17"
}
 */
