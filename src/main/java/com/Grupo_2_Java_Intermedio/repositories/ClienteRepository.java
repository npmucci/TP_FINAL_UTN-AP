package com.Grupo_2_Java_Intermedio.repositories;

import com.Grupo_2_Java_Intermedio.Entidades.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, Integer> {
}
