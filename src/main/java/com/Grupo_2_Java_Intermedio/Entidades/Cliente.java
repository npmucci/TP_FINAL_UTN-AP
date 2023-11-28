package com.Grupo_2_Java_Intermedio.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
//ORM convierte el OBJETO a bases RELACIONALES => HIBERNATE es el ORM mas usado
//JPA permite persistir los objetos
@Data //Get - Set - Constructor  - ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity //Para crear la entidad de Cliente como una tabla
@Table(name="cliente") //para que Hibernate haga la tabla empleado
public class Cliente {
    @Id // para decirle q esta columna es IDENTIFICADOR
    @GeneratedValue(strategy = GenerationType.IDENTITY) //me da el auto-incremental
    private int id;
    private long cuit;
    private String razonSocial;
    private String email;
    private String nombre;
    private String apellido;
    @OneToMany
    @JoinColumn(name = "id_cliente", referencedColumnName = "id") // mi cliente se relaciona con muchos servicios
    private List<Servicio> servicios;

}

