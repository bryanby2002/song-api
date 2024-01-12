package com.project.song.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCancion;
    private String titulo;
    private LocalTime duracion;
    @Column(length = 2000)
    private String source;
    @Column(length = 2000)
    private String letra;

    @ManyToOne(targetEntity = Album.class)
    @JsonBackReference("cancion-album")
    @JoinColumn(name = "id_album")
    private Album album;

    @ManyToOne(targetEntity = Genero.class)
    @JsonBackReference("cancion-genero")
    @JoinColumn(name = "id_genero")
    private Genero genero;

    @ManyToOne(targetEntity = Artista.class)
    @JsonBackReference("cancion-artista")
    @JoinColumn(name = "id_artista")
    private Artista artista;

}