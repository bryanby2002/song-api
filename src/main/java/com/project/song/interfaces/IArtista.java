package com.project.song.interfaces;

import com.project.song.entity.Artista;

import java.util.List;
import java.util.Optional;

public interface IArtista {

    Artista save(Artista artista);
    List<Artista> getAll();
    Optional<Artista> getById(Long id);
    void update(Artista artista);
}
