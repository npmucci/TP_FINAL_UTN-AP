package com.Grupo_2_Java_Intermedio.services;


import com.Grupo_2_Java_Intermedio.Entidades.Servicio;
import com.Grupo_2_Java_Intermedio.repositories.ServicioRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@NoArgsConstructor
public class ServicioService {
    ServicioRepository servicioRepository;
    @Autowired
    public ServicioService(ServicioRepository servicioRepository){
        this.servicioRepository = servicioRepository;
    }

    public int guardar (Servicio s){
        return servicioRepository.save(s).getId();
    }

    public List<Servicio> buscarTodos(){
        return servicioRepository.findAll();
    }

    public void eliminar (Servicio s){
        servicioRepository.delete(s);
    }


    public Servicio buscarPorId(int id) {
        return servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + id));
    }
}
