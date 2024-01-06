package com.project.song.repository;

import com.project.song.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface AlbumRepo extends JpaRepository<Album, Long> {

    @Query("select a from Album a where a.nombre like %:palabra%")
    List<Album> searchAlbum(@Param(value = "palabra") String palabra);

}
