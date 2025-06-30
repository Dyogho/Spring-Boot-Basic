package com.lcipriano.apiAcademica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lcipriano.apiAcademica.models.Estudiante;
 
public interface EstudianteRepository extends JpaRepository<Estudiante, Long>{ 
} 
 
 