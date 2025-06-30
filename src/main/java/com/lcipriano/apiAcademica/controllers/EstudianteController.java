package com.lcipriano.apiAcademica.controllers;

import com.lcipriano.apiAcademica.models.Estudiante; 
import com.lcipriano.apiAcademica.repositories.EstudianteRepository; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*; 
 
import java.util.List; 
 
@RestController 
@RequestMapping("/api/estudiantes") 
public class EstudianteController { 
 
    @Autowired 
    private EstudianteRepository repo; 
 
    @PostMapping 
    public Estudiante agregar(@RequestBody Estudiante estudiante) { 
        return repo.save(estudiante); 
    } 
 
    @PutMapping("/{id}") 
    public ResponseEntity<Estudiante> modificar(@PathVariable Long id, @RequestBody 
Estudiante datos) { 
        return repo.findById(id) 
                .map(est -> { 
                    est.setNomEstudiante(datos.getNomEstudiante()); 
                    est.setDirEstudiante(datos.getDirEstudiante()); 
                    est.setCiuEstudiante(datos.getCiuEstudiante()); 
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
    public List<Estudiante> listarTodos() { 
        return repo.findAll(); 
    } 
 
    @GetMapping("/{id}") 
    public ResponseEntity<Estudiante> buscarPorId(@PathVariable Long id) { 
        return repo.findById(id) 
                .map(ResponseEntity::ok) 
                .orElse(ResponseEntity.notFound().build()); 
    } 
}
