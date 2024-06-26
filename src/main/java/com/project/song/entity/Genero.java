package com.project.song.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGenero;
    private String nombre;

    @OneToMany(
            targetEntity = Cancion.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "genero"
    )
    @JsonManagedReference("cancion-genero")
    private List<Cancion> cancionList = new ArrayList<>();

}
