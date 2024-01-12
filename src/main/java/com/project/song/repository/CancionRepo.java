package com.project.song.repository;

import com.project.song.entity.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CancionRepo  extends JpaRepository<Cancion, Long> {

    @Query("select c from Cancion c where " +
            "c.titulo like %:palabra% " +
            "or c.artista.nombreArtistico like %:palabra% " +
            "or c.genero.nombre like %:palabra% " +
            "or c.album.nombre like %:palabra% " +
            "or c.artista.banda.nombre like %:palabra%")
    List<Cancion> search(@Param(value = "palabra") String palabra);
}
