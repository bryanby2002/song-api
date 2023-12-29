package com.project.song.service;

import com.project.song.entity.Album;
import com.project.song.interfaces.IAlbum;
import com.project.song.repository.AlbumRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor // generate constructor by lombok
public class AlbumService implements IAlbum {

    // dependency injection by constructor
    private final AlbumRepo albumRepo;

    // methods for crud album
    @Override
    public Album save(Album album) {
        return albumRepo.save(album);
    }

    @Override
    public List<Album> getAll() {
        return albumRepo.findAll();
    }

    @Override
    public Optional<Album> getById(Long id) {
        return albumRepo.findById(id);
    }

    @Override
    public void update(Album album) {
        albumRepo.save(album);
    }
}
