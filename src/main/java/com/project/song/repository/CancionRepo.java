package com.project.song.repository;

import com.project.song.entity.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancionRepo  extends JpaRepository<Cancion, Long> {
}
