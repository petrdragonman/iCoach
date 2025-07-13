package com.petrvalouch.icoach.athlete;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository<Athlete, Long>{
    
}
