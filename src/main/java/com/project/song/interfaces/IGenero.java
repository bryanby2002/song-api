package com.project.song.interfaces;

import com.project.song.entity.Genero;

import java.util.List;
import java.util.Optional;

public interface IGenero {

    Genero save(Genero genero);
    List<Genero> getAll();
    Optional<Genero> getById(Long id);
    void update(Genero genero);
    void deleteById(long id);
    List<Genero> search(String palabra);

}
