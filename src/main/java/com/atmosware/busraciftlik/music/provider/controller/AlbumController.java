package com.atmosware.busraciftlik.music.provider.controller;

import com.atmosware.busraciftlik.music.provider.entity.Album;
import com.atmosware.busraciftlik.music.provider.service.AlbumService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService service;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Album add(@RequestBody Album album){
        return service.add(album);
    }

    @GetMapping("/getAll")
    public List<Album> findAll(){
        return service.findAll();
    }

    @PutMapping("/update")
    public Album update(@RequestBody Album album){
       return service.update(album);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Album delete(@PathVariable Integer id){
        return service.delete(id);
    }

}
