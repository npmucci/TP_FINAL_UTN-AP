package com.Grupo_2_Java_Intermedio.services;

import com.Grupo_2_Java_Intermedio.Entidades.Incidente;
import com.Grupo_2_Java_Intermedio.Entidades.MedioComunicacion;
import com.Grupo_2_Java_Intermedio.repositories.IncidenteRepository;
import com.Grupo_2_Java_Intermedio.repositories.MedioComunicacionRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@NoArgsConstructor
public class MedioComunicacionService {
    MedioComunicacionRepository medioComunicacionRepository;
    @Autowired
    public MedioComunicacionService(MedioComunicacionRepository medioComunicacionRepository){
        this.medioComunicacionRepository = medioComunicacionRepository;
    }

    public int guardar (MedioComunicacion m){
        return medioComunicacionRepository.save(m).getId();
    }

    public List<MedioComunicacion> buscarTodos(){
        return medioComunicacionRepository.findAll();
    }

    public void eliminar (MedioComunicacion m){
        medioComunicacionRepository.delete(m);
    }


}
