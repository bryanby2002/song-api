package com.project.song.repository;

import com.project.song.entity.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistaRepo extends JpaRepository<Artista, Long> {

    @Query("select a from Artista a where a.nombre like %:palabra%")
    List<Artista> searchByNombre(@Param(value = "palabra") String palabra);
}
