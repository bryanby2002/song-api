package com.project.song.controller;

import com.project.song.entity.*;
import com.project.song.service.CancionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cancion")
@AllArgsConstructor
@Slf4j
public class CancionController {

    private final CancionService cancionService;

    @PostMapping("/new")
    public ResponseEntity<?> save(@RequestBody Cancion cancion) {
        Map<String, String> response = new HashMap<>();
        try {
            Album album = cancion.getAlbum();
            Genero genero = cancion.getGenero();
            Artista artista = cancion.getArtista();
            if (album != null && genero != null && artista != null) {
                album.getCancionList().add(cancion);
                genero.getCancionList().add(cancion);
                artista.getCancionList().add(cancion);
            }
            cancionService.save(cancion);
            response.put("estado", "registrado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error al registrar artista " + e.getMessage());
            response.put("estado", "error xd "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll(){
        List<Cancion> cancionList = cancionService.getAll();
        return ResponseEntity.ok(cancionList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Map<String,String> response = new HashMap<>();
        Optional<Cancion> cancionById = cancionService.getById(id);
        if(cancionById.isPresent()){
            return ResponseEntity.ok(cancionById);
        }
        response.put("estado","cancion con id="+" no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        Optional<Cancion> cancionById = cancionService.getById(id);
        if(cancionById.isPresent()){
            cancionService.deleteById(id);
            response.put("estado", "cancion "+cancionById.get().getTitulo()+" eliminado");
            return ResponseEntity.ok(response);
        }
        response.put("estado", "cancion con id="+id+" no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateById(@RequestBody Cancion cancion, @PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        Optional<Cancion> cancionById = cancionService.getById(id);
        if(cancionById.isPresent()){
            cancion.setIdCancion(id);
            cancionService.update(cancion);
            response.put("estado","actualizado "+cancion);
            return ResponseEntity.ok(response);
        }
        response.put("estado","cancion con id="+id+" no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @RequestMapping(value = {"/search/", "/search/{palabra}"})
    public ResponseEntity<?> search(@PathVariable(required = false) String palabra){
        if(palabra == null || palabra.isEmpty()){
            return ResponseEntity.ok(cancionService.getAll());
        }
        Map<String, String> response = new HashMap<>();
        List<Cancion> searchCancion = cancionService.search(palabra);
        if(searchCancion.isEmpty()){
            response.put("estado"," no se encontró resultados para'"+palabra+"' ");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(searchCancion);
    }
}
