package com.project.song.repository;

import com.project.song.entity.Banda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandaRepo extends JpaRepository<Banda, Long> {

    @Query("select b from Banda b where b.nombre like %:palabra%")
    List<Banda>searchBanda(@Param(value = "palabra") String palabra);

}
