package com.project.song.service;

import com.project.song.entity.Banda;
import com.project.song.interfaces.IBanda;
import com.project.song.repository.BandaRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor // anotacion de lombok genera el constructor
public class BandaService implements IBanda {

    // inyeccion de dependencia por constructor
    private final BandaRepo bandaRepo;

    @Override
    public Banda save(Banda banda) {
        return bandaRepo.save(banda);
    }

    @Override
    public List<Banda> getAll() {
        return bandaRepo.findAll();
    }

    @Override
    public Optional<Banda> getById(Long id) {
        return bandaRepo.findById(id);
    }

    @Override
    public void update(Banda banda) {
        bandaRepo.save(banda);
    }

    @Override
    public void deleteById(Long id) {
        bandaRepo.deleteById(id);
    }

    @Override
    public List<Banda> search(String palabra) {
        return bandaRepo.searchBanda(palabra);
    }
}
