package com.Grupo_2_Java_Intermedio.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tecnico")
public class Tecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellido;
    @OneToMany
    @JoinColumn(name = "id_tecnico", referencedColumnName = "id")
    private List<Especialidad> especialidad;
    @OneToMany
    @JoinColumn(name = "tecnico_id")  // nombre de la columna en la tabla MedioComunicacion
    private List<MedioComunicacion> mediosComunicacion;
}
