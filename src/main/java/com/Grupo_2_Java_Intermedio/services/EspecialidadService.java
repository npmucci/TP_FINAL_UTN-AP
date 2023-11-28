package com.Grupo_2_Java_Intermedio.services;

import com.Grupo_2_Java_Intermedio.Entidades.Especialidad;
import com.Grupo_2_Java_Intermedio.repositories.EspecialidadRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@NoArgsConstructor
public class EspecialidadService {
    EspecialidadRepository especielidadRepository;
    @Autowired
    public EspecialidadService(EspecialidadRepository especielidadRepository) {
        this.especielidadRepository = especielidadRepository;
    }

    public int guardar (Especialidad e){
        return especielidadRepository.save(e).getId();
    }

    public List<Especialidad> buscarTodos(){
        return especielidadRepository.findAll();
    }

    public void eliminar (Especialidad e){
        especielidadRepository.delete(e);
    }


    public Especialidad buscarPorId(int id) {
        return especielidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrado con ID: " + id));
    }
}
