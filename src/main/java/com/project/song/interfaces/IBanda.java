package com.project.song.interfaces;

import com.project.song.entity.Banda;

import java.util.List;
import java.util.Optional;

public interface IBanda {

    Banda save(Banda banda);
    List<Banda> getAll();
    Optional<Banda> getById(Long id);
    void update(Banda banda);
    void deleteById(Long id);
    List<Banda>search(String palabra);
}
