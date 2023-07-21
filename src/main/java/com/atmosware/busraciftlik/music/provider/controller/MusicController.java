package com.atmosware.busraciftlik.music.provider.controller;

import com.atmosware.busraciftlik.music.provider.dto.MusicDto;
import com.atmosware.busraciftlik.music.provider.dto.request.CreateMusicRequest;
import com.atmosware.busraciftlik.music.provider.service.MusicService;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/music")
@RequiredArgsConstructor
public class MusicController {
    private final MusicService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MusicDto add(@RequestBody CreateMusicRequest request) {
        return service.add(request);
    }

    @GetMapping("/getAll")
    public Set<MusicDto> findAll() {
        return service.findAll();
    }

    @PutMapping("/update")
    public Music update(@RequestBody Music music) {
        return service.update(music);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Music delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}
