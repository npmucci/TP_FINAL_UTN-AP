package com.Grupo_2_Java_Intermedio;

import com.Grupo_2_Java_Intermedio.Entidades.Especialidad;
import com.Grupo_2_Java_Intermedio.Entidades.Incidente;
import com.Grupo_2_Java_Intermedio.Entidades.Tecnico;
import com.Grupo_2_Java_Intermedio.Manger.DataManager;
import com.Grupo_2_Java_Intermedio.services.EspecialidadService;
import com.Grupo_2_Java_Intermedio.services.IncidenteService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TpFinalApplication {
	private static DataManager dataManager;
	private static IncidenteService incidenteService;

	private static EspecialidadService especialidadService;

	@Autowired
	public TpFinalApplication(DataManager dataManager, IncidenteService incidenteService, EspecialidadService especialidadService) {
		this.dataManager = dataManager;
		this.incidenteService = incidenteService;
		this.especialidadService = especialidadService;
	}

	public static void main(String[] args) {
		SpringApplication.run(TpFinalApplication.class, args);
		//dataManager.inicializarDatos();

		// Obtener el técnico con más incidentes resueltos en los últimos 30 días
		int ultimosNDias = 30;
		Tecnico tecnicoConMasIncidentes = incidenteService.obtenerTecnicoConMasIncidentesResueltosEnUltimosNDias(ultimosNDias);

		// Imprimir los resultados
		if (tecnicoConMasIncidentes != null) {
			System.out.println("Técnico con más incidentes resueltos en los últimos " + ultimosNDias + " días:");
			System.out.println("ID del Técnico: " + tecnicoConMasIncidentes.getId());
			System.out.println("Nombre del Técnico: " + tecnicoConMasIncidentes.getNombre());
			// ... Puedes imprimir otros detalles del técnico si es necesario
		} else {
			System.out.println("No hay resultados para el período especificado.");
		}

		Especialidad especialidad = especialidadService.buscarPorId(1);

		// Llamar al servicio para obtener al técnico con más incidentes resueltos
		Tecnico tecnico = incidenteService.obtenerTecnicoConMasIncidentesResueltosPorEspecialidadEnUltimosNDias(especialidad, ultimosNDias);

		if (tecnico != null) {
			System.out.println("El técnico con más incidentes resueltos en la especialidad "
					+ especialidad.getNombre() + " en los últimos " + ultimosNDias + " días es: " + tecnico.getNombre());
		} else {
			System.out.println("No hay resultados para el período y la especialidad especificados.");
		}

		// Obtener el técnico que resolvió los incidentes más rápido
		Tecnico tecnicoMasRapido = incidenteService.obtenerTecnicoConMayorVelocidadResolucion(ultimosNDias);

		if (tecnicoMasRapido != null) {
			System.out.println("El técnico que resolvió los incidentes más rápido es: " + tecnicoMasRapido.getNombre());
		} else {
			System.out.println("No hay resultados disponibles.");
		}

	}


}



