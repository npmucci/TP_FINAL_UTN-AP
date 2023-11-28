package com.Grupo_2_Java_Intermedio.Entidades;

import com.Grupo_2_Java_Intermedio.Enumeradores.EstadoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="incidente")
public class Incidente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String descripcion;
    private Date fechaIngreso;
    private Date fechaEstimadaResolucion;
    private Date fechaResolucion;
    private EstadoEnum estado;
    private String consideraciones;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;
    @OneToMany
    @JoinColumn(name = "incidente_id")  // nombre de la columna en la tabla TipoDeProblema
    private List<TipoProblema> tiposProblema;

    @OneToOne
    private Servicio servicio;
    //un incidente puede tener muchas especialidades, y una especialidad puede estar asociada con muchos incidentes
    @ManyToMany
    @JoinTable(
            name = "incidente_especialidad",
            joinColumns = @JoinColumn(name = "incidente_id"),
            inverseJoinColumns = @JoinColumn(name = "especialidad_id")
    )
    private List<Especialidad> especialidades;
}