package com.Grupo_2_Java_Intermedio.services;

import com.Grupo_2_Java_Intermedio.Entidades.Servicio;
import com.Grupo_2_Java_Intermedio.Entidades.TipoProblema;
import com.Grupo_2_Java_Intermedio.repositories.TecnicoRepository;
import com.Grupo_2_Java_Intermedio.repositories.TipoProblemaRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@NoArgsConstructor
public class TipoProblemaService {
    TipoProblemaRepository tipoProblemaRepository;

    @Autowired
    public TipoProblemaService (TipoProblemaRepository tipoProblemaRepository){
        this.tipoProblemaRepository = tipoProblemaRepository;
    }

    public int guardar (TipoProblema tp){
        return tipoProblemaRepository.save(tp).getId();
    }

    public List<TipoProblema> buscarTodos(){
        return tipoProblemaRepository.findAll();
    }

    public void eliminar (TipoProblema tp){
        tipoProblemaRepository.delete(tp);
    }

    public TipoProblema buscarPorId(int id) {
        return tipoProblemaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Problema no encontrado con ID: " + id));
    }
}
