package com.Grupo_2_Java_Intermedio.services;

import com.Grupo_2_Java_Intermedio.Entidades.Cliente;
import com.Grupo_2_Java_Intermedio.repositories.ClienteRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@NoArgsConstructor
public class ClienteService {
    ClienteRepository clienteRepository;
    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public int guardar (Cliente c){
        return clienteRepository.save(c).getId();
    }

    public List<Cliente> buscarTodos(){
        return clienteRepository.findAll();
    }

    public void eliminar (Cliente c){
        clienteRepository.delete(c);
    }


    public Cliente buscarPorId(int id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }
}
