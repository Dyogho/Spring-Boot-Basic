package com.lcipriano.apiAcademica.models;

import java.time.LocalDate; // Calcular las edades

import jakarta.persistence.*;
import jakarta.validation.constraints.*;  // Agrege una dependencia mas.

@Entity
@Table(name = "docente")
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocente;
        
        // validación a través de la declaración de restricciones y metadatos para aplicaciones Java
        // https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nomDocente;

    private String dirDocente;
    private String ciuDocente;

    //Validaciones minimas
    
    @Email(message = "Correo no valido")  //El emailDocente debe tener el formato de correo
    private String emailDocente;

    @Past(message = "La fecha debe ser anterior al de hoy") //El tiempoServicio no puede ser negativo
    private LocalDate fecNacimiento;

    @Min(value = 0, message = "El tiempo de servicio no puede ser negativo") //El tiempoServicio no puede ser negativo
    private int tiempoServicio;

    public String getNomDocente() {
        return nomDocente;
    }

    public void setNomDocente(String nomDocente) {
        this.nomDocente = nomDocente;
    }

    public String getDirDocente() {
        return dirDocente;
    }

    public void setDirDocente(String dirDocente) {
        this.dirDocente = dirDocente;
    }

    public String getCiuDocente() {
        return ciuDocente;
    }

    public void setCiuDocente(String ciuDocente) {
        this.ciuDocente = ciuDocente;
    }

    public String getEmailDocente() {
        return emailDocente;
    }

    public void setEmailDocente(String emailDocente) {
        this.emailDocente = emailDocente;
    }

    public LocalDate getFecNacimiento() {
        return fecNacimiento;
    }

    public void setFecNacimiento(LocalDate fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }

    public int getTiempoServicio() {
        return tiempoServicio;
    }

    public void setTiempoServicio(int tiempoServicio) {
        this.tiempoServicio = tiempoServicio;
    }

}
