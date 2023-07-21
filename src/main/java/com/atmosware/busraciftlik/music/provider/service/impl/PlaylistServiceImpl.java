package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.entity.Playlist;
import com.atmosware.busraciftlik.music.provider.repository.PlaylistRepository;
import com.atmosware.busraciftlik.music.provider.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository repository;

    @Override
    public List<Playlist> findAll() {
        return repository.findAll();
    }

    @Override
    public Playlist add(Playlist playlist) {
        return repository.save(playlist);
    }

    @Override
    public Playlist update(Playlist playlist) {
        return null;
    }

    @Override
    public Playlist delete(Integer id) {
        Playlist playlist = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return playlist;
    }
}
