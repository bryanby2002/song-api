package com.project.song.interfaces;

import com.project.song.entity.Album;

import java.util.List;
import java.util.Optional;

public interface IAlbum {

    Album save(Album album);
    List<Album> getAll();
    Optional<Album> getById(Long id);
    void update(Album album);


}
