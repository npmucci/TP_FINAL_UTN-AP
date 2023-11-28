package com.Grupo_2_Java_Intermedio.services;

import com.Grupo_2_Java_Intermedio.Entidades.Incidente;
import com.Grupo_2_Java_Intermedio.Entidades.Tecnico;
import com.Grupo_2_Java_Intermedio.repositories.IncidenteRepository;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
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
    public List<Incidente> obtenerMasResueltosUltimosNDias(int n) {
        LocalDate fechaLimite = LocalDate.now().minusDays(n);
        List<Incidente> incidentes = incidenteRepository.findAll().stream()
                .filter(incidente -> incidente.getFechaResolucion() != null && incidente.getFechaResolucion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(fechaLimite))
                .sorted(Comparator.comparing(Incidente::getFechaResolucion).reversed())
                .collect(Collectors.toList());

        // Inicializar las asociaciones de cada incidente dentro de una nueva transacción
        incidentes.forEach(incidente -> {
            try {
                Hibernate.initialize(incidente.getTecnico());
            } catch (LazyInitializationException e) {
                // Maneja la excepción como desees, por ejemplo, puedes imprimir un mensaje de log
                System.out.println("Excepción de inicialización perezosa: " + e.getMessage());
            }
        });

        return incidentes;
    }



    @Transactional
    public Tecnico obtenerTecnicoMasIncidentesResueltosUltimosNDias(int n) {
        List<Incidente> incidentes = obtenerMasResueltosUltimosNDias(n);

        // Inicializar las asociaciones de cada incidente
        incidentes.forEach(incidente -> {
            Hibernate.initialize(incidente.getTecnico());

        });

        return incidentes.stream()
                .collect(Collectors.groupingBy(Incidente::getTecnico, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
    @Transactional
    public Tecnico obtenerTecnicoMasRapidoResolviendo() {
        List<Incidente> incidentes = incidenteRepository.findAll().stream()
                .filter(incidente -> incidente.getFechaResolucion() != null)
                .sorted(Comparator.comparingInt(incidente -> calcularTiempoResolucion(incidente.getFechaIngreso(), incidente.getFechaResolucion())))
                .collect(Collectors.toList());

        // Inicializar las asociaciones de cada incidente
        incidentes.forEach(incidente -> {
            Hibernate.initialize(incidente.getTecnico());

        });

        return incidentes.stream()
                .min(Comparator.comparingInt(incidente -> calcularTiempoResolucion(incidente.getFechaIngreso(), incidente.getFechaResolucion())))
                .map(Incidente::getTecnico)
                .orElse(null);
    }

    @Transactional
    public int calcularTiempoResolucion(Date fechaIngreso, Date fechaResolucion) {
        return (int) Duration.between(fechaIngreso.toInstant(), fechaResolucion.toInstant()).toMinutes();
    }


}
