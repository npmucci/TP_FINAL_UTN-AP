package com.Grupo_2_Java_Intermedio.repositories;

import com.Grupo_2_Java_Intermedio.Entidades.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository <Tecnico, Integer> {
}
