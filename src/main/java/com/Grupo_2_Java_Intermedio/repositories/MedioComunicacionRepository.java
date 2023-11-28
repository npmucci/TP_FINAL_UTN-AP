package com.Grupo_2_Java_Intermedio.repositories;

import com.Grupo_2_Java_Intermedio.Entidades.MedioComunicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedioComunicacionRepository extends JpaRepository <MedioComunicacion, Integer> {
}
