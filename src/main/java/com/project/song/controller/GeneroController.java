package com.project.song.controller;

import com.project.song.entity.Genero;
import com.project.song.service.GeneroService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/genero")
@AllArgsConstructor
public class GeneroController {

    private final GeneroService generoService;

    @PostMapping("/new")
    public ResponseEntity<?> saveAlbum(@RequestBody Genero genero){
        Map<String, String> response = new HashMap<>();
        generoService.save(genero);
        response.put("estado","registrado "+genero);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll(){
        List<Genero> generoList = generoService.getAll();
        return ResponseEntity.ok(generoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        Optional<Genero> generoById = generoService.getById(id);
        if(generoById.isPresent()){
            return ResponseEntity.ok(generoById);
        }
        response.put("estado", "genero con id="+id+" no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteById(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        Optional<Genero> getById = generoService.getById(id);
        if(getById.isPresent()){
            response.put("estado", "genero "+getById.get().getNombre()+ " eliminado");
            generoService.deleteById(id);
            return ResponseEntity.ok(response);
        }
        response.put("estado", "genero con id="+id+" no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @RequestMapping(value = {"/search/", "/search/{palabra}"})
    public ResponseEntity<?> search(@PathVariable(required = false) String palabra){
        if(palabra == null || palabra.isEmpty()){
            return ResponseEntity.ok(generoService.getAll());
        }
        List<Genero> generoList = generoService.search(palabra);
        Map<String, String> response = new HashMap<>();
        if(generoList.isEmpty()){
            response.put("estado", "no se encontraron resultados = '"+palabra+"' ");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(generoList);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Genero genero, @PathVariable Long id){
        Optional<Genero> generoById = generoService.getById(id);
        Map<String, String> response = new HashMap<>();
        if(generoById.isPresent()){
            genero.setIdGenero(id);
            generoService.update(genero);
            response.put("estado", "actualizado "+genero);
            return ResponseEntity.ok(response);
        }
        response.put("estado", "genero con id="+id+" no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
