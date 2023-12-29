package com.project.song.repository;

import com.project.song.entity.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepo extends JpaRepository<Artista, Long> {
}
