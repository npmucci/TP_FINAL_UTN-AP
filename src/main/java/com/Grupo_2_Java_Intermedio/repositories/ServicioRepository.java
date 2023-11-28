package com.Grupo_2_Java_Intermedio.repositories;

        import com.Grupo_2_Java_Intermedio.Entidades.Servicio;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository <Servicio, Integer> {
}
