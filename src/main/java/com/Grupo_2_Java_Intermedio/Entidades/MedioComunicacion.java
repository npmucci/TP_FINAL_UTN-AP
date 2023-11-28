package com.Grupo_2_Java_Intermedio.Entidades;

import com.Grupo_2_Java_Intermedio.Enumeradores.MedioEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="medio_comunicacion")
public class MedioComunicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private MedioEnum medioEnum;
    private String contacto;
}
