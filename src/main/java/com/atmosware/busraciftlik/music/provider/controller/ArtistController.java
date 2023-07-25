package com.atmosware.busraciftlik.music.provider.controller;

import com.atmosware.busraciftlik.music.provider.dto.AlbumDto;
import com.atmosware.busraciftlik.music.provider.dto.ArtistDto;
import com.atmosware.busraciftlik.music.provider.dto.MusicDto;
import com.atmosware.busraciftlik.music.provider.dto.request.AlbumRequest;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateArtistRequest;
import com.atmosware.busraciftlik.music.provider.dto.request.UpdateArtistNameRequest;
import com.atmosware.busraciftlik.music.provider.service.ArtistService;
import com.atmosware.busraciftlik.music.provider.entity.Artist;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/artist")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService service;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistDto add(@RequestBody CreateArtistRequest request){
      return service.add(request);
    }

    @GetMapping("/getAll")
    public Set<ArtistDto> findAll(){
        return service.findAll();
    }

    @PutMapping("/{id}")
    public ArtistDto update(@PathVariable Integer id,@RequestBody Artist artist){
        return service.update(id,artist);
    }

    @PutMapping("/updateName/{id}")
    public ArtistDto updateArtistName(@PathVariable Integer id, @RequestBody UpdateArtistNameRequest request){
        return service.updateArtistName(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ArtistDto delete(@PathVariable Integer id){
        return service.delete(id);
    }

    @PostMapping("/addMusic/{artistId}")
    public Set<MusicDto> addMusic(@PathVariable Integer artistId, @RequestBody Music music){
       return service.addMusic(artistId,music);
    }
    @PostMapping("/addAlbum/{artistId}")
    public Set<AlbumDto> addAlbum(@PathVariable Integer artistId, @RequestBody AlbumRequest album){
        return service.addAlbum(artistId,album);
    }
}
