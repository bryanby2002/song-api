package com.project.song.repository;

import com.project.song.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface GeneroRepo extends JpaRepository<Genero, Long> {

    @Query("select g from Genero g where g.nombre like %:palabra%")
    List<Genero> search(@Param(value = "palabra")String palabra);
}
