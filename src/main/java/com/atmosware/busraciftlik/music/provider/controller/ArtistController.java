package com.atmosware.busraciftlik.music.provider.controller;

import com.atmosware.busraciftlik.music.provider.dto.AlbumDto;
import com.atmosware.busraciftlik.music.provider.dto.ArtistDto;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateArtistRequest;
import com.atmosware.busraciftlik.music.provider.service.ArtistService;
import com.atmosware.busraciftlik.music.provider.entity.Artist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/artist")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService service;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ArtistDto add(@RequestBody CreateArtistRequest request){
      return service.add(request);
    }

    @GetMapping("/getAll")
    public Set<ArtistDto> findAll(){
        return service.findAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ArtistDto update(@PathVariable Integer id,@RequestBody Artist artist){
        return service.update(id,artist);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public ArtistDto delete(@PathVariable Integer id){
        return service.delete(id);
    }

/*    @PostMapping("/addMusic/{artistId}")
    public Set<MusicDto> addMusic(@PathVariable Integer artistId, @RequestBody Music music){
       return service.addMusic(artistId,music);
    }
    @PostMapping("/addAlbum/{artistId}")
    public Set<AlbumDto> addAlbum(@PathVariable Integer artistId, @RequestBody AlbumRequest album){
        return service.addAlbum(artistId,album);
    }*/
}
