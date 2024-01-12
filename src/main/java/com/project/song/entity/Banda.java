package com.project.song.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Banda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBanda;
    private String nombre;
    @Column(length = 500)
    private String descripcion;
    private String origen;
    private LocalDate fechaCreacion;
    @OneToMany(
            targetEntity = Artista.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "banda"
    )
    @JsonManagedReference("artista-banda")
    private List<Artista> artistaList = new ArrayList<>();

}
