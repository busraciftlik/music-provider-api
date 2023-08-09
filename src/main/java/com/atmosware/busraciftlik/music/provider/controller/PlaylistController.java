package com.atmosware.busraciftlik.music.provider.controller;

import com.atmosware.busraciftlik.music.provider.dto.PlaylistDto;
import com.atmosware.busraciftlik.music.provider.dto.request.PlaylistRequest;
import com.atmosware.busraciftlik.music.provider.entity.Playlist;
import com.atmosware.busraciftlik.music.provider.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/playlist")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService service;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public PlaylistDto add(@RequestBody PlaylistRequest request) {
        return service.add(request);
    }

    @GetMapping("/getAll")
    public Set<PlaylistDto> findAll() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public PlaylistDto update(@PathVariable Integer id, @RequestBody PlaylistRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public Playlist delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}
