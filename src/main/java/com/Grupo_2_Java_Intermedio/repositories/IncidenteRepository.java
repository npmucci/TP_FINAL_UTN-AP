package com.Grupo_2_Java_Intermedio.repositories;

import com.Grupo_2_Java_Intermedio.Entidades.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface IncidenteRepository extends JpaRepository <Incidente, Integer> {


}
