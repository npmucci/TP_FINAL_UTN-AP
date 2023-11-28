package com.Grupo_2_Java_Intermedio.services;

import com.Grupo_2_Java_Intermedio.Entidades.Especialidad;
import com.Grupo_2_Java_Intermedio.Entidades.Incidente;
import com.Grupo_2_Java_Intermedio.Entidades.Tecnico;
import com.Grupo_2_Java_Intermedio.Enumeradores.EstadoEnum;
import com.Grupo_2_Java_Intermedio.repositories.IncidenteRepository;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@Transactional
public class IncidenteService {
    IncidenteRepository incidenteRepository;
    @Autowired
    public IncidenteService(IncidenteRepository incidenteRepository) {
        this.incidenteRepository = incidenteRepository;
    }

    public int guardar (Incidente i){
        return incidenteRepository.save(i).getId();
    }

    public List<Incidente> buscarTodos(){
        return incidenteRepository.findAll();
    }

    public void eliminar (Incidente i){
        incidenteRepository.delete(i);
    }

    @Transactional
    public Tecnico obtenerTecnicoConMasIncidentesResueltosEnUltimosNDias(int n) {
        // Obtener la fecha actual
        Date fechaActual = new Date();

        // Calcular la fecha N días atrás
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.DAY_OF_MONTH, -n);
        Date fechaN = calendar.getTime();

        // Obtener la lista de incidentes resueltos en los últimos N días
        List<Incidente> incidentesResueltos = incidenteRepository.findByFechaResolucionBetween(fechaN, fechaActual)
                .stream()
                .filter(incidente -> incidente.getEstado() == EstadoEnum.FINALIZADO)
                .collect(Collectors.toList());
        System.out.println("Incidentes resueltos en los últimos " + n + " días:");
        incidentesResueltos.forEach(System.out::println);

        // Contar la cantidad de incidentes resueltos por cada técnico
        Map<Tecnico, Long> incidentesPorTecnico = incidentesResueltos.stream()
                .collect(Collectors.groupingBy(Incidente::getTecnico, Collectors.counting()));

        // Encontrar al técnico con más incidentes resueltos
        Optional<Map.Entry<Tecnico, Long>> maxEntry = incidentesPorTecnico.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        // Devolver al técnico con más incidentes resueltos o null si no hay resultados
        return maxEntry.map(Map.Entry::getKey).orElse(null);
    }
    @Transactional
    public Tecnico obtenerTecnicoConMasIncidentesResueltosPorEspecialidadEnUltimosNDias(Especialidad especialidad, int n) {
        // Obtener la fecha actual
        Date fechaActual = new Date();


        // Calcular la fecha N días atrás
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.DAY_OF_MONTH, -n);
        Date fechaN = calendar.getTime();

        // Obtener la lista de incidentes resueltos en los últimos N días para una especialidad dada
        List<Incidente> incidentesResueltos = incidenteRepository
                .findByFechaResolucionBetweenAndEspecialidadesIn(fechaN, fechaActual, Collections.singletonList(especialidad))
                .stream()
                .filter(incidente -> incidente.getEstado() == EstadoEnum.FINALIZADO)
                .collect(Collectors.toList());

        // Contar el número de incidentes resueltos por cada técnico
        Map<Tecnico, Long> contadorPorTecnico = incidentesResueltos.stream()
                .collect(Collectors.groupingBy(Incidente::getTecnico, Collectors.counting()));

        // Encontrar al técnico con más incidentes resueltos
        Optional<Map.Entry<Tecnico, Long>> maxEntry = contadorPorTecnico.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        // Devolver al técnico con más incidentes resueltos o null si no hay resultados
        return maxEntry.map(Map.Entry::getKey).orElse(null);
    }
    @Transactional
    public Tecnico obtenerTecnicoConMayorVelocidadResolucion(int n) {
        // Obtener la fecha actual
        Date fechaActual = new Date();

        // Calcular la fecha N días atrás
        Date fechaN = calcularFechaNDiasAtras(fechaActual, n);

        // Obtener la lista de incidentes resueltos en los últimos N días
        List<Incidente> incidentesResueltos = incidenteRepository.findByFechaResolucionBetween(fechaN, fechaActual)
                .stream()
                .filter(incidente -> incidente.getEstado() == EstadoEnum.FINALIZADO)
                .collect(Collectors.toList());

        // Encontrar al técnico que resolvió los incidentes más rápido
        Optional<Tecnico> tecnicoMasRapido = incidentesResueltos.stream()
                .collect(Collectors.groupingBy(Incidente::getTecnico, Collectors.averagingDouble(incidente ->
                        calcularDuracionEnMinutos(incidente.getFechaIngreso(), incidente.getFechaResolucion()))))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);

        return tecnicoMasRapido.orElse(null);
    }

    // Método para calcular la duración en minutos entre dos fechas
    private double calcularDuracionEnMinutos(Date inicio, Date fin) {
        Instant instantInicio = inicio.toInstant();
        Instant instantFin = fin.toInstant();

        // Calcular la duración en minutos
        long minutos = Duration.between(instantInicio, instantFin).toMinutes();

        // Convertir el resultado a double
        return (double) minutos;
    }

    // Método para calcular la fecha N días atrás
    private Date calcularFechaNDiasAtras(Date fecha, int n) {
        // Usar Calendar para restar días
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_MONTH, -n);
        return calendar.getTime();
    }
    }


