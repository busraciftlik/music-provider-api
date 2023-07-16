package com.atmosware.busraciftlik.music.provider.controller;

import com.atmosware.busraciftlik.music.provider.service.ArtistService;
import com.atmosware.busraciftlik.music.provider.entity.Artist;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/artist")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService service;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Artist add(@RequestBody Artist artist){
      return service.add(artist);
    }

    @GetMapping("/getAll")
    public List<Artist> findAll(){
        return service.findAll();
    }

    @PutMapping("/update")
    public Artist update(@RequestBody Artist artist){
        return service.update(artist);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Artist delete(@PathVariable UUID id){
        return service.delete(id);
    }

    @PostMapping("/addMusic")
    public Set<Music> addMusic(@PathVariable UUID artistId, @RequestBody Music music){
       return service.addMusic(artistId,music);
    }
}
