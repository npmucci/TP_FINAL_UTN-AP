package com.Grupo_2_Java_Intermedio;

import com.Grupo_2_Java_Intermedio.Entidades.Incidente;
import com.Grupo_2_Java_Intermedio.Entidades.Tecnico;
import com.Grupo_2_Java_Intermedio.Manger.DataManager;
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

	@Autowired
	public TpFinalApplication(DataManager dataManager, IncidenteService incidenteService) {
		this.dataManager = dataManager;
		this.incidenteService = incidenteService;
	}

	public static void main(String[] args) {
		SpringApplication.run(TpFinalApplication.class, args);
        dataManager.inicializarDatos();

		// Obtener e imprimir incidentes resueltos en los últimos 60 días
		List<Incidente> incidentesUltimosDias = incidenteService.obtenerMasResueltosUltimosNDias(20);
		System.out.println("Incidentes resueltos en los últimos 20 días:");
		System.out.println(incidentesUltimosDias);

		// Obtener e imprimir al técnico con más incidentes resueltos en los últimos 60 días
		Tecnico tecnicoMasIncidentes = incidenteService.obtenerTecnicoMasIncidentesResueltosUltimosNDias(60);
		System.out.println("Técnico con más incidentes resueltos en los últimos 60 días: " + tecnicoMasIncidentes);

		// Obtener e imprimir al técnico más rápido resolviendo incidentes
		Tecnico tecnicoMasRapido = incidenteService.obtenerTecnicoMasRapidoResolviendo();
		System.out.println("Técnico más rápido resolviendo incidentes: " + tecnicoMasRapido);

		// Calcular e imprimir el tiempo de resolución para el primer incidente
		if (!incidentesUltimosDias.isEmpty()) {
			Incidente primerIncidente = incidentesUltimosDias.get(0);
			int tiempoResolucion = incidenteService.calcularTiempoResolucion(primerIncidente.getFechaIngreso(), primerIncidente.getFechaResolucion());
			System.out.println("Tiempo de resolución para el primer incidente: " + tiempoResolucion + " minutos");
		} else {
			System.out.println("No hay incidentes resueltos en los últimos 60 días.");
		}


	}
}


