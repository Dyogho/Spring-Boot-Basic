package com.lcipriano.apiAcademica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lcipriano.apiAcademica.models.Docente;
import java.util.List;

public interface DocenteRepository extends JpaRepository<Docente, Long>{   

       List<Docente> findByCiuDocenteIgnoreCase(String ciudad);
       List<Docente> findByTiempoServicioGreaterThanEqual(int service);
}