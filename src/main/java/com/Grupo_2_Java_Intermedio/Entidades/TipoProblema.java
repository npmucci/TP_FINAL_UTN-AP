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
@Table(name="tipo_problema")
public class TipoProblema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tipo;
    private int tiempoEstimado;
    private int tiempoMaximo;
    //un tipo de problema puede tener muchas especialidades, y una especialidad puede estar asociada con muchos tipos de problemas
    @ManyToMany
    @JoinTable(
            name = "tipo_problema_especialidad",
            joinColumns = @JoinColumn(name = "tipo_problema_id"),
            inverseJoinColumns = @JoinColumn(name = "especialidad_id")
    )
    private List<Especialidad> especialidades;
}
