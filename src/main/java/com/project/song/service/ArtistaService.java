package com.project.song.service;

import com.project.song.entity.Artista;
import com.project.song.interfaces.IArtista;
import com.project.song.repository.ArtistaRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor // generate constructor by lombok
public class ArtistaService  implements IArtista {

    // dependency injection by constructor
    private final ArtistaRepo artistaRepo;

    // methods for crud artista
    @Override
    public Artista save(Artista artista) {
        return artistaRepo.save(artista);
    }

    @Override
    public List<Artista> getAll() {
        return artistaRepo.findAll();
    }

    @Override
    public Optional<Artista> getById(Long id) {
        return artistaRepo.findById(id);
    }

    @Override
    public void update(Artista artista) {
        artistaRepo.save(artista);
    }
}
