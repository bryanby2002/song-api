package com.project.song.interfaces;

import com.project.song.entity.Cancion;

import java.util.List;
import java.util.Optional;

public interface ICancion {

    Cancion save(Cancion cancion);
    List<Cancion> getAll();
    Optional<Cancion> getById(Long id);
    void update(Cancion cancion);

}
