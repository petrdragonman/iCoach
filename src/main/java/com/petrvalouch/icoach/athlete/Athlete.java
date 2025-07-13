package com.petrvalouch.icoach.athlete;

import java.util.HashSet;
import java.util.Set;

import com.petrvalouch.icoach.session.Session;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
  property = "id")
@Entity
@Table(name = "athletes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Athlete { 

  public enum Gender {
    FEMALE,
    MALE
  }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String nickName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private Double weight;

    @ManyToMany
    @JoinTable(
        name = "athletes_sessions", 
        joinColumns = @JoinColumn(name = "athleteId"), 
        inverseJoinColumns = @JoinColumn(name = "sessionId")
    )
    private Set<Session> attendedSessions = new HashSet<>();

    // private void addSession(Session session) {
    //     attendedSessions.add(session);
    //     //session.getPresentAthletes().add(this);
    // }

}
