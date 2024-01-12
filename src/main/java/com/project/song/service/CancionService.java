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
        return cancionRepo.save(cancion);
    }

    @Override
    public List<Cancion> getAll() {
        return cancionRepo.findAll();
    }

    @Override
    public Optional<Cancion> getById(Long id) {
        return cancionRepo.findById(id);
    }

    @Override
    public void update(Cancion cancion) {
        cancionRepo.save(cancion);
    }

    @Override
    public void deleteById(Long id) {
        cancionRepo.deleteById(id);
    }

    @Override
    public List<Cancion> search(String palabra) {
        return cancionRepo.search(palabra);
    }
}
