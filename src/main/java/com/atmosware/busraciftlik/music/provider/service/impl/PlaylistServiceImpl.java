package com.atmosware.busraciftlik.music.provider.service.impl;

import com.atmosware.busraciftlik.music.provider.dto.PlaylistDto;
import com.atmosware.busraciftlik.music.provider.entity.Playlist;
import com.atmosware.busraciftlik.music.provider.repository.PlaylistRepository;
import com.atmosware.busraciftlik.music.provider.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import static com.atmosware.busraciftlik.music.provider.util.EntityDtoMapper.*;


@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository repository;

    @Override
    public Set<PlaylistDto> findAll() {
        return mapPlaylistEntity2PlaylistDto(repository.findAll());
    }

    @Override
    public Playlist findById(Integer id) {
        return null;
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
