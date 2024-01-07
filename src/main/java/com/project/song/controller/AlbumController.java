package com.project.song.controller;

import com.project.song.entity.Album;
import com.project.song.service.AlbumService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/album")
@AllArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    @PostMapping("/new")
    public ResponseEntity<?> saveAlbum(@RequestBody Album album){
        Map<String, String> response = new HashMap<>();
        albumService.save(album);
        response.put("estado","registrado "+album);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAlbums(){
        List<Album> albumList = albumService.getAll();
        return ResponseEntity.ok(albumList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>listAlbumById(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        Optional<Album> albumById = albumService.getById(id);
        if(albumById.isPresent()){
            return ResponseEntity.ok(albumById);
        }
        response.put("estado", "album con id="+id+" no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteAlbumById(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        Optional<Album> albumById = albumService.getById(id);
        if(albumById.isPresent()){
            response.put("estado", "album "+albumById.get().getNombre()+ " eliminado");
            albumService.deleteById(id);
            return ResponseEntity.ok(response);
        }
        response.put("estado", "Album con id="+id+" no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @RequestMapping(value = {"/search/", "/search/{palabra}"})
    public ResponseEntity<?> search(@PathVariable(required = false) String palabra){
        if(palabra == null || palabra.isEmpty()){
            return ResponseEntity.ok(albumService.getAll());
        }
        List<Album> searchAlbum = albumService.searchAlbum(palabra);
        Map<String, String> response = new HashMap<>();
        if(searchAlbum.isEmpty()){
            response.put("estado", "no se encontraron resultados para '"+palabra+"' ");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(searchAlbum);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Album album, @PathVariable Long id){
        Optional<Album> albumById = albumService.getById(id);
        Map<String, String> response = new HashMap<>();
        if(albumById.isPresent()){
            album.setIdAlbum(id);
            albumService.update(album);
            response.put("estado", "actualizado "+album);
            return ResponseEntity.ok(response);
        }
        response.put("estado", "album con id="+id+" no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
