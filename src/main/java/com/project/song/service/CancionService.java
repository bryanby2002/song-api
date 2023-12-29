package com.project.song.service;

import com.project.song.entity.Cancion;
import com.project.song.interfaces.ICancion;
import com.project.song.repository.CancionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor // generated constructor by lombok
public class CancionService implements ICancion {

    // dependency injection by constructor
    private final CancionRepo cancionRepo;

    // methods for crud cancion
    @Override
    public Cancion save(Cancion cancion) {
        return null;
    }

    @Override
    public List<Cancion> getAll() {
        return null;
    }

    @Override
    public Optional<Cancion> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Cancion cancion) {

    }
}
