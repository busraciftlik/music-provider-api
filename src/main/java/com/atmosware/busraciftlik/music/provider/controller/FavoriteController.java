package com.atmosware.busraciftlik.music.provider.controller;

import com.atmosware.busraciftlik.music.provider.dto.FavoriteDto;
import com.atmosware.busraciftlik.music.provider.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favorite")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService service;

    @PostMapping("/like")
    public ResponseEntity<FavoriteDto> likeMusic(@RequestParam Integer musicId) {
        return ResponseEntity.ok(service.likeMusic(musicId));
    }

    @PostMapping("/unlike")
    public ResponseEntity<FavoriteDto> unlikeMusic(@RequestParam Integer musicId) {
        return ResponseEntity.ok(service.unlikeMusic(musicId));
    }
    @GetMapping("/likeCount")
    public ResponseEntity<Integer> getLikeCount(@RequestParam Integer musicId) {
        return ResponseEntity.ok(service.getLikeCount(musicId));
    }

}
