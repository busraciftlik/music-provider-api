package com.atmosware.busraciftlik.music.provider.controller;

import com.atmosware.busraciftlik.music.provider.dto.AlbumDto;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateAlbumRequest;
import com.atmosware.busraciftlik.music.provider.dto.request.UpdateAlbumRequest;
import com.atmosware.busraciftlik.music.provider.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/album")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService service;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public AlbumDto add(@RequestBody CreateAlbumRequest request){
        return service.add(request);
    }

    @GetMapping("/getAll")
    public Set<AlbumDto> findAll(){
        return service.findAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AlbumDto update(@PathVariable Integer id,@RequestBody UpdateAlbumRequest request){
       return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public AlbumDto delete(@PathVariable Integer id){
        return service.delete(id);
    }

}
