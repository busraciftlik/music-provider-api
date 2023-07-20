package com.atmosware.busraciftlik.music.provider.controller;

import com.atmosware.busraciftlik.music.provider.dto.ArtistDto;
import com.atmosware.busraciftlik.music.provider.dto.request.UpdateArtistNameRequest;
import com.atmosware.busraciftlik.music.provider.entity.Album;
import com.atmosware.busraciftlik.music.provider.service.ArtistService;
import com.atmosware.busraciftlik.music.provider.entity.Artist;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @PutMapping("/updateName/{id}")
    public ArtistDto updateArtistName(@PathVariable Integer id, @RequestBody UpdateArtistNameRequest request){
        return service.updateArtistName(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Artist delete(@PathVariable Integer id){
        return service.delete(id);
    }

    @PostMapping("/addMusic/{id}")
    public Set<Music> addMusic(@PathVariable Integer id, @RequestBody Music music){
       return service.addMusic(id,music);
    }
    @PostMapping("/addAlbum/{id}")
    public Set<Album> addAlbum( @PathVariable Integer id ,@RequestBody Album album){
        return service.addAlbum(id,album);
    }
}
