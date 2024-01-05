package com.project.song.controller;

import com.project.song.entity.Album;
import com.project.song.service.AlbumService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/album")
@AllArgsConstructor
@Slf4j
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping("/new-album")
    public String newAlbumForm(Model model){
        model.addAttribute("album", new Album());
        return "newAlbum";
    }

    @PostMapping("/new-album")
    public String newAlbum(@ModelAttribute Album album){
        try {
            albumService.save(album);
            log.info("Album registrado correctamente");
            return "redirect:/new-album";
        }catch (Exception e){
            log.error("Error al registrar album");
            return "errorPage";
        }
    }
}
