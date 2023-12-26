package com.project.song.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCancion;
    private String titulo;
    private String duracion;
    @Column(length = 2000)
    private String source;
    @Column(length = 1000000)
    private String letra;

    @ManyToOne(targetEntity = Album.class)
    @JoinColumn(name = "id_album")
    @JsonBackReference("cancion-album")
    private Album album;

    @ManyToOne(targetEntity = Genero.class)
    @JoinColumn(name = "id_genero")
    @JsonBackReference("cancion-genero")
    private Genero genero;

    @ManyToOne(targetEntity = Artista.class)
    @JoinColumn(name = "id_artista")
    @JsonBackReference("cancion-artista")
    private Artista artista;

}