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
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlbum;
    private String nombre;
    private LocalDate fechaCreacion;

    @OneToMany(
            targetEntity = Cancion.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "album"
    )
    @JsonManagedReference("cancion-album")
    private List<Cancion> cancionList = new ArrayList<>();


}
