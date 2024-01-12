    package com.project.song.controller;
    
    import com.project.song.entity.Artista;
    import com.project.song.entity.Banda;
    import com.project.song.service.ArtistaService;
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
    @RequestMapping("/artista")
    @AllArgsConstructor
    @Slf4j
    public class ArtistaController {
    
        private final ArtistaService artistaService;
    
        @PostMapping("/new")
        public ResponseEntity<?> save(@RequestBody Artista artista) {
            Map<String, String> response = new HashMap<>();
            try {
                Artista artistaNuevo = artistaService.save(artista);
                Banda banda = artista.getBanda();
                if (banda != null) {
                    banda.getArtistaList().add(artistaNuevo);
                }
                response.put("estado", "registrado correctamente");
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                log.error("Error al registrar artista " + e.getMessage());
                response.put("estado", "error xd");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }
    
        @GetMapping("/list")
        public ResponseEntity<?> getAll(){
            List<Artista> artistaList = artistaService.getAll();
            return ResponseEntity.ok(artistaList);
        }
    
        @GetMapping("/{id}")
        public ResponseEntity<?> getById(@PathVariable Long id){
            Map<String,String> response = new HashMap<>();
            Optional<Artista> artistaById = artistaService.getById(id);
            if(artistaById.isPresent()){
                return ResponseEntity.ok(artistaById);
            }
            response.put("estado","artista con id="+" no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> deleteById(@PathVariable Long id){
            Map<String, String> response = new HashMap<>();
            Optional<Artista> artistaById = artistaService.getById(id);
            if(artistaById.isPresent()){
                artistaService.deleteById(id);
                response.put("estado", "artista "+artistaById.get().getNombreArtistico()+" eliminado");
                return ResponseEntity.ok(response);
            }
            response.put("estado", "artista con id="+id+" no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    
        @PatchMapping("/update/{id}")
        public ResponseEntity<?> updateById(@RequestBody Artista artista, @PathVariable Long id){
            Map<String, String> response = new HashMap<>();
            Optional<Artista> artistaById = artistaService.getById(id);
            if(artistaById.isPresent()){
                artista.setIdArtista(id);
                artistaService.update(artista);
                response.put("estado","actualizado "+artista);
                return ResponseEntity.ok(response);
            }
            response.put("estado","artista con id="+id+" no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    
        @RequestMapping(value = {"/search/", "/search/{palabra}"})
        public ResponseEntity<?> search(@PathVariable(required = false) String palabra){
            if(palabra == null || palabra.isEmpty()){
                return ResponseEntity.ok(artistaService.getAll());
            }
            Map<String, String> response = new HashMap<>();
            List<Artista> searchArtista = artistaService.searchArtista(palabra);
            if(searchArtista.isEmpty()){
                response.put("estado"," no se encontró resultados para '"+palabra+"' ");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.ok(searchArtista);
        }
    }
