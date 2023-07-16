package com.atmosware.busraciftlik.music.provider.controller;

import com.atmosware.busraciftlik.music.provider.service.MusicService;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/music")
@RequiredArgsConstructor
public class MusicController {
    private final MusicService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Music add(@RequestBody Music music) {
        return service.add(music);
    }

    @GetMapping("/getAll")
    public List<Music> findAll() {
        return service.findAll();
    }

    @PutMapping("/update")
    public Music update(@RequestBody Music music) {
        return service.update(music);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Music delete(@PathVariable UUID id) {
        return service.delete(id);
    }
}
