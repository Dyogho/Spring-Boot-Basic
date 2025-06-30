package com.lcipriano.apiAcademica.controllers;

import com.lcipriano.apiAcademica.models.Docente;
import com.lcipriano.apiAcademica.repositories.DocenteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate; // Utilizado
import java.time.Period;
import java.util.List;

@RestController
@RequestMapping("/api/docentes")
public class DocenteController {
    
    @Autowired
    private DocenteRepository repo;

    @PostMapping
    public Docente agregar(@RequestBody Docente docente) {
        return repo.save(docente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Docente> modificar(@PathVariable Long id, @RequestBody Docente datos) {
        return repo.findById(id)
                .map(est -> {
                    est.setNomDocente(datos.getNomDocente());
                    est.setDirDocente(datos.getDirDocente());
                    est.setCiuDocente(datos.getCiuDocente());
                    est.setEmailDocente(datos.getEmailDocente());
                    est.setFecNacimiento(datos.getFecNacimiento());
                    est.setTiempoServicio(datos.getTiempoServicio());
                    return ResponseEntity.ok(repo.save(est));
                })
                .orElse(ResponseEntity.notFound().build());
            }
                
            @DeleteMapping("/{id}")
            public ResponseEntity<Void> eliminar(@PathVariable Long id) {
                if (repo.existsById(id)) {
                    repo.deleteById(id);
                    return ResponseEntity.noContent().build();
                }
                return ResponseEntity.notFound().build();
            }

            @GetMapping
            public List<Docente> listarTodos() {
                return repo.findAll();
            }

            @GetMapping("/{id}")
            public ResponseEntity<Docente> buscarPorId(@PathVariable Long id) {
                return repo.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
            }

            // @GetMapping("/{id}")
            // public ResponseEntity<Docente> buscarPorId(@PathVariable Long id) {
            //     Docente d = repo.findById(id)
            //         .orElseThrow(() -> new RecursoNoEncontradoException("Docente con ID " + id + " no encontrado"));
            //     return ResponseEntity.ok(d); // Se agrega la expecion
            // }

            @GetMapping("/ciudad/{ciudad}")    //Listar todos los docentes que residen en una ciudad específica 
            public List<Docente> buscarporCiudad(@PathVariable String ciudad) {
                return repo.findByCiuDocenteIgnoreCase(ciudad);
            }

            @GetMapping("/experiencia/{service}") //Listar los docentes con al menos cierta cantidad de años de servicio
            public List<Docente> buscarporExperiencia(@PathVariable int service) {
                return repo.findByTiempoServicioGreaterThanEqual(service);
            }

            @GetMapping("/edad-promedio") //Calcula y devuelve la edad promedio de todos los docentes registrados
            public double calcularPromedio() {
                List<Docente> docentes = repo.findAll();
                return docentes.stream()        // Stream convierte la lista a un flujo de datos
                    .mapToInt(doc ->Period.between(doc.getFecNacimiento(), LocalDate.now()).getYears()) //maptoint transforma a int
                    .average()                  // Period.between calcula la edad y average el promedio
                    .orElse(0);             // si no existe docentes devuelve 0
            }

            @GetMapping("/pages")
            public ResponseEntity<Page<Docente>> listardocentesPaginados(
                    @RequestParam(defaultValue = "0") int page,
                    @RequestParam(defaultValue = "10") int size
            ) {
                Pageable pageable = PageRequest.of(page, size);
                Page<Docente> docentes = repo.findAll(pageable);
                return ResponseEntity.ok(docentes);
            }

}