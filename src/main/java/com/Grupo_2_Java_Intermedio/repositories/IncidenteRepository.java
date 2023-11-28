package com.Grupo_2_Java_Intermedio.repositories;

import com.Grupo_2_Java_Intermedio.Entidades.Especialidad;
import com.Grupo_2_Java_Intermedio.Entidades.Incidente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public interface IncidenteRepository extends JpaRepository <Incidente, Integer> {
    List<Incidente> findByFechaResolucionBetween(Date fechaInicio, Date fechaFin);


    List<Incidente> findByFechaResolucionBetweenAndEspecialidadesIn(
            Date fechaN, Date fechaActual, List<Especialidad> especialidades);

    List<Incidente> findByFechaResolucionIsNotNull();}
