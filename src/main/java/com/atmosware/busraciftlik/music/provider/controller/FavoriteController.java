package com.atmosware.busraciftlik.music.provider.controller;

import com.atmosware.busraciftlik.music.provider.dto.FavoriteDto;
import com.atmosware.busraciftlik.music.provider.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorite")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService service;

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<FavoriteDto> findAll(){
        return service.findAll();
    }

    @PostMapping("/like")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<FavoriteDto> likeMusic(@RequestParam Integer musicId) {
        return ResponseEntity.ok(service.likeMusic(musicId));
    }

    @PostMapping("/unlike")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<FavoriteDto> unlikeMusic(@RequestParam Integer musicId) {
        return ResponseEntity.ok(service.unlikeMusic(musicId));
    }
    @GetMapping("/likeCount")
    public ResponseEntity<Integer> getLikeCount(@RequestParam Integer musicId) {
        return ResponseEntity.ok(service.getLikeCount(musicId));
    }

}
