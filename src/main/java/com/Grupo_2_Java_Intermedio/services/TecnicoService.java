package com.Grupo_2_Java_Intermedio.services;

import com.Grupo_2_Java_Intermedio.Entidades.Servicio;
import com.Grupo_2_Java_Intermedio.Entidades.Tecnico;
import com.Grupo_2_Java_Intermedio.repositories.TecnicoRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@NoArgsConstructor
public class TecnicoService {
    TecnicoRepository tecnicoRepository;

    @Autowired
    public TecnicoService ( TecnicoRepository tecnicoRepository){
        this.tecnicoRepository = tecnicoRepository;
    }

    public int guardar (Tecnico t){
        return tecnicoRepository.save(t).getId();
    }

    public List<Tecnico> buscarTodos(){

        return tecnicoRepository.findAll();
    }

    public void eliminar (Tecnico t){
        tecnicoRepository.delete(t);
    }


    public Tecnico buscarPorId(int id) {
        return tecnicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tecnico no encontrado con ID: " + id));
    }

}
