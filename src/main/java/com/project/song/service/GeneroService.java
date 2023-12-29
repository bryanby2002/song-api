package com.project.song.service;

import com.project.song.entity.Genero;
import com.project.song.interfaces.IGenero;
import com.project.song.repository.GeneroRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor // generated constructor by lombok
public class GeneroService implements IGenero {

    // dependency injection by constructor
    private final GeneroRepo generoRepo;

    // methods for crud genero
    @Override
    public Genero save(Genero genero) {
        return generoRepo.save(genero);
    }

    @Override
    public List<Genero> getAll() {
        return generoRepo.findAll();
    }

    @Override
    public Optional<Genero> getById(Long id) {
        return generoRepo.findById(id);
    }

    @Override
    public void update(Genero genero) {
        generoRepo.save(genero);
    }
}
