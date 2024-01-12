package com.project.song.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArtista;
    private String nombreArtistico;
    private String apellido;
    private Integer edad;
    private String sexo;
    private String nacionalidad;

    @ManyToOne(targetEntity = Banda.class)
    @JsonBackReference("artista-banda")
    @JoinColumn(name = "id_banda")
    private Banda banda;

    @OneToMany(
            targetEntity = Cancion.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "artista"
    )
    @JsonManagedReference("cancion-artista")
    private List<Cancion> cancionList = new ArrayList<>();

}
