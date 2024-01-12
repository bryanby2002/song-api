package com.project.song.controller;

import com.project.song.entity.Banda;
import com.project.song.service.BandaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/banda")
@AllArgsConstructor
public class BandaController {

    private final BandaService bandaService;

    @PostMapping("/new")
    public ResponseEntity<?> saveAlbum(@RequestBody Banda banda){
        Map<String, String> response = new HashMap<>();
        bandaService.save(banda);
        response.put("estado","registrado "+banda);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAlbums(){
        List<Banda> bandaList = bandaService.getAll();
        return ResponseEntity.ok(bandaList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        Optional<Banda> bandaById = bandaService.getById(id);
        if(bandaById.isPresent()){
            return ResponseEntity.ok(bandaById);
        }
        response.put("estado", "banda con id="+id+" no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteById(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        Optional<Banda> getById = bandaService.getById(id);
        if(getById.isPresent()){
            response.put("estado", "album "+getById.get().getNombre()+ " eliminado");
            bandaService.deleteById(id);
            return ResponseEntity.ok(response);
        }
        response.put("estado", "banda con id="+id+" no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @RequestMapping(value = {"/search/", "/search/{palabra}"})
    public ResponseEntity<?> search(@PathVariable(required = false) String palabra){
        if(palabra == null || palabra.isEmpty()){
            return ResponseEntity.ok(bandaService.getAll());
        }
        List<Banda> searchBanda = bandaService.search(palabra);
        Map<String, String> response = new HashMap<>();
        if(searchBanda.isEmpty()){
            response.put("estado", "no se encontraron resultados '"+palabra+"' ");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(searchBanda);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Banda banda, @PathVariable Long id){
        Optional<Banda> bandaById = bandaService.getById(id);
        Map<String, String> response = new HashMap<>();
        if(bandaById.isPresent()){
            banda.setIdBanda(id);
            bandaService.update(banda);
            response.put("estado", "actualizado "+banda);
            return ResponseEntity.ok(response);
        }
        response.put("estado", "banda con id="+id+" no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
