package com.atmosware.busraciftlik.music.provider.controller;

import com.atmosware.busraciftlik.music.provider.dto.MusicDto;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateMusicRequest;
import com.atmosware.busraciftlik.music.provider.enums.Genre;
import com.atmosware.busraciftlik.music.provider.service.MusicService;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/music")
@RequiredArgsConstructor
public class MusicController {
    private final MusicService service;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public MusicDto add(@RequestBody CreateMusicRequest request) {
        return service.add(request);
    }

    @GetMapping("/getAll")
    public Set<MusicDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/byAlbum")
    public Set<MusicDto> findMusicsByAlbum(@RequestParam String albumName){
       return service.searchMusicsByAlbum(albumName);
    }

    @GetMapping("/byArtist")
    public Set<MusicDto> findMusicsByArtist(@RequestParam String artistName){
        return service.searchMusicsByArtist(artistName);
    }

    @GetMapping("/byGenre")
    public Set<MusicDto> findMusicsByGenre(@RequestParam Genre genre){
        return service.searchMusicsByGenre(genre);
    }

    @PutMapping("/{id}")
    public Music update(@PathVariable Integer id,@RequestBody Music music) {
        return service.update(id,music);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Music delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}
