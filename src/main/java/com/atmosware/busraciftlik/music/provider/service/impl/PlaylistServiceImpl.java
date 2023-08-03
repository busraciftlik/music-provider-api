package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.dto.PlaylistDto;
import com.atmosware.busraciftlik.music.provider.dto.request.PlaylistRequest;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.entity.Playlist;
import com.atmosware.busraciftlik.music.provider.repository.PlaylistRepository;
import com.atmosware.busraciftlik.music.provider.service.MusicService;
import com.atmosware.busraciftlik.music.provider.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.atmosware.busraciftlik.music.provider.util.EntityDtoMapper.mapPlaylistEntity2PlaylistDto;


@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository repository;
    private final MusicService musicService;

    @Override
    @Cacheable("playlist_set")
    public Set<PlaylistDto> findAll() {
        return mapPlaylistEntity2PlaylistDto(repository.findAll());
    }

    @Override
    public Playlist findById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    @CacheEvict(value = "playlist_set", allEntries = true)
    public PlaylistDto add(PlaylistRequest request) {
        Set<Music> musics = musicService.findAllByIds(request.getMusicIds());
        Playlist playlist = Playlist.builder().musics(musics).build();
        Playlist saved = repository.save(playlist);
        return mapPlaylistEntity2PlaylistDto(saved);
    }

    @Override
    @CacheEvict(value = "playlist_set", keyGenerator = "customKeyGenerator")
    public PlaylistDto update(Integer id, PlaylistRequest request) {
        Playlist playlist = repository.findById(id).orElseThrow();
        Set<Music> musics = musicService.findAllByIds(request.getMusicIds());
        playlist.setMusics(musics);
        Playlist saved = repository.save(playlist);
        return mapPlaylistEntity2PlaylistDto(saved);
    }

    @Override
    @CacheEvict(value = "playlist_set", keyGenerator = "customKeyGenerator")
    public Playlist delete(Integer id) {
        Playlist playlist = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return playlist;
    }

    @Override
    public PlaylistDto addMusic(Integer musicId) {
   // TODO: 1.08.2023
        return null;
    }
}
