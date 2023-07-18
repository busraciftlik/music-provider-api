package com.atmosware.busraciftlik.music.provider.controller;

import com.atmosware.busraciftlik.music.provider.entity.Playlist;
import com.atmosware.busraciftlik.music.provider.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService service;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Playlist add(@RequestBody Playlist playlist){
       return service.add(playlist);
    }

    @GetMapping("/getAll")
    public List<Playlist> findAll(){
        return service.findAll();
    }

    @PutMapping("/update")
    public Playlist update(@RequestBody Playlist playlist){
        return service.update(playlist);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Playlist delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
